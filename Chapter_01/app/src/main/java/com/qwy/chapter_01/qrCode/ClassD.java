package com.qwy.chapter_01.qrCode;

import com.google.zxing.BarcodeFormat;

import java.util.EnumSet;
import java.util.Set;

public final class ClassD {
    public static final Set cbb = EnumSet.of(BarcodeFormat.QR_CODE);
    public static final Set cbc = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    public static final Set caZ = EnumSet.of(BarcodeFormat.UPC_A, BarcodeFormat.EAN_13, BarcodeFormat.EAN_8, BarcodeFormat.RSS_14, BarcodeFormat.RSS_EXPANDED);
    public static final Set cba = EnumSet.of(BarcodeFormat.CODE_39, BarcodeFormat.CODE_93, BarcodeFormat.CODE_128, BarcodeFormat.ITF, BarcodeFormat.CODABAR);
}
