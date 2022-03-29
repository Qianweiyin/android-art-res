/*
 * Copyright (C) 2012 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.zxing.camera;

import android.content.Context;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.RejectedExecutionException;


@SuppressWarnings("deprecation") // camera APIs
final class AutoFocusManager implements Camera.AutoFocusCallback {
    private static final String TAG = AutoFocusManager.class.getSimpleName();


    //设置自动对焦并2秒对焦一次
    //    private static long AUTO_FOCUS_INTERVAL_MS = 2000L;
    private static long AUTO_FOCUS_INTERVAL_MS = 1000L;


    private static final Collection<String> FOCUS_MODES_CALLING_AF;
    private final Camera mCamera;
    private boolean stopped;
    private boolean focusing;
    private final boolean useAutoFocus;
    private AsyncTask outstandingTask;
    private CameraManager.CameraManagerInterface mAutoFocusManager;


    public AutoFocusManager(Context context, Camera camera) {
        mCamera = camera;
        String currentFocusMode = camera.getParameters().getFocusMode();
        useAutoFocus = FOCUS_MODES_CALLING_AF.contains(currentFocusMode);
        Log.e("QwyZxing", "Current focus mode '" + currentFocusMode + "'; use auto focus? " + useAutoFocus);
        start();
    }


    @Override
    public synchronized void onAutoFocus(boolean success, Camera theCamera) {
        synchronized (this) {
            focusing = false;
            autoFocusAgainLater();
            // TODO: 2022/3/29 qwyelink add
            if (mAutoFocusManager != null) {
                mAutoFocusManager.onAutoFocus();
            }
        }
    }


    private final class AutoFocusTask extends AsyncTask {

        public AutoFocusTask() {
        }

        @Override
        protected Object doInBackground(Object... voids) {
            try {
                Thread.sleep(AUTO_FOCUS_INTERVAL_MS);
            } catch (InterruptedException e) {
                // continue
                e.printStackTrace();
            }
            start();
            return null;
        }
    }


    static {
        FOCUS_MODES_CALLING_AF = new ArrayList<>(2);
        FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_AUTO);
        FOCUS_MODES_CALLING_AF.add(Camera.Parameters.FOCUS_MODE_MACRO);
    }


    private synchronized void autoFocusAgainLater() {
        synchronized (this) {
            if (!stopped && outstandingTask == null) {
                AutoFocusTask newTask = new AutoFocusTask();
                try {
                    newTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    this.outstandingTask = newTask;
                } catch (RejectedExecutionException ree) {
                    Log.w(TAG, "Could not request auto focus", ree);
                }
            }
        }
    }


    private synchronized void cancelOutstandingTask() {
        synchronized (this) {
            if (outstandingTask != null) {
                if (outstandingTask.getStatus() != AsyncTask.Status.FINISHED) {
                    outstandingTask.cancel(true);
                }
                outstandingTask = null;
            }
        }
    }


    /**
     * 设置自动对焦time
     *
     * @param j
     */
    public static void bW(long j) {
        AUTO_FOCUS_INTERVAL_MS = j;
    }

    /**
     * is 自动对焦
     *
     * @param
     */

    public boolean isFocusing() {
        boolean mFocusing;
        synchronized (this) {
            mFocusing = focusing;
        }
        return mFocusing;
    }

    public void setAutoFocusManager(CameraManager.CameraManagerInterface autoFocusManager) {
        mAutoFocusManager = autoFocusManager;
    }


    public void start() {
        synchronized (this) {
            if (useAutoFocus) {
                outstandingTask = null;
                if (!stopped && !focusing) {
                    try {
                        mCamera.autoFocus(this);
                        focusing = true;
                    } catch (RuntimeException e) {
                        // Have heard RuntimeException reported in Android 4.0.x+; continue?
                        Log.w(TAG, "Unexpected exception while focusing", e);
                        // Try again later to keep cycle going
                        autoFocusAgainLater();
                    }
                }
            }
        }
    }


    public void stop() {
        synchronized (this) {
            stopped = true;
            if (useAutoFocus) {
                cancelOutstandingTask();
                // Doesn't hurt to call this even if not focusing
                try {
                    mCamera.cancelAutoFocus();
                } catch (RuntimeException e) {
                    Log.w(TAG, "Unexpected exception while cancelling focusing", e);
                }
            }
        }
    }

}
