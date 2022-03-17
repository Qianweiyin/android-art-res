package com.google.zxing;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * public class e {
 *     public static a BD(String str) {
 *         return new b(str);
 *     }
 */
public class ELinkClassE {

    public static ELinkInterfaceC BD(String str) {
        return new ELinkClassB(str);
    }

    public static ELinkInterfaceC a(Context context, TodoResult eVar) {
        return a(context, eVar, "");
    }

    public static ELinkInterfaceC a(Context context, TodoResult eVar, String str) {
        return new ELinkClassH(eVar, context, str);
    }

    public static ELinkInterfaceC a(Bitmap bitmap, ELinkInterfaceD cVar) {
        return new ELinkClassB(bitmap, cVar);
    }

    public static ELinkInterfaceC aF(Context context, String str) {
        return a(context, new TodoResult(str, BarcodeFormat.QR_CODE));
    }

    public static ELinkInterfaceC b(Context context, int i, int i2, String str, Bitmap bitmap) {
        return new ELinkClassJ(context, i, i2, str, bitmap);
    }

    public static ELinkInterfaceC x(Context context, String str, String str2) {
        return new ELinkClassK(context, str, str2);
    }
}
