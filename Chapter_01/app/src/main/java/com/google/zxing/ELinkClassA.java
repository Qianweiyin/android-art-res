package com.google.zxing;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ELinkClassA implements ELinkInterfaceA, ELinkInterfaceB {
    private static final String TAG = "QwyZxing";
    private static ELinkClassA eLinkClassA;
    private List<ELinkInterfaceA> listA = new ArrayList();
    private List<ELinkInterfaceB> listB = new ArrayList();
    private String processName = ELinkCookieSpecs.DEFAULT;


    public static ELinkClassA btv() {
        if (eLinkClassA == null) {
            eLinkClassA = new ELinkClassA();
        }
        return eLinkClassA;
    }

    @Override
    public Bitmap a(String str, int i, int i2, Bitmap bitmap, String str2) {
        Log.d(TAG, "encode: ");
        for (ELinkInterfaceB bVar : listB) {
            Bitmap a2 = bVar.a(str, i, i2, bitmap, str2);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    @Override
    public TodoResult getELinkResult(ELinkProcessType processType, Bitmap bitmap) {
        Log.d(TAG, "decode: bitmap start ");
        for (ELinkInterfaceA aVar : listA) {
            Log.d(TAG, "decode: bitmap start " + aVar.getName());
            TodoResult a2 = aVar.getELinkResult(processType, bitmap);
            if (a2 != null) {
                this.processName = aVar.getName();
                Log.d(TAG, "decode: bitmap success : " + this.processName + " | " + a2.getText());
                return a2;
            }
        }
        Log.d(TAG, "decode: bitmap error ");
        return null;
    }

    @Override
    public TodoResult getELinkResult(ELinkProcessType processType, byte[] bArr, int i, int i2, C0521a aVar) {
        Log.d(TAG, "decode: data");
        for (ELinkInterfaceA aVar2 : listA) {
            Log.d(TAG, "decode: data start " + aVar2.getName());
            TodoResult todoResult = aVar2.getELinkResult(processType, bArr, i, i2, aVar);
            Log.d(TAG, "(todoResult != null)  :  " + (todoResult != null));

            if (todoResult != null) {
                this.processName = aVar2.getName();
                Log.d(TAG, "decode: data success : " + this.processName + " | " + todoResult.getText());
                return todoResult;
            }
        }
        Log.d(TAG, "decode: data error ");
        return null;
    }


    public void addA(ELinkInterfaceA aVar) {
        this.listA.add(aVar);
    }

    public void addB(ELinkInterfaceB bVar) {
        this.listB.add(bVar);
    }

    @Override
    public String getName() {
        return null;
    }


}
