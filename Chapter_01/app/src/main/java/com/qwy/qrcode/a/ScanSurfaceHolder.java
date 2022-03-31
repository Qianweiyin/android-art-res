package com.qwy.qrcode.a;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.google.zxing.AmbientLightManager;
import com.google.zxing.CaptureActivityInterface;
import com.google.zxing.InactivityTimer;
import com.google.zxing.QrCodeViewInterface;
import com.google.zxing.ScanMediaPlayer;
import com.google.zxing.SurfaceHolderInterface;
import com.google.zxing.camera.CameraManager;
import com.qwy.qrcode.Result;

import java.io.IOException;

public class ScanSurfaceHolder implements SurfaceHolder.Callback, CaptureActivityInterface {
    private static final String TAG = "ScanSurfaceHolder";
    private Activity activity;
    private CameraManager mCameraManager;
    private AmbientLightManager ambientLightManager;
    private CaptureActivityClass mCaptureActivityClass;
    private SurfaceView surfaceView;
    private QrCodeViewInterface mQrCodeViewInterface;
    private SurfaceHolderInterface mSurfaceHolderInterface;
    private InactivityTimer inactivityTimer;
    private ScanMediaPlayer mScanMediaPlayer;
    private boolean hasSurface = false;
    private SurfaceHolder mSurfaceHolder;
    private String mType;

    public ScanSurfaceHolder(Activity activity,
                             SurfaceView surfaceView,
                             QrCodeViewInterface qrCodeViewInterface,
                             SurfaceHolderInterface surfaceHolderInterface,
                             String type) {
        this.activity = activity;
        this.surfaceView = surfaceView;
        this.mQrCodeViewInterface = qrCodeViewInterface;
        this.mSurfaceHolderInterface = surfaceHolderInterface;
        this.mType = type;
        initParams();
    }

    private void initParams() {
        inactivityTimer = new InactivityTimer(activity);
        mScanMediaPlayer = new ScanMediaPlayer(activity);
        ambientLightManager = new AmbientLightManager(activity);
    }


    @Override
    public CameraManager getCameraManager() {
        return mCameraManager;
    }

    @Override
    public void handleDecode(Result result) {
        Log.e("QwyZxing", "handleDecode result ");

        inactivityTimer.onActivity();
        mScanMediaPlayer.playBeepSoundAndVibrate();
        mSurfaceHolderInterface.handleDecode(result);
    }

    @Override
    public void onPreviewData(int width, int height, byte[] data) {
        mSurfaceHolderInterface.onPreviewData(width, height, data);
    }

    public float onScale(float scale) {
        return mCameraManager.onScale(scale);
    }

    public void btE() {
        if (mCaptureActivityClass != null) {
            mCaptureActivityClass.btE();
        }
    }


    public void quitSynchronously() {
        if (mCaptureActivityClass != null) {
            mCaptureActivityClass.quitSynchronously();
        }
    }

    public void initCamera() {

        Log.e("QwyZxing", "mType : " + mType);


        mCaptureActivityClass = !TextUtils.isEmpty(mType)
                ? new CaptureActivityClass(mCameraManager, mType, this)
                : new CaptureActivityClass(mCameraManager, this);

        if (mSurfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (mCameraManager.isOpen()) {
            Log.e("QwyZxing", "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                //打开摄像头
                mCameraManager.openDriver(mSurfaceHolder);
                Log.e("QwyZxing", "initCamera 2  ");
                mCaptureActivityClass.start();
                mSurfaceHolderInterface.start();
                mCameraManager.startPreview();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("QwyZxing", e.getMessage());
            } catch (RuntimeException e2) {
                e2.printStackTrace();
                Log.e("QwyZxing", "Unexpected error initializing camera", e2);
                mSurfaceHolderInterface.initCameraFail();
            }
        }
    }

    @Override
    public void drawViewfinder() {
        Log.e("QwyZxing", "ScanSurfaceHolder drawViewfinder");
        mQrCodeViewInterface.drawViewfinder();
    }

    @Override
    public Handler getHandler() {
        return mCaptureActivityClass.getCaptureActivityHandler();
    }

    public void setTorch(boolean on) {
        mCameraManager.setTorch(on);
    }

    public void pause() {
        inactivityTimer.onPause();
        ambientLightManager.stop();
        mScanMediaPlayer.close();
        mCameraManager.closeDriver();
        if (!hasSurface && mSurfaceHolder != null) {
            mSurfaceHolder.removeCallback(this);
        }
    }

    public void reScan() {
        if (mCaptureActivityClass != null) {
            mCaptureActivityClass.btC();
        }
    }

    public void resume() {
        mCameraManager = new CameraManager(activity);
        mQrCodeViewInterface.setCameraManager(mCameraManager);
        mScanMediaPlayer.ME();
        ambientLightManager.registerLightSensor(mCameraManager);
        inactivityTimer.onResume();
        mSurfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera();
        } else {
            mSurfaceHolder.addCallback(this);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
        if (!hasSurface) {
            hasSurface = true;
            mSurfaceHolderInterface.initCamera();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.hasSurface = false;
    }
}
