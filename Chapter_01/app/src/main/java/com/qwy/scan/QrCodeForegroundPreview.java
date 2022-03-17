package com.qwy.scan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.WindowManager;

import com.qwy.chapter_01.R;


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
        return this.resources.getColor(R.color.c_ffffffff);
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
        return this.resources.getColor(R.color.c_ffffffff);
    }

    /**
     * ANIMATION_DELAY
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
     * @return
     */
    @Override
    public int getOpaque() {
        return 0xFF;
    }

    @Override
    public int getPointColor() {
        return this.resources.getColor(R.color.c_ffff00);
    }

    @Override
    public int getResultColor() {
        return this.resources.getColor(R.color.teal_700);
    }

    @Override
    public String getScanText() {
        return this.resources.getString(R.string.scan_tip);
    }

    @Override
    public int getScanTextColor() {
        return getResources().getColor(R.color.white);
    }

    @Override
    public int getScanTextLeft() {
        return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth() / 2;
    }

    @Override
    public int getScanTextSize() {
        return (int) this.resources.getDimension(R.dimen.y40);
    }

    public String getScanType() {
        return "zxing";
    }
}