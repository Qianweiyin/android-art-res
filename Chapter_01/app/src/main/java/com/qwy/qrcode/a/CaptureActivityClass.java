package com.qwy.qrcode.a;

import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.CaptureActivityHandler;
import com.google.zxing.CaptureActivityInterface;
import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.ProcessType;

public class CaptureActivityClass implements CaptureActivityInterface {
    private CaptureActivityInterface.a mCaptureInterface;
    private CaptureActivityHandler mCaptureActivityHandler;

    public CaptureActivityClass(CameraManager cameraManager, CaptureActivityInterface.a captureInterface) {
        Log.e("QwyZxing", "constructor 2 ");

        mCaptureInterface = captureInterface;
        mCaptureActivityHandler = new CaptureActivityHandler(captureInterface, ProcessType.ALL, cameraManager);
    }

    public CaptureActivityClass(CameraManager cameraManager, String type, a captureInterface) {
        Log.e("QwyZxing", "constructor 1 mType   : " + type);

        mCaptureInterface = captureInterface;
        //这个handler负责扫码流程的所有状态的传递
        mCaptureActivityHandler = new CaptureActivityHandler(captureInterface,
                TextUtils.equals(type, "qrCode")
                        ? ProcessType.QR_CODE : TextUtils.equals(type, "barCode")
                        ? ProcessType.BAR_CODE : ProcessType.ALL, cameraManager);
    }


    public void quitSynchronously() {
        CaptureActivityInterface.a captureInterface = mCaptureInterface;
        if (captureInterface != null) {
            captureInterface.animatorEnd();
        }
        CaptureActivityHandler captureActivityHandler = this.mCaptureActivityHandler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
        }
    }

    public void btC() {
        CaptureActivityHandler captureActivityHandler = this.mCaptureActivityHandler;
        if (captureActivityHandler != null) {
            captureActivityHandler.sendEmptyMessageDelayed(9, 0L);
        }
    }

    public CaptureActivityHandler getCaptureActivityHandler() {
        return mCaptureActivityHandler;
    }

    public void btE() {
        Log.e("QwyZxing", "btE");

        if (mCaptureActivityHandler == null) {
            start();
        }
        a captureInterface = mCaptureInterface;
        if (captureInterface != null) {
            captureInterface.animatorStart();
        }
        CaptureActivityHandler captureActivityHandler = mCaptureActivityHandler;
        if (captureActivityHandler != null) {
            captureActivityHandler.resume();
        }
    }


    public void start() {
        Log.e("QwyZxing", "start");
        mCaptureActivityHandler.start();
        mCaptureInterface.animatorStart();
    }
}
