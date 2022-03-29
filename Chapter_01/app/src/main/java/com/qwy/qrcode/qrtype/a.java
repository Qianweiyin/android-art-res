package com.qwy.qrcode.qrtype;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.ReaderException;
import com.google.zxing.DecodeFormatManager;
import com.qwy.qrcode.InterfaceA;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.QRCodeFormat;
import com.qwy.qrcode.Result;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

abstract class a implements InterfaceA {


    static final int[] gEE = new int[ProcessType.values().length];
    static final int[] gED;

    static {
        try {
            gEE[ProcessType.QR_CODE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
            unused.printStackTrace();
        }
        try {
            gEE[ProcessType.BAR_CODE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
            unused2.printStackTrace();
        }
        gED = new int[BarcodeFormat.values().length];
        try {
            gED[BarcodeFormat.QR_CODE.ordinal()] = 1;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            gED[BarcodeFormat.ITF.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            gED[BarcodeFormat.AZTEC.ordinal()] = 3;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            gED[BarcodeFormat.EAN_8.ordinal()] = 4;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            gED[BarcodeFormat.UPC_A.ordinal()] = 5;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            gED[BarcodeFormat.UPC_E.ordinal()] = 6;
        } catch (NoSuchFieldError unused8) {
        }
        try {
            gED[BarcodeFormat.EAN_13.ordinal()] = 7;
        } catch (NoSuchFieldError unused9) {
        }
        try {
            gED[BarcodeFormat.RSS_14.ordinal()] = 8;
        } catch (NoSuchFieldError unused10) {
        }
        try {
            gED[BarcodeFormat.CODABAR.ordinal()] = 9;
        } catch (NoSuchFieldError unused11) {
        }
        try {
            gED[BarcodeFormat.CODE_39.ordinal()] = 10;
        } catch (NoSuchFieldError unused12) {
        }
        try {
            gED[BarcodeFormat.CODE_93.ordinal()] = 11;
        } catch (NoSuchFieldError unused13) {
        }
        try {
            gED[BarcodeFormat.PDF_417.ordinal()] = 12;
        } catch (NoSuchFieldError unused14) {
        }
        try {
            gED[BarcodeFormat.CODE_128.ordinal()] = 13;
        } catch (NoSuchFieldError unused15) {
        }
        try {
            gED[BarcodeFormat.MAXICODE.ordinal()] = 14;
        } catch (NoSuchFieldError unused16) {
        }
        try {
            gED[BarcodeFormat.DATA_MATRIX.ordinal()] = 15;
        } catch (NoSuchFieldError unused17) {
        }
        try {
            gED[BarcodeFormat.RSS_EXPANDED.ordinal()] = 16;
        } catch (NoSuchFieldError unused18) {
        }
        try {
            gED[BarcodeFormat.UPC_EAN_EXTENSION.ordinal()] = 17;
        } catch (NoSuchFieldError unused19) {
        }
    }


    /**
     * A factory method to build the appropriate LuminanceSource object based on the format
     * of the preview buffers, as described by Camera.Parameters.
     */
    private PlanarYUVLuminanceSource buildLuminanceSource(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < height; i2++) {
            int i3 = i2 * width;
            for (int i4 = 0; i4 < width; i4++) {
                int i5 = i3 + i4;
                int i6 = iArr[i5];
                int i7 = (i6 >> 16) & 255;
                int i8 = (i6 >> 8) & 255;
                int i9 = i6 & 255;
                if (i7 == i8 && i8 == i9) {
                    bArr[i5] = (byte) i7;
                } else {
                    bArr[i5] = (byte) ((((i7 + i8) + i8) + i9) >> 2);
                }
            }
        }
        return new PlanarYUVLuminanceSource(bArr, width, height, 0, 0, width, height, false);
    }

    private QRCodeFormat a(BarcodeFormat barcodeFormat) {
        switch (barcodeFormat) {
            case QR_CODE:
                return QRCodeFormat.QR_CODE;
            case ITF:
                return QRCodeFormat.ITF;
            case AZTEC:
                return QRCodeFormat.AZTEC;
            case EAN_8:
                return QRCodeFormat.EAN_8;
            case UPC_A:
                return QRCodeFormat.UPC_A;
            case UPC_E:
                return QRCodeFormat.UPC_E;
            case EAN_13:
                return QRCodeFormat.EAN_13;
            case RSS_14:
                return QRCodeFormat.RSS_14;
            case CODABAR:
                return QRCodeFormat.CODABAR;
            case CODE_39:
                return QRCodeFormat.CODE_39;
            case CODE_93:
                return QRCodeFormat.CODE_93;
            case PDF_417:
                return QRCodeFormat.PDF_417;
            case CODE_128:
                return QRCodeFormat.CODE_128;
            case MAXICODE:
                return QRCodeFormat.MAXICODE;
            case DATA_MATRIX:
                return QRCodeFormat.DATA_MATRIX;
            case RSS_EXPANDED:
                return QRCodeFormat.RSS_EXPANDED;
            case UPC_EAN_EXTENSION:
                return QRCodeFormat.UPC_EAN_EXTENSION;
            default:
                return QRCodeFormat.UNKNOWN;
        }
    }


    // private e a(Map map, l lVar) {
    private Result a(Map<DecodeHintType, Object> hints, LuminanceSource luminanceSource) {
        MultiFormatReader multiFormatReader = new MultiFormatReader();
        multiFormatReader.setHints(hints);
        try {
            try {
                com.google.zxing.Result zxingResult = multiFormatReader.decodeWithState(b(luminanceSource));
                Result result = new Result(zxingResult.getText(), a(zxingResult.getBarcodeFormat()));
                multiFormatReader.reset();
                return result;
            } catch (ReaderException e) {
                e.printStackTrace();
                multiFormatReader.reset();
                return null;
            }
        } catch (Throwable th) {
            multiFormatReader.reset();
            throw th;
        }
    }


    private Map<DecodeHintType, Object> b(ProcessType processType) {
        Set<BarcodeFormat> set;
        Set<BarcodeFormat> set2;
        Map<DecodeHintType, Object> enumMap = new EnumMap<>(DecodeHintType.class);
        Set<BarcodeFormat> decodeFormats = EnumSet.noneOf(BarcodeFormat.class);
        int i = gEE[processType.ordinal()];
        if (i == 1) {
            set = DecodeFormatManager.QR_CODE_FORMATS;
        } else if (i != 2) {
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.PRODUCT_FORMATS);
            set = DecodeFormatManager.ONE_D_FORMATS;
        } else {
            decodeFormats.addAll(DecodeFormatManager.PRODUCT_FORMATS);
            set2 = DecodeFormatManager.ONE_D_FORMATS;
            decodeFormats.addAll(set2);
            enumMap.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
            return enumMap;
        }
        decodeFormats.addAll(set);
        set2 = DecodeFormatManager.DATA_MATRIX_FORMATS;
        decodeFormats.addAll(set2);
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        return enumMap;
    }


    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        return a(b(processType), buildLuminanceSource(bitmap));
    }

