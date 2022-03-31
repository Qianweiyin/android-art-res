package com.google.zxing;

import android.os.Handler;

import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.Result;

public interface CaptureActivityInterface {

    CameraManager getCameraManager();

    default void handleDecode(Result result) {
    }

    default void drawViewfinder() {
    }

    Handler getHandler();

    default void onPreviewData(int width, int height, byte[] data) {
    }

}
