package com.qwy.qrcode.qrtype;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.qwy.qrcode.InterfaceA;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.Result;

public class QrCodeType extends a {


    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        return super.handleBitmap(processType, bitmap);
    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] bArr, int i, int i2, InterfaceA.C0521a aVar) {
        return super.handleQrCode(processType, bArr, i, i2, aVar);
    }

    @Override
    BinaryBitmap b(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource));
    }

    @Override
    public String getName() {
        return "ZXingQRCodeProcessor2-BaseZXingQRCodeProcessor";
    }
}
