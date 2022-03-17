package com.google.zxing;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.google.zxing.client.android.CaptureActivityInterface;
import com.google.zxing.client.android.camera.CameraManager;

import java.io.IOException;

public class ScanSurfaceHolder implements SurfaceHolder.Callback, CaptureActivityInterface.a {
    private static final String TAG = "ScanSurfaceHolder";
    private Activity activity;
    private CameraManager mCameraManager;
    private AmbientLightManager ambientLightManager;
    private CaptureActivityClass mCaptureActivityClass;
    private ObjectAnimator objectAnimator;
    private SurfaceView surfaceView;
    private QrCodeViewInterface mQrCodeViewInterface;
    private View view;
    private SurfaceHolderInterface mSurfaceHolderInterface;
    private InactivityTimer inactivityTimer;
    private ScanMediaPlayer mScanMediaPlayer;
    private boolean hasSurface = false;
    private SurfaceHolder mSurfaceHolder;
    private String mType;

    public ScanSurfaceHolder(Activity activity,
                             SurfaceView surfaceView,
                             QrCodeViewInterface qrCodeViewInterface,
                             View view,
                             SurfaceHolderInterface surfaceHolderInterface,
                             String type) {
        this.activity = activity;
        this.surfaceView = surfaceView;
        this.mQrCodeViewInterface = qrCodeViewInterface;
        this.view = view;
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
    public void animatorStart() {
        objectAnimator.start();
    }

    @Override
    public void animatorEnd() {
        ObjectAnimator objectAnimator;
        if (activity != null
                && !activity.isFinishing()
                && (objectAnimator = this.objectAnimator) != null) {
            objectAnimator.end();
        }
    }

    @Override
    public CameraManager getCameraManager() {
        return mCameraManager;
    }

    @Override
    public void handleDecode(TodoResult result) {
        Log.e("QwyZxing", "handleDecode result ");

        inactivityTimer.onActivity();
        mScanMediaPlayer.playBeepSoundAndVibrate();
        mSurfaceHolderInterface.handleDecode(result);
    }

    public float onScale(float scale) {
        return mCameraManager.onScale(scale);
    }

    public void btE() {
        CaptureActivityClass captureActivityClass = mCaptureActivityClass;
        if (captureActivityClass != null) {
            captureActivityClass.btE();
        }
    }

    protected void btJ() {
        Rect framingRect = mCameraManager.getFramingRect();
        if (framingRect == null) {
            view.setVisibility(View.GONE);
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMargins(
                marginLayoutParams.leftMargin,
                framingRect.top,
                marginLayoutParams.rightMargin,
                marginLayoutParams.bottomMargin);
        view.setLayoutParams(marginLayoutParams);
        objectAnimator = ObjectAnimator.ofFloat(
                view,
                "translationY",
                0.0f,
                (framingRect.bottom - framingRect.top) - 12);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setDuration(3000L);
    }

    public void btK() {
        CaptureActivityClass aVar = mCaptureActivityClass;
        if (aVar != null) {
            aVar.btB();
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

                btJ();
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

    public void setTorch(boolean z) {
        mCameraManager.setTorch(z);
    }

    public void pause() {
        SurfaceHolder surfaceHolder;
        inactivityTimer.onPause();
        ambientLightManager.stop();
        mScanMediaPlayer.close();
        mCameraManager.closeDriver();
        if (!this.hasSurface
                && (surfaceHolder = mSurfaceHolder) != null) {
            surfaceHolder.removeCallback(this);
        }
    }

    public void reScan() {
        CaptureActivityClass captureActivityClass = mCaptureActivityClass;
        if (captureActivityClass != null) {
            captureActivityClass.btC();
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
