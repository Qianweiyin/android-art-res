package com.qwy.chapter_01.qrCode;

import android.graphics.Bitmap;

import com.google.zxing.ELinkProcessType;
import com.google.zxing.TodoResult;
import com.google.zxing.client.android.camera.CameraManager;

public interface InterfaceA {

    class C0521a {
        private CameraManager caK;

        public C0521a(CameraManager cameraManager) {
            this.caK = cameraManager;
        }

        public CameraManager MK() {
            return this.caK;
        }
    }

    TodoResult a(ELinkProcessType processType, Bitmap bitmap);

    TodoResult a(ELinkProcessType processType, byte[] bArr, int i, int i2, C0521a aVar);

    String getName();

}
