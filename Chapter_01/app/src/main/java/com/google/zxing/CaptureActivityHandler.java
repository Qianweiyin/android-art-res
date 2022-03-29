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

package com.google.zxing;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.zxing.camera.CameraManager;
import com.qwy.chapter_01.R;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.Result;

/**
 * This class handles all the messaging which comprises the state machine for capture.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 */
public final class CaptureActivityHandler extends Handler {

    private static final String TAG = "CaptureActivityHandler";
    private final CameraManager cameraManager;
    private final CaptureActivityInterface.a captureActivityInterface;
    private final DecodeThread decodeThread;
    private State state;


    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }


    public CaptureActivityHandler(CaptureActivityInterface.a captureActivityInterface, ProcessType processType, CameraManager cameraManager) {
        this.captureActivityInterface = captureActivityInterface;
        //开启一条解码线程
        this.decodeThread = new DecodeThread(captureActivityInterface, processType);
        this.cameraManager = cameraManager;
    }


    /**
     * 开始预览并解码
     */
    private void restartPreviewAndDecode() {
        Log.e("QwyZxing", "restartPreviewAndDecode state : " + state);

        if (state == State.SUCCESS) {
            state = State.PREVIEW;
            //请求摄像头的一帧图像数据,注意这里传入的是decodeThread的handler
            cameraManager.requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
            //重绘扫码框控件
            captureActivityInterface.drawViewfinder();
        }
    }


    @Override
    public void handleMessage(Message message) {

        int what = message.what;
        Log.e("QwyZxing", "what : " + what);
        if (what == R.id.decode_failed) { // 4
            // We're decoding as fast as possible, so when one decode fails, start another.
            //解码失败
            state = State.PREVIEW;
            //重新取一帧图像并执行解码
            cameraManager.requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
        } else if (what == R.id.decode_succeeded) { //5
            state = State.SUCCESS;
            this.captureActivityInterface.handleDecode((Result) message.obj);
        } else if (what == R.id.restart_preview) {  //9
            //重新取一帧图像并执行解码
            restartPreviewAndDecode();
        }
    }

    public void quitSynchronously() {
        state = State.DONE;
        cameraManager.stopPreview();
        Message.obtain(decodeThread.getHandler(), R.id.quit).sendToTarget();   //   R.id.quit  8
        try {
            // Wait at most half a second; should be enough time, and onPause() will timeout quickly
            decodeThread.join(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            // continue
        }

        // Be absolutely sure we don't send any queued up messages
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }


    public void resume() {
        state = State.DONE;
        Log.e("QwyZxing", " R.id.quit : " + R.id.quit);
        Log.e("QwyZxing", " R.id.decode : " + R.id.decode);
        Message.obtain(decodeThread.getHandler(), 3).sendToTarget();
        cameraManager.requestPreviewFrame(decodeThread.getHandler(), 1);
    }

    public void start() {
        decodeThread.start();
        state = State.SUCCESS;
        Log.e("QwyZxing", "startPreview");
        //启动摄像头预览
        cameraManager.startPreview();
        restartPreviewAndDecode();
    }
}
