package com.google.zxing;

import java.util.EnumSet;
import java.util.Set;

public final class DecodeFormatManager {

    public static final Set<BarcodeFormat> QR_CODE_FORMATS = EnumSet.of(BarcodeFormat.QR_CODE);
    public static final Set<BarcodeFormat> DATA_MATRIX_FORMATS = EnumSet.of(BarcodeFormat.DATA_MATRIX);
    public static final Set<BarcodeFormat> PRODUCT_FORMATS =
            EnumSet.of(BarcodeFormat.UPC_A,
                    BarcodeFormat.EAN_13,
                    BarcodeFormat.EAN_8,
                    BarcodeFormat.RSS_14,
                    BarcodeFormat.RSS_EXPANDED);
    public static final Set<BarcodeFormat> ONE_D_FORMATS =
            EnumSet.of(BarcodeFormat.CODE_39,
                    BarcodeFormat.CODE_93,
                    BarcodeFormat.CODE_128,
                    BarcodeFormat.ITF,
                    BarcodeFormat.CODABAR);

}
