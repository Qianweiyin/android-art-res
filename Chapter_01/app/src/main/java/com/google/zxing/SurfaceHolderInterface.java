package com.google.zxing;

import com.qwy.qrcode.Result;

public interface SurfaceHolderInterface {

    void handleDecode(Result result);

    void initCamera();

    void start();

    void initCameraFail();
}
