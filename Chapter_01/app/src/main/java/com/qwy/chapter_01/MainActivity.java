package com.qwy.chapter_01;

import android.animation.Animator;
import android.animation.ObjectAnimator;
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
import com.qwy.scan.a.InterfaceB;
import com.qwy.scan.preview.QrCodeForegroundPreview;
import com.qwy.videogo.scan.main.FinishListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SurfaceHolderInterface, InterfaceB.a {


    ScanSurfaceHolder scanSurfaceHolder;
    private ScaleGestureDetector scaleGestureDetector;
    private int mode = 0;

    private float gHy;
    private float gHz;
    private float mPosX;
    private int gHD = 0;
    private String gHo;
    private LinearLayout ll_light;
    private ImageView iv_qrcode_light_open;
    private TextView tv_qrcode_light_text;

    private boolean gHw;

    private QrCodeForegroundPreview qr_viewfinder_view;
    private SurfaceView surface_view;
    private ImageView iv_scan_bar;


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


//    private void Bq(String str) {
//        ab.aqf().ab(this, d.np(R.string.recognizing));
//        e.x(this, str, this.gHo).a(new com.yunzhijia.scan.c.c() { // from class: com.yunzhijia.scan.CameraFetureBizActivity.1
//            @Override // com.yunzhijia.scan.c.c
//            public void afY() {
//            }
//
//            @Override // com.yunzhijia.scan.c.c
//            public void k(int i, Object obj) {
//                ab.aqf().aqg();
//            }
//
//            @Override // com.yunzhijia.scan.c.c
//            public void kc(String str2) {
//                ab.aqf().aqg();
//                CameraFetureBizActivity cameraFetureBizActivity = CameraFetureBizActivity.this;
//                as.a(cameraFetureBizActivity, cameraFetureBizActivity.getString(R.string.toast_2));
//            }
//        });
//    }


    @Override
    public void buD() {
//        this.gHL.start();
    }

    @Override
    public void buE() {

    }

    @Override
    public int[] but() {
        return new int[]{this.surface_view.getWidth(), this.surface_view.getHeight()};
    }

    @Override
    public void buu() {

    }


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        qr_viewfinder_view = (QrCodeForegroundPreview) findViewById(R.id.qr_viewfinder_view);
        surface_view = (SurfaceView) findViewById(R.id.surface_view);
        iv_scan_bar = (ImageView) findViewById(R.id.iv_scan_bar);
        ll_light = (LinearLayout) findViewById(R.id.ll_light);
        iv_qrcode_light_open = (ImageView) findViewById(R.id.iv_qrcode_light_open);
        tv_qrcode_light_text = (TextView) findViewById(R.id.tv_qrcode_light_text);


        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());


        scanSurfaceHolder = new ScanSurfaceHolder(
                this,
                surface_view,
                qr_viewfinder_view,
                iv_scan_bar,
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

        scanQrCode();


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
                this.iv_qrcode_light_open.setImageResource(R.mipmap.qrcode_scan_light_close);
                textView = this.tv_qrcode_light_text;
                string = getResources().getString(R.string.qrcode_scan_light_close);
            } else {
                this.iv_qrcode_light_open.setImageResource(R.mipmap.qrcode_scan_light_open);
                textView = this.tv_qrcode_light_text;
                string = getResources().getString(R.string.qrcode_scan_light_open);
            }
            textView.setText(string);
//            au.traceEvent("scan_flashlight", z ? "打开" : "关闭");
            scanSurfaceHolder.setTorch(isOpen);
            gHw = isOpen;
        } catch (Exception unused) {
//            as.a(this, d.np(R.string.qrcode_scan_open_flashlight_error));
            Toast.makeText(this, "qrcode_scan_open_flashlight_error", Toast.LENGTH_SHORT).show();
        }
    }


//    public static void a(Activity activity, int i, int i2, String str) {
//        Intent intent = new Intent(activity, MainActivity.class);
//        intent.putExtra("from_type", i2);
//        if (str != null && !ELinkClassT.mG(str)) {
//            intent.putExtra("title_bar_name", str);
//        }
//        intent.putExtra("mode_tag", 1);
//        activity.startActivityForResult(intent, i);
//    }

    /**
     * 扫码回调 Handler scan result
     *
     * @param result
     */
    @Override
    public void handleDecode(Result result) {
        Log.e("QwyZxing", "result");

//        ELinkClassE.a(this, result, gHo)
//                .a(new ELinkInterfaceD() {
//                    @Override
//                    public void afY() {
//                        scanSurfaceHolder.reScan();
//                    }
//
//                    @Override
//                    public void k(int i, Object obj) {
//
//                    }
//
//                    @Override
//                    public void kc(String str) {
//                        Toast.makeText(MainActivity.this, getString(R.string.toast_1), Toast.LENGTH_LONG).show();
//                    }
//                });
    }

    @Override
    public void initCamera() {
        Log.e("QwyZxing", "initCamera");
        scanSurfaceHolder.initCamera();
    }

    @Override
    public void start() {
        Log.e("QwyZxing", "start");
    }

    @Override
    public void initCameraFail() {
        Log.e("QwyZxing", "initCameraFail");
        displayFrameworkBugMessageAndExit();

    }


    private void scanQrCode() {
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(qr_viewfinder_view, "alpha", 0.0f, 1.0f);
        ofFloat2.setDuration(200L);
        ofFloat2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
                if (gHD == 0) {
                    iv_scan_bar.setVisibility(View.VISIBLE);
                    scanSurfaceHolder.btE();
                }
            }
        });

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


    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.btn_capture /* 2131296720 */:
//                this.gHA.buH();
//                return;

            //照明
            case R.id.iv_qrcode_light_open:
                clickQtCodeLight(!this.gHw);
                return;
//            case R.id.tv_card /* 2131301772 */:
//                if (this.gHD != 1) {
//                    buy();
//                    this.gHD = 1;
//                    return;
//                }
//                return;
//            case R.id.tv_code /* 2131301812 */:
//                if (this.gHD != 0) {
//                    bux();
//                    this.gHD = 0;
//                    return;
//                }
//                return;
            default:
                return;
        }
    }


    @Override
    public void onPause() {
        Log.e("QwyZxing", "onPause");
        int i = this.mode;
        if (i == 0 || i == 2) {
            this.scanSurfaceHolder.quitSynchronously();
        }
        this.scanSurfaceHolder.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanSurfaceHolder.resume();
    }


}
