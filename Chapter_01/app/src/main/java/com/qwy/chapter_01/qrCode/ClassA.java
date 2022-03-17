package com.qwy.chapter_01.qrCode;

import android.graphics.Bitmap;
import android.media.Image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ELinkInterfaceB;
import com.google.zxing.ELinkProcessType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.TodoResult;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.nio.charset.StandardCharsets;
import java.util.Hashtable;

public class ClassA implements ELinkInterfaceB {


    @Override
    public Bitmap a(String str, int i, int i2, Bitmap bitmap, String str2) {
        Bitmap bitmap2;
        if (str == null) {
            return null;
        }
//        try {
//        } catch (WriterException e) {
//            e.printStackTrace();
//            bitmap2 = null;
//        }
        if ("".equals(str) || str.length() < 1) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, str2);
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix a2 = null;
        try {
            a2 = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[] iArr = new int[i * i2];
        int i3 = i / 2;
        int i4 = i2 / 2;
        int width = bitmap != null ? bitmap.getWidth() / 2 : 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        boolean z = true;
        boolean z2 = true;
        while (i5 < i2) {
            int i8 = i7;
            int i9 = i6;
            for (int i10 = 0; i10 < i; i10++) {
                if (width > 0 && i10 > i3 - width && i10 < i3 + width && i5 > i4 - width && i5 < i4 + width) {
                    iArr[(i5 * i) + i10] = bitmap.getPixel((i10 - i3) + width, (i5 - i4) + width);
                } else if (a2.get(i10, i5)) {
                    if (z) {
                        i8 = i5;
                        i9 = i10;
                        z = false;
                        z2 = false;
                    }
                    iArr[(i5 * i) + i10] = -16777216;
                }
            }
            i5++;
            i6 = i9;
            i7 = i8;
            z = z;
            z2 = z2;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        if (i6 <= 0 || i7 <= 0) {
            return createBitmap;
        }
        bitmap2 = Bitmap.createBitmap(createBitmap, i6, i7, i - (i6 * 2), i2 - (i7 * 2));
        return bitmap2;
    }
}