    @Override
    public Result handleQrCode(ProcessType processType, byte[] data, int width, int height, a.C0521a aVar) {
        Map<DecodeHintType, Object> b = b(processType);
        Rect rect = aVar.getCameraManager().getFramingRectInPreview();
        Log.d("QwyZxing", "rect == null : " + (null == rect));
        Log.d("QwyZxing", "data == null : " + (null == data));

        if (rect == null) {
            return null;
        }
        //对于目前CPU性能过剩的大多数智能手机来说，这种裁剪显得没有必要。
        // 如果把解码数据换成采用全幅图像数据，这样在识别的过程中便不再拘束于聚焦框，
        // 也使得二维码数据可以铺满整个屏幕。这样用户在使用程序来扫描二维码时，
        // 尽管不完全对准聚焦框，也可以识别出来。这属于一种策略上的让步，给用户造成了错觉，提高了识别的精度。
        // 解决办法很简单，就是不仅仅使用聚焦框里的图像数据，而是采用全幅图像的数据。
        return a(b,
                new PlanarYUVLuminanceSource(
                        data,
                        width,
                        height,
                        rect.left,
                        rect.top,
                        rect.width(),
                        rect.height(),
                        false));
    }

    abstract BinaryBitmap b(LuminanceSource luminanceSource);

}
