package com.qwy.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.camera.CameraManager;

public interface InterfaceA {
    class C0521a {
        private CameraManager mCameraManager;

        public C0521a(CameraManager cameraManager) {
            this.mCameraManager = cameraManager;
        }

        public CameraManager getCameraManager() {
            return this.mCameraManager;
        }
    }

    Result handleBitmap(ProcessType processType, Bitmap bitmap);

    Result handleQrCode(ProcessType processType, byte[] bArr, int width, int height, C0521a aVar);

    String getName();
}