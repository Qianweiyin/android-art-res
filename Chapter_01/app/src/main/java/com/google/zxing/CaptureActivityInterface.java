package com.google.zxing;

import android.os.Handler;

import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.Result;

public interface CaptureActivityInterface {

    interface a {
        void animatorStart();

        void animatorEnd();

        CameraManager getCameraManager();

        void handleDecode(Result result);

        void drawViewfinder();

        Handler getHandler();
    }
}
