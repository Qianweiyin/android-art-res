package com.qwy.chapter_01;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.SurfaceHolderInterface;
import com.qwy.qrcode.Result;
import com.qwy.qrcode.a.ScanSurfaceHolder;
import com.qwy.scan.preview.QrCodeForegroundPreview;
import com.qwy.videogo.scan.main.FinishListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolderInterface {

    private static final String TAG = "QwyMainActivity";

    ScanSurfaceHolder scanSurfaceHolder;
    private ScaleGestureDetector scaleGestureDetector;

    private LinearLayout ll_light;
    private ImageView iv_qrcode_light_open;
    private TextView tv_qrcode_light_text;

    private boolean isFlashOn;

    private QrCodeForegroundPreview qr_viewfinder_view;
    private SurfaceView surface_view;


    /**
     * 手势处理
     */
    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private float mScaleFactor;

        private ScaleListener() {
            this.mScaleFactor = 1.0f;
        }

        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            scanSurfaceHolder.onScale((scaleGestureDetector.getScaleFactor() + mScaleFactor) - 1.0f);
            return false;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            super.onScaleEnd(scaleGestureDetector);
            this.mScaleFactor = (scaleGestureDetector.getScaleFactor() + this.mScaleFactor) - 1.0f;
            this.mScaleFactor = scanSurfaceHolder.onScale(mScaleFactor);
        }
    }


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        qr_viewfinder_view = (QrCodeForegroundPreview) findViewById(R.id.qr_viewfinder_view);
        surface_view = (SurfaceView) findViewById(R.id.surface_view);
        ll_light = (LinearLayout) findViewById(R.id.ll_light);
        iv_qrcode_light_open = (ImageView) findViewById(R.id.iv_qrcode_light_open);
        tv_qrcode_light_text = (TextView) findViewById(R.id.tv_flash);


        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        scanSurfaceHolder = new ScanSurfaceHolder(
                this,
                surface_view,
                qr_viewfinder_view,
                this,
                "qrCode");


        surface_view.performClick();
        surface_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return scaleGestureDetector.onTouchEvent(motionEvent);
            }
        });

        iv_qrcode_light_open.setOnClickListener(this);

//        scanQrCode();
        scanSurfaceHolder.btE();


    }

    /**
     * 手电筒照明
     *
     * @param isOpen
     */
    private void clickQtCodeLight(boolean isOpen) {
        TextView textView;
        String string;
        try {
            if (isOpen) {
                iv_qrcode_light_open.setImageResource(R.mipmap.qrcode_scan_light_close);
                textView = tv_qrcode_light_text;
                string = getResources().getString(R.string.qrcode_scan_light_close);
            } else {
                iv_qrcode_light_open.setImageResource(R.mipmap.qrcode_scan_light_open);
                textView = this.tv_qrcode_light_text;
                string = getResources().getString(R.string.qrcode_scan_light_open);
            }
            textView.setText(string);
            scanSurfaceHolder.setTorch(isOpen);
            isFlashOn = isOpen;
        } catch (Exception unused) {
            Toast.makeText(this, "qrcode_scan_open_flashlight_error", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 扫码回调 Handler scan result
     *
     * @param result
     */
    @Override
    public void handleDecode(Result result) {
        Log.e(TAG, "result");

    }

    @Override
    public void initCamera() {
        Log.e(TAG, "initCamera");
        scanSurfaceHolder.initCamera();
    }

    @Override
    public void start() {
        Log.e(TAG, "start");
    }

    @Override
    public void initCameraFail() {
        Log.e(TAG, "initCameraFail");
        displayFrameworkBugMessageAndExit();

    }

    @Override
    public void onPreviewData(int width, int height, byte[] data) {
        if (width == 0 || height == 0 || data == null) {
            return;
        }
        try {
            int size = width * height;
            int y = 0;
            //数据中采集的点数为100
            for (int i = 0; i < size; i += size / 100) {
                y += data[i] & 0xff;
            }
            int curLux = y / 100;
            boolean isDark = curLux < 100.0f;
            if (isDark) {
                ll_light.setVisibility(View.VISIBLE);
            } else {
                if (!isFlashOn) {   //闪光灯亮的时候，手电筒图标不消失
                    ll_light.setVisibility(View.INVISIBLE);
                } else {
                    ll_light.setVisibility(View.VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("很遗憾，Android 相机出现问题。你可能需要重启设备。");
        builder.setPositiveButton("OK", new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //照明
            case R.id.ll_light:
                clickQtCodeLight(!isFlashOn);
                return;
            default:
                return;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
        scanSurfaceHolder.quitSynchronously();
        scanSurfaceHolder.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanSurfaceHolder.resume();
    }


}
