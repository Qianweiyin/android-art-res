package com.qwy.qrcode;

public class Result {

    private final String text;
    private final QRCodeFormat qrCodeFormat;

    public Result(String text, QRCodeFormat qrCodeFormat) {
        this.text = text;
        this.qrCodeFormat = qrCodeFormat;
    }

    public String getText() {
        return text;
    }

    public QRCodeFormat getQrCodeFormat() {
        return qrCodeFormat;
    }

    public String getFormat() {
        return qrCodeFormat.name();
    }
}
