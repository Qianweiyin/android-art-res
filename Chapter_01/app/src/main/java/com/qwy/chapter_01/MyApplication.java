package com.qwy.chapter_01;

import android.app.Application;

import com.qwy.qrcode.d;
import com.qwy.qrcode.qrtype.QrCodeType;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


//        d.btv().handleQrCode(new com.yunzhijia.qrcode.firebase.a());
//        d.btv().addQrCode(new a());  //ZbarQRCodeProcessor
//        d.btv().addQrCode(new d.b()); //ZXingQRCodeProcessor1-GlobalHistogramBinarizer
        d.btv().addQrCode(new QrCodeType());
//        d.btv().addBitmap(new qrtype.d());


    }
}
