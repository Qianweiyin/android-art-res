package com.qwy.chapter_01;

import android.app.Application;

import com.qwy.qrcode.CameraInputInfo;
import com.qwy.qrcode.mlkit.FirebaseType;
import com.qwy.qrcode.zxing.ZxingType;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

//        KdweiboApplication
        CameraInputInfo.getInstance().addQrCode(new FirebaseType()); //FirebaseQRCodeProcessor
//        d.getInstance().addQrCode(new a());  //ZbarQRCodeProcessor
//        d.getInstance().addQrCode(new d.b()); //ZXingQRCodeProcessor1-GlobalHistogramBinarizer
        CameraInputInfo.getInstance().addQrCode(new ZxingType()); //ZXingQRCodeProcessor2-BaseZXingQRCodeProcessor
//        d.getInstance().addBitmap(new qrtype.d()); //


    }
}
