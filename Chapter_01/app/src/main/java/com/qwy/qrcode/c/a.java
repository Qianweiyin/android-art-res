package com.qwy.qrcode.c;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qwy.qrcode.InterfaceA;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.Result;

import java.nio.charset.StandardCharsets;

public class a implements InterfaceA {


    private static final String TAG = "a";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean Bg(String str) {
        return str.equals(new String(str.getBytes(), StandardCharsets.UTF_8));
    }


    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        int[] iArr = new int[width * height];
//        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
//        Image image = new Image(width, height, "RGB4");
//        image.setData(iArr);
//        return a(processType, image.convert("Y800"));

        return null;

    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] bArr, int i, int i2, C0521a aVar) {
//        Image image = new Image(i, i2, "Y800");
//        image.setData(bArr);
//        return a(processType, image);
        return null;
    }

    @Override
    public String getName() {
        return "ZbarQRCodeProcessor";
    }
}
