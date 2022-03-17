package com.google.zxing;


/**
 * public class e {
 *     private final QRCodeFormat gEs;
 *     private final String text;
 */
public class TodoResult {

    private final BarcodeFormat barcodeFormat;
    private final String text;

    public TodoResult(String str, BarcodeFormat barcodeFormat) {
        this.text = str;
        this.barcodeFormat = barcodeFormat;
    }

    public BarcodeFormat getBarcodeFormat() {
        return barcodeFormat;
    }

    public String getFormat() {
        return barcodeFormat.name();
    }

    public String getText() {
        return this.text;
    }

}
