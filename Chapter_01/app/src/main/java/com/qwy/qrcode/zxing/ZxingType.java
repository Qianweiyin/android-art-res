package com.qwy.qrcode.zxing;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.qwy.qrcode.VisionImageProcessor;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.Result;

public class ZxingType extends a {


    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        return super.handleBitmap(processType, bitmap);
    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] bArr, int i, int i2, VisionImageProcessor.C0521a aVar) {
        return super.handleQrCode(processType, bArr, i, i2, aVar);
    }

    /**
     * Given an image source, convert to a binary bitmap.
     * <p>
     * Override this to use a custom binarizer.
     *
     * @param luminanceSource the image source
     * @return a BinaryBitmap
     */
    @Override
    BinaryBitmap toBitmap(LuminanceSource luminanceSource) {
        return new BinaryBitmap(new GlobalHistogramBinarizer(luminanceSource));
    }

    @Override
    public String getName() {
        return "ZXingQRCodeProcessor2-BaseZXingQRCodeProcessor";
    }
}
