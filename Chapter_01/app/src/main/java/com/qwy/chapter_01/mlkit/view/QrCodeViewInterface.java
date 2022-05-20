package com.qwy.chapter_01.mlkit.view;



public interface QrCodeViewInterface {

    /**
     * add扫描点
     *
     * @return
     */
    boolean addPoint();

    void drawViewfinder();

    /**
     * 扫码边角颜色
     *
     * @return
     */
    int getCornerColor();

    /**
     * 扫描区域边框颜色
     *
     * @return
     */
    int getFrameLineColor();

    long getDelayMilliseconds();

    int getMaskColor();

    int getOpaque();

    int getPointColor();

    int getResultColor();

//    String getScanText();

//    int getScanTextColor();

    /**
     * 扫描线颜色
     */
    int getLaserColor();

//    int getScanTextLeft();

//    int getScanTextSize();

}
