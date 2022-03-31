package com.qwy.chapter_01;

import android.app.Application;

import com.qwy.qrcode.d;
import com.qwy.qrcode.firebase.FirebaseType;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

//        KdweiboApplication
        d.getInstance().addQrCode(new FirebaseType()); //FirebaseQRCodeProcessor
//        d.getInstance().addQrCode(new a());  //ZbarQRCodeProcessor
//        d.getInstance().addQrCode(new d.b()); //ZXingQRCodeProcessor1-GlobalHistogramBinarizer
//        d.getInstance().addQrCode(new QrCodeType()); //ZXingQRCodeProcessor2-BaseZXingQRCodeProcessor
//        d.getInstance().addBitmap(new qrtype.d()); //


    }
}
