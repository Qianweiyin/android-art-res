package com.qwy.qrcode;

import android.graphics.Bitmap;

public interface InputInfo {
    Bitmap getBitmap(String str, int width, int height, Bitmap bitmap, String str2);
}
