package com.qwy.chapter_01;

import android.app.Application;

import com.google.zxing.ELinkClassA;
import com.google.zxing.ELinkInterfaceA;
import com.qwy.chapter_01.qrCode.ClassA;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        ELinkClassA.btv().addA(new ClassA());
        ELinkClassA.btv().addB(new ClassA());

    }
}
