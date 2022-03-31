package com.qwy.qrcode.a;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.CaptureActivityHandler;
import com.google.zxing.CaptureActivityInterface;
import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.ProcessType;

public class CaptureActivityClass implements CaptureActivityInterface {
    private CaptureActivityHandler mCaptureActivityHandler;

    public CaptureActivityClass(CameraManager cameraManager, CaptureActivityInterface captureInterface) {
        Log.e("QwyZxing", "constructor 2 ");
        mCaptureActivityHandler = new CaptureActivityHandler(captureInterface, ProcessType.ALL, cameraManager);
    }

    public CaptureActivityClass(CameraManager cameraManager, String type, CaptureActivityInterface captureInterface) {
        Log.e("QwyZxing", "constructor 1 mType   : " + type);
        //这个handler负责扫码流程的所有状态的传递
        mCaptureActivityHandler = new CaptureActivityHandler(captureInterface,
                TextUtils.equals(type, "qrCode")
                        ? ProcessType.QR_CODE : TextUtils.equals(type, "barCode")
                        ? ProcessType.BAR_CODE : ProcessType.ALL, cameraManager);
    }


    public void quitSynchronously() {
        if (mCaptureActivityHandler != null) {
            mCaptureActivityHandler.quitSynchronously();
        }
    }

    public void btC() {
        if (mCaptureActivityHandler != null) {
            mCaptureActivityHandler.sendEmptyMessageDelayed(9, 0L);
        }
    }

    public CaptureActivityHandler getCaptureActivityHandler() {
        return mCaptureActivityHandler;
    }

    public void btE() {
        Log.e("QwyZxing", "btE");
        if (mCaptureActivityHandler == null) {
            start();
        } else {
            mCaptureActivityHandler.resume();
        }
    }


    public void start() {
        Log.e("QwyZxing", "start");
        mCaptureActivityHandler.start();
    }

    @Override
    public CameraManager getCameraManager() {
        return null;
    }

    @Override
    public Handler getHandler() {
        return null;
    }
}
