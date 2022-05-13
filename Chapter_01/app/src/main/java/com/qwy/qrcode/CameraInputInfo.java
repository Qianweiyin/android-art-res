package com.qwy.qrcode;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CameraInputInfo implements VisionImageProcessor, InputInfo {
    private static final String TAG = "QwyZxing";
    private static CameraInputInfo cameraInputInfo;
    private List<VisionImageProcessor> qrCodeList = new ArrayList();
    private List<InputInfo> bitmapList = new ArrayList();
    private String processName = "default";

    public static CameraInputInfo getInstance() {
        if (cameraInputInfo == null) {
            cameraInputInfo = new CameraInputInfo();
        }
        return cameraInputInfo;
    }

    @Override
    public Bitmap getBitmap(String str, int width, int height, Bitmap bitmap, String str2) {
        Log.d(TAG, "encode: ");
        for (InputInfo bVar : bitmapList) {
            Bitmap mBitmap = bVar.getBitmap(str, width, height, bitmap, str2);
            if (mBitmap != null) {
                return mBitmap;
            }
        }
        return null;
    }

    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        Log.d(TAG, "decode: bitmap start ");
        for (VisionImageProcessor qrCode : qrCodeList) {
            Log.d(TAG, "decode: bitmap start " + qrCode.getName());
            Result result = qrCode.handleBitmap(processType, bitmap);
            if (result != null) {
                processName = qrCode.getName();
                Log.d(TAG, "decode: bitmap success : " + processName + " | " + result.getText());
                return result;
            }
        }
        Log.d(TAG, "decode: bitmap error ");
        return null;
    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] bArr, int width, int height, VisionImageProcessor.C0521a aVar) {
        Log.d(TAG, "decode: data");
        for (VisionImageProcessor qrCode : qrCodeList) {
            Log.d(TAG, "decode: data start " + qrCode.getName());
            Result result = qrCode.handleQrCode(processType, bArr, width, height, aVar);
            Log.e(TAG, "decode:  null == result " + (null == result));

            if (result != null) {
                processName = qrCode.getName();
                Log.d(TAG, "decode: data success : " + processName + " | " + result.getText());
                return result;
            }
        }
        Log.d(TAG, "decode: data error ");
        return null;
    }

    public void addQrCode(VisionImageProcessor aVar) {
        qrCodeList.add(aVar);
    }

    public void addBitmap(InputInfo inputInfo) {
        bitmapList.add(inputInfo);
    }

    @Override
    public String getName() {
        return processName;
    }
}
