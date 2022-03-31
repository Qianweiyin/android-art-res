
/*
 * Copyright (C) 2010 ZXing authors
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


import com.qwy.chapter_01.R;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.Result;
import com.qwy.qrcode.VisionImageProcessor;
import com.qwy.qrcode.CameraInputInfo;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


/**
 * final class e extends Handler {
 * private static final String TAG = "e";
 * private final h.a caS;
 * private final ProcessType cbd;
 * private boolean running = true;
 * private boolean cbe = false;
 */
final class DecodeHandler extends Handler {

    private static final String TAG = "QwyZxing";
    private final CaptureActivityInterface captureActivityInterface;
    private final ProcessType processType;


    public DecodeHandler(CaptureActivityInterface captureActivityInterface, ProcessType processType) {
        this.captureActivityInterface = captureActivityInterface;
        this.processType = processType;
    }


    /**
     * Decode the data within the viewfinder rectangle, and time how long it took. For efficiency,
     * reuse the same reader objects from one decode to the next.
     *
     * @param data   The YUV preview frame.
     * @param width  The width of the preview frame.
     * @param height The height of the preview frame.
     */
    private void decode(byte[] data, int width, int height) {


        Message message ;
        long currentTimeMillis = System.currentTimeMillis();
        Log.d("QwyZxing", "data.length : " + (data.length));

        Result result = null;
        try {
            // 这里需要将获取的data翻转一下，因为相机默认拿的的横屏的数据
            byte[] bytes = new byte[data.length];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    bytes[(((j * height) + height) - i) - 1] = data[(i * width) + j];
                }
            }
            result = CameraInputInfo.getInstance().handleQrCode(
                    processType,
                    bytes,
                    height,
                    width,
                    new VisionImageProcessor.C0521a(captureActivityInterface.getCameraManager()));

            Log.d("QwyZxing", "result == null : " + (result == null));


            if (result != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("QwyDecode: QRCodeProcessHelper ");
                sb.append(CameraInputInfo.getInstance().getName());
                sb.append("|result=");
                sb.append(result.getText());
                Log.d("QwyZxing", sb.toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        long currentTimeMillis2 = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("spend:");
        long times = currentTimeMillis2 - currentTimeMillis;
        sb2.append(times);
        Log.d(TAG, sb2.toString());

        Handler handler = captureActivityInterface.getHandler();
        if (result != null) {
            Log.d(TAG, "Found barcode in " + times + " ms; format: " + result.getFormat());
            if (handler != null) {
                message = Message.obtain(handler, R.id.decode_succeeded, result);
            } else {
                return;
            }
        } else if (handler != null) {
            message = Message.obtain(handler, R.id.decode_failed);
        } else {
            return;
        }
        message.sendToTarget();
    }


    @Override
    public void handleMessage(Message message) {
        Log.e("QwyZxing", "message.what  : " + message.what);
        Log.e("QwyZxing", "decode  : " + R.id.decode);
        Log.e("QwyZxing", "decode_failed  : " + R.id.decode_failed);
        Log.e("QwyZxing", "quit  : " + R.id.quit);
        Log.e("QwyZxing", "decode_succeeded  : " + R.id.decode_succeeded);
        Log.e("QwyZxing", "launch_product_query  : " + R.id.launch_product_query);


        if (message.what == R.id.decode) {
            decode((byte[]) message.obj, message.arg1, message.arg2);
        } else if (message.what == R.id.quit) {
            Looper.myLooper().quit();
        }
        Message msg = Message.obtain(captureActivityInterface.getHandler(), R.id.scan_flow_data);
        msg.arg1 = message.arg1;
        msg.arg2 = message.arg2;
        msg.obj = message.obj;
        msg.sendToTarget();
    }


}

