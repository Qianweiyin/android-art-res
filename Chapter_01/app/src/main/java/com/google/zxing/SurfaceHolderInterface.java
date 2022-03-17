package com.google.zxing;

public interface SurfaceHolderInterface {

    void handleDecode(TodoResult result);

    void initCamera();

    void start();

    void initCameraFail();
}
