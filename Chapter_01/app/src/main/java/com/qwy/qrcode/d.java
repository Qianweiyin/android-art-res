package com.qwy.qrcode;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class d implements InterfaceA, InterfaceB {
    private static final String TAG = "QwyZxing";
    private static d gEp;
    private List<InterfaceA> gEq = new ArrayList();
    private List<InterfaceB> gEr = new ArrayList();
    //    private String processName = CookieSpecs.DEFAULT;
    private String processName = "default";

    public static d btv() {
        if (gEp == null) {
            gEp = new d();
        }
        return gEp;
    }

    @Override
    public Bitmap getBitmap(String str, int i, int i2, Bitmap bitmap, String str2) {
        Log.d(TAG, "encode: ");
        for (InterfaceB bVar : this.gEr) {
            Bitmap mBitmap = bVar.getBitmap(str, i, i2, bitmap, str2);
            if (mBitmap != null) {
                return mBitmap;
            }
        }
        return null;
    }

    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        Log.d(TAG, "decode: bitmap start ");
        for (InterfaceA aVar : this.gEq) {
            Log.d(TAG, "decode: bitmap start " + aVar.getName());
            Result result = aVar.handleBitmap(processType, bitmap);
            if (result != null) {
                this.processName = aVar.getName();
                Log.d(TAG, "decode: bitmap success : " + this.processName + " | " + result.getText());
                return result;
            }
        }
        Log.d(TAG, "decode: bitmap error ");
        return null;
    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] bArr, int width, int height, InterfaceA.C0521a aVar) {
        Log.d(TAG, "decode: data");
        for (InterfaceA aVar2 : this.gEq) {
            Log.d(TAG, "decode: data start " + aVar2.getName());
            Result result = aVar2.handleQrCode(processType, bArr, width, height, aVar);
            Log.e(TAG, "decode:  null == result " + (null == result));

            if (result != null) {
                this.processName = aVar2.getName();
                Log.d(TAG, "decode: data success : " + this.processName + " | " + result.getText());
                return result;
            }
        }
        Log.d(TAG, "decode: data error ");
        return null;
    }

    public void addQrCode(InterfaceA aVar) {
        this.gEq.add(aVar);
    }

    public void addBitmap(InterfaceB bVar) {
        this.gEr.add(bVar);
    }

    @Override
    public String getName() {
        return this.processName;
    }
}
