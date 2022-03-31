package com.qwy.scan.preview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;

import com.qwy.chapter_01.R;
import com.qwy.qrcode.view.QrCodeForegroundView;


public class QrCodeForegroundPreview extends QrCodeForegroundView {
    private Context context;

    public QrCodeForegroundPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    @Override
    public boolean addPoint() {
        return true;
    }

    /**
     * 扫码边角颜色
     *
     * @return
     */
    @Override
    public int getCornerColor() {
        return resources.getColor(R.color.c_ffffffff);
    }

    public int getCornerWidth() {
        return 1;
    }


    /**
     * 扫码框四个边颜色
     *
     * @return
     */
    @Override
    public int getFrameLineColor() {
        return resources.getColor(R.color.c_ffffffff);
    }

    /**
     * ANIMATION_DELAY
     *
     * @return
     */
    @Override
    public long getDelayMilliseconds() {
        return 10L;
    }

    /**
     * @return
     */

    @Override
    public int getMaskColor() {
        return this.resources.getColor(R.color.c_00000000);
    }

    /**
     * OPAQUE
     *
     * @return
     */
    @Override
    public int getOpaque() {
        return 0xFF;
    }

    @Override
    public int getPointColor() {
        return resources.getColor(R.color.c_ffff00);
    }

    @Override
    public int getResultColor() {
        return resources.getColor(R.color.teal_700);
    }

    @Override
    public String getScanText() {
        return resources.getString(R.string.scan_tip);
    }

    @Override
    public int getScanTextColor() {
        return getResources().getColor(R.color.c_ffffffff);
    }

    @Override
    public int getLaserColor() {
        return getResources().getColor(R.color.c_ffffffff);
    }

    @Override
    public int getScanTextLeft() {
        return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2;
    }

    @Override
    public int getScanTextSize() {
        return (int) resources.getDimension(R.dimen.y40);
    }

    public String getScanType() {
        return "zxing";
    }
}