/*
 * Copyright (C) 2008 ZXing authors
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
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;

import com.google.zxing.camera.open.OpenCamera;
import com.google.zxing.camera.open.OpenCameraInterface;

import java.io.IOException;

/**
 * This object wraps the Camera service object and expects to be the only one talking to it. The
 * implementation encapsulates the steps needed to take preview-sized images, which are used for
 * both preview and decoding.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
@SuppressWarnings("deprecation") // camera APIs
public final class CameraManager {
    private static final String TAG = CameraManager.class.getSimpleName();
    private final CameraConfigurationManager configManager;
    private OpenCamera mCamera;
    private AutoFocusManager mAutoFocusManager;
    private int requestedCameraId = OpenCameraInterface.NO_REQUESTED_CAMERA;
    private boolean mInPortrait;
    /**
     * Preview frames are delivered here, which we pass on to the registered handler. Make sure to
     * clear the handler so it will only receive one message.
     */
    private final PreviewCallback previewCallback;
    private final Context mContext;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;
    private boolean previewing;
    private int requestedFramingRectWidth;
    private int requestedFramingRectHeight;


    private static final int MIN_FRAME_WIDTH = 240;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MAX_FRAME_WIDTH = 1200; // = 5/8 * 1920
    private static final int MAX_FRAME_HEIGHT = 1200; // = 5/8 * 1080


    public interface CameraManagerInterface {
        void onAutoFocus();
    }


    public CameraManager(Context context) {
        mContext = context;
        configManager = new CameraConfigurationManager(context);
        previewCallback = new PreviewCallback(configManager);
        mInPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }


    private static int findDesiredDimensionInRange(int resolution, int hardMin, int hardMax) {
        int dim = (5 * resolution) / 8; // Target 5/8 of each dimension
        return dim < hardMin ? hardMin : Math.min(dim, hardMax);
    }


    public boolean Na() {
        return this.mInPortrait;
    }


    // TODO: 2022/3/29 qwyelink modify
    public void a(Context context, final Camera.PictureCallback pictureCallback) {
        if (!mAutoFocusManager.isFocusing()) {
            mCamera.getCamera().takePicture(null, null, pictureCallback);
            mAutoFocusManager.stop();
            return;
        }
        mAutoFocusManager.setAutoFocusManager(new CameraManagerInterface() {
            @Override
            public void onAutoFocus() {
                mCamera.getCamera().takePicture(null, null, pictureCallback);
                mAutoFocusManager.stop();
            }
        });
    }


    public float onScale(float scale) {
        OpenCamera openCamera = mCamera;
        if (!(openCamera == null || openCamera.getCamera() == null)) {
            Camera.Parameters parameters = mCamera.getCamera().getParameters();
            if (parameters.isZoomSupported()) {
                if (scale <= 1.0f) {
                    parameters.setZoom(0);
                    return 1.0f;
                }
                int i = (int) ((scale - 1.0f) * 100.0f);
                if (i < parameters.getMaxZoom()) {
                    parameters.setZoom(i);
                    mCamera.getCamera().setParameters(parameters);
                    return scale;
                }
                parameters.setZoom(parameters.getMaxZoom());
                mCamera.getCamera().setParameters(parameters);
                return ((parameters.getMaxZoom() * 1.0f) / 100.0f) + 1.0f;
            }
        }
        return 1.0f;
    }


    public void bX(long j) {
        AutoFocusManager aVar = this.mAutoFocusManager;
        aVar.bW(j);
    }


    /**
     * Convenience method for
     *
     * @param newSetting if {@code true}, light should be turned on if currently off. And vice versa.
     */
    public void setTorch(boolean newSetting) {
        synchronized (this) {
            OpenCamera theCamera = mCamera;
            if (!(theCamera == null
                    || newSetting == configManager.getTorchState(theCamera.getCamera()))) {
                boolean wasAutoFocusManager = mAutoFocusManager != null;
                if (wasAutoFocusManager) {
                    mAutoFocusManager.stop();
                    mAutoFocusManager = null;
                }
                configManager.setTorch(theCamera.getCamera(), newSetting);
                if (wasAutoFocusManager) {
                    mAutoFocusManager = new AutoFocusManager(mContext, theCamera.getCamera());
                    mAutoFocusManager.start();
                }
            }
        }
    }

    /**
     * Closes the camera driver if still in use.
     */
    public void closeDriver() {
        synchronized (this) {
            if (mCamera != null) {
                mCamera.getCamera().release();
                // Make sure to clear these each time we close the camera, so that any scanning rect
                // requested by intent is forgotten.
                mCamera = null;
                framingRect = null;
                framingRectInPreview = null;
            }
        }
    }


    /**
     * Calculates the framing rect which the UI should draw to show the user where to place the
     * barcode. This target helps with alignment as well as forces the user to hold the device
     * far enough away to ensure the image will be in focus.
     *
     * @return The rectangle to draw on screen in window coordinates.
     */
    public Rect getFramingRect() {
        synchronized (this) {
            if (framingRect == null) {
                if (mCamera == null) {
                    return null;
                }
                Point screenResolution = configManager.getScreenResolution();
                if (screenResolution == null) {
                    return null;
                }

                int width = findDesiredDimensionInRange(screenResolution.x, MIN_FRAME_WIDTH, MAX_FRAME_WIDTH);
                // TODO: 2022/3/29 qwyelink modify height
                int height = findDesiredDimensionInRange(screenResolution.x, MIN_FRAME_HEIGHT, MAX_FRAME_HEIGHT);

                int leftOffset = (screenResolution.x - width) / 2;
                int topOffset = (screenResolution.y - height) / 3;
                framingRect = new Rect(leftOffset, topOffset, width + leftOffset, height + topOffset);
                StringBuilder sb = new StringBuilder();
                sb.append("Calculated framing rect: ");
                sb.append(framingRect);
                Log.e("QwyElink", sb.toString());
            }
            return framingRect;
        }
    }


    /**
     * Like {@link #getFramingRect} but coordinates are in terms of the preview frame,
     * not UI / screen.
     *
     * @return {@link Rect} expressing barcode scan area in terms of the preview size
     */
    public Rect getFramingRectInPreview() {
        synchronized (this) {
            if (this.framingRectInPreview == null) {
                Rect framingRect = getFramingRect();
                if (framingRect == null) {
                    return null;
                }
                Rect rect = new Rect(framingRect);
                Point cameraResolution = this.configManager.getCameraResolution();
                Point screenResolution = this.configManager.getScreenResolution();
                if (cameraResolution == null || screenResolution == null) {
                    return null;
                }
                rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
                rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
                rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
                rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
                this.framingRectInPreview = rect;
            }
            return this.framingRectInPreview;
        }
    }


    public boolean isOpen() {
        boolean isOpen;
        synchronized (this) {
            isOpen = this.mCamera != null;
        }
        return isOpen;
    }

    /**
     * Opens the camera driver and initializes the hardware parameters.
     *
     * @param surfaceHolder The surface object which the camera will draw preview frames into.
     * @throws IOException Indicates the camera driver failed to open.
     */
    public void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this) {
            OpenCamera theCamera = mCamera;
            if (theCamera == null) {
                //通过OpenCameraInterface打开摄像头
                theCamera = OpenCameraInterface.open(requestedCameraId);
                if (theCamera != null) {
                    mCamera = theCamera;
                } else {
                    throw new IOException("Camera.open() failed to return object from driver");
                }
            }
            //初始化执行的操作
            if (!this.initialized) {
                this.initialized = true;
                //初始化相机的参数，选择最佳的预览分辨率，配置画面预览方向
                this.configManager.initFromCameraParameters(theCamera);
                if (this.requestedFramingRectWidth > 0 && this.requestedFramingRectHeight > 0) {
                    setManualFramingRect(this.requestedFramingRectWidth, this.requestedFramingRectHeight);
                    this.requestedFramingRectWidth = 0;
                    this.requestedFramingRectHeight = 0;
                }
            }
            Camera cameraObject = theCamera.getCamera();
            Camera.Parameters parameters = cameraObject.getParameters();
            // Save these, temporarily
            String flatten = parameters == null ? null : parameters.flatten();
            try {
                //设置必要的参数，包括焦点，闪光灯等
                this.configManager.setDesiredCameraParameters(theCamera, false);
            } catch (RuntimeException unused) {
                // Driver failed
                Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
                StringBuilder sb = new StringBuilder();
                sb.append("Resetting to saved camera params: ");
                sb.append(flatten);
                Log.i(TAG, sb.toString());
                if (flatten != null) {
                    Camera.Parameters parameters2 = cameraObject.getParameters();
                    parameters2.unflatten(flatten);
                    try {
                        cameraObject.setParameters(parameters2);
                        this.configManager.setDesiredCameraParameters(theCamera, true);
                    } catch (RuntimeException unused2) {
                        Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                    }
                }
            }
            //设置摄像头预览控件载体
            cameraObject.setPreviewDisplay(surfaceHolder);
        }
    }


    /**
     * A single preview frame will be returned to the handler supplied. The data will arrive as byte[]
     * in the message.obj field, with width and height encoded as message.arg1 and message.arg2,
     * respectively.
     *
     * @param handler The handler to send the message to.
     * @param message The what field of the message to be sent.
     */
    public void requestPreviewFrame(Handler handler, int message) {
        synchronized (this) {
            OpenCamera theCamera = mCamera;
            Log.e("QwyZxing", "theCamera != null && previewing  : " + (theCamera != null && previewing));
            if (theCamera != null && previewing) {
                //传decodeThread的handler到previewCallback
                previewCallback.setHandler(handler, message);
                //请求camera的一帧预览画面
                //通过camera的setOneShotPreviewCallback方法，请求camera的一帧预览画面，并传入了previewCallback回调对象。
                theCamera.getCamera().setOneShotPreviewCallback(previewCallback);
            }
        }
    }

    /**
     * Allows third party apps to specify the scanning rectangle dimensions, rather than determine
     * them automatically based on screen resolution.
     *
     * @param width  The width in pixels to scan.
     * @param height The height in pixels to scan.
     */
    public void setManualFramingRect(int width, int height) {

        synchronized (this) {
            if (initialized) {
                Point screenResolution = configManager.getScreenResolution();
                if (width > screenResolution.x) {
                    width = screenResolution.x;
                }
                if (height > screenResolution.y) {
                    height = screenResolution.y;
                }
                int leftOffset = (screenResolution.x - width) / 2;
                int topOffset = (screenResolution.y - height) / 2;
                framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);

                StringBuilder sb = new StringBuilder();
                sb.append("QwyCalculated manual framing rect: ");
                sb.append(framingRect);
                Log.d(TAG, sb.toString());
                framingRectInPreview = null;
            } else {
                requestedFramingRectWidth = width;
                requestedFramingRectHeight = height;
            }
        }

    }


    /**
     * Asks the camera hardware to begin drawing preview frames to the screen.
     */
    public void startPreview() {
        synchronized (this) {
            OpenCamera theCamera = mCamera;
            Log.d("QwyZxing", "startPreview theCamera != null && !previewing  : " + (theCamera != null && !previewing));
            Log.d("QwyZxing", "startPreview theCamera != null  : " + (theCamera != null));
            Log.d("QwyZxing", "startPreview !previewing  : " + (!previewing));
            if (theCamera != null && !previewing) {
                theCamera.getCamera().startPreview();
                previewing = true;
                mAutoFocusManager = new AutoFocusManager(mContext, theCamera.getCamera());
            }
        }

    }

    /**
     * Tells the camera to stop drawing preview frames.
     */
    public void stopPreview() {
        synchronized (this) {
            if (mAutoFocusManager != null) {
                mAutoFocusManager.stop();
                mAutoFocusManager = null;
            }
            if (mCamera != null && previewing) {
                mCamera.getCamera().stopPreview();
                previewCallback.setHandler(null, 0);
                previewing = false;
            }
        }
    }


}
