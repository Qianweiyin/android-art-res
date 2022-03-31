package com.google.zxing;

import com.google.zxing.camera.CameraManager;

public interface QrCodeViewInterface {

    /**
     * add扫描点
     *
     * @return
     */
    boolean addPoint();

    void drawViewfinder();

    int getCornerColor();

    int getFrameLineColor();

    long getDelayMilliseconds();

    int getMaskColor();

    int getOpaque();

    int getPointColor();

    int getResultColor();

    String getScanText();

    int getScanTextColor();

    /**
     * 扫描线颜色
     */
    int getLaserColor();

    int getScanTextLeft();

    int getScanTextSize();

    void setCameraManager(CameraManager cameraManager);
}
