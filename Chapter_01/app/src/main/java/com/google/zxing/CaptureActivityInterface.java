package com.google.zxing;

import android.os.Handler;

import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.Result;

public interface CaptureActivityInterface {

    default void animatorStart() {
    }


    default void animatorEnd() {
    }

    CameraManager getCameraManager();

    default void handleDecode(Result result) {
    }

    default void drawViewfinder() {
    }

    Handler getHandler();
}
