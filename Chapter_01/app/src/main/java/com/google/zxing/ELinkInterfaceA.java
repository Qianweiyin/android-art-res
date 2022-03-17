package com.google.zxing;

import android.graphics.Bitmap;

import com.google.zxing.client.android.camera.CameraManager;

public interface ELinkInterfaceA {

    public class C0521a {
        private CameraManager caK;

        public C0521a(CameraManager cameraManager) {
            this.caK = cameraManager;
        }

        public CameraManager MK() {
            return this.caK;
        }
    }

    TodoResult getELinkResult(ELinkProcessType processType, Bitmap bitmap);

    TodoResult getELinkResult(ELinkProcessType processType, byte[] bArr, int i, int i2, C0521a aVar);

    String getName();

}
