package com.qwy.qrcode.mlkit;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.qwy.qrcode.VisionImageProcessor;
import com.qwy.qrcode.ProcessType;
import com.qwy.qrcode.QRCodeFormat;
import com.qwy.qrcode.Result;

import java.util.List;


public class FirebaseType implements VisionImageProcessor {

    private BarcodeScanner getProcessType(ProcessType processType) {
//        Log.e("QwyFirebase", "processType.name() : " + processType.name());
        return processType == ProcessType.BAR_CODE ? getBarCodeScanner()
                : processType == ProcessType.QR_CODE ? getQrCodeScanner()
                : btx();
    }

    private Result handleResult(ProcessType processType, InputImage inputImage) {
        BarcodeScanner barcodeScanner = getProcessType(processType);
        Result result = null;
        Log.e("QwyFirebase", "firebase null == barcodeScanner : " + (null == barcodeScanner));
        if (barcodeScanner != null) {
            Task<List<Barcode>> listTask = barcodeScanner.process(inputImage);
            boolean isCanceled;
            do {
                isCanceled = listTask.isCanceled();

                if (isCanceled) {
                    return null;
                }
                isCanceled = listTask.isComplete();

            } while (!isCanceled);


            List<Barcode> barcodeList = listTask.getResult();

            if (barcodeList != null) {
                isCanceled = barcodeList.isEmpty();
                Log.e("QwyFirebase", "isCanceled  : " + (isCanceled));

                if (!isCanceled) {
                    Barcode barcode = barcodeList.get(0);
                    String rawValue = barcode.getRawValue();
                    int format = barcode.getFormat();

                    Log.e("QwyFirebase", "firebase rawValue : " + rawValue);
                    Log.e("QwyFirebase", "firebase format : " + format);

                    if (!TextUtils.isEmpty(rawValue)) {
                        result = new Result(rawValue, getQrCodeFormat(format));
                        Log.e("QwyFirebase", "firebase result : " + new Gson().toJson(result));
                    }

                }
            }
        }

        return result;

    }

    private BarcodeScanner btx() {
        return BarcodeScanning
                .getClient(new BarcodeScannerOptions
                        .Builder()
                        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS, new int[0])
                        .build());
    }

    private BarcodeScanner getQrCodeScanner() {
        BarcodeScannerOptions barcodeScannerOptions =
                new BarcodeScannerOptions
                        .Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_QR_CODE,
                                Barcode.FORMAT_DATA_MATRIX)
                        .build();
        return BarcodeScanning.getClient(barcodeScannerOptions);
    }

    private BarcodeScanner getBarCodeScanner() {
        return BarcodeScanning
                .getClient(new BarcodeScannerOptions
                        .Builder()
                        .setBarcodeFormats(
                                Barcode.FORMAT_CODABAR,
                                512, 64, 32, 2, 4, 1)
                        .build());
    }

    private QRCodeFormat getQrCodeFormat(int format) {
        Log.e("QwyFirebaseCodeFormat", "firebase getQrCodeFormat format : " + format);
        switch (format) {
            case Barcode.FORMAT_CODE_128: //1
                return QRCodeFormat.CODE_128;
            case Barcode.FORMAT_CODE_39: // 2
                return QRCodeFormat.CODE_39;
            case Barcode.FORMAT_CODE_93: //4
                return QRCodeFormat.CODE_93;
            case Barcode.FORMAT_CODABAR: //8
                return QRCodeFormat.CODABAR;
            case Barcode.FORMAT_DATA_MATRIX: //16
                return QRCodeFormat.DATA_MATRIX;
            case Barcode.FORMAT_EAN_13: //32
                return QRCodeFormat.EAN_13;
            case Barcode.FORMAT_EAN_8: //64
                return QRCodeFormat.EAN_8;
            case Barcode.FORMAT_ITF: //128
                return QRCodeFormat.ITF;
            case Barcode.FORMAT_QR_CODE: //256
                return QRCodeFormat.QR_CODE;
            case Barcode.FORMAT_UPC_A: //512
                return QRCodeFormat.UPC_A;
            case Barcode.FORMAT_UPC_E: //1024
                return QRCodeFormat.UPC_E;
            case Barcode.FORMAT_PDF417: //2048
                return QRCodeFormat.PDF_417;
            case Barcode.FORMAT_AZTEC://4096
                return QRCodeFormat.AZTEC;
            default:
                return QRCodeFormat.UNKNOWN;
        }
    }


    @Override
    public Result handleBitmap(ProcessType processType, Bitmap bitmap) {
        Log.e("QwyFirebase", "firebase handleBitmap");
        return handleResult(processType, InputImage.fromBitmap(bitmap, 0));
    }

    /**
     * @param processType
     * @param byteArray   the content of the image
     * @param width       the width of the image
     * @param height      the height of the image
     * @param aVar
     * @return
     */

    @Override
    public Result handleQrCode(ProcessType processType, byte[] byteArray, int width, int height, C0521a aVar) {
        Log.e("QwyFirebase", "firebase handleQrCode");

        //  rotationDegrees (the image's counter-clockwise orientation degrees. Only 0, 90, 180, 270 are supported.
        //  IllegalArgumentException will be thrown if the input degree is not in the list.)


        //the image format. The supported formats are IMAGE_FORMAT_NV21 and IMAGE_FORMAT_YV12.
        // For IMAGE_FORMAT_YUV_420_888, please use fromMediaImage(Image, int).
        return handleResult(processType, InputImage.fromByteArray(byteArray, width, height, 0, InputImage.IMAGE_FORMAT_NV21));
    }

    @Override
    public String getName() {
        return "FirebaseQRCodeProcessor";
    }


}
