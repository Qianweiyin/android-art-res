package com.qwy.qrcode;

import android.graphics.Bitmap;

import com.google.zxing.camera.CameraManager;

/**
 * An interface to process the images with different vision detectors and custom image models.
 */
public interface VisionImageProcessor {
    class C0521a {
        private CameraManager mCameraManager;

        public C0521a(CameraManager cameraManager) {
            mCameraManager = cameraManager;
        }

        public CameraManager getCameraManager() {
            return mCameraManager;
        }
    }

    Result handleBitmap(ProcessType processType, Bitmap bitmap);

    /**
     * @param processType
     * @param byteArray
     * @param width
     * @param height
     * @param aVar
     * @return
     */
    Result handleQrCode(ProcessType processType, byte[] byteArray, int width, int height, C0521a aVar);

    String getName();
}