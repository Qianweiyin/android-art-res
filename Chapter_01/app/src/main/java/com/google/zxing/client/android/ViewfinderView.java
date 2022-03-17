/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.zxing.client.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.camera.CameraManager;
import com.qwy.chapter_01.R;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder rectangle and partial
 * transparency outside it, as well as the laser scanner animation and result points.
 *
 * @author allen.huang
 */
public final class ViewfinderView extends View {

    private static final long ANIMATION_DELAY = 10L;
    private static final int OPAQUE = 0xFF;
    /**
     * 扫描区边角的宽
     */
    private static final int CORNER_RECT_WIDTH = 40;
    /**
     * 扫描区边角的高
     */
    private static final int CORNER_RECT_HEIGHT = 40;
    /**
     * 扫描线移动距离
     */
    private static final int SCANNER_LINE_MOVE_DISTANCE = 5;
    /**
     * 扫描线宽度
     */
    private static final int SCANNER_LINE_HEIGHT = 10;

    private Rect mBound;
    private final Paint paint;
    private Bitmap resultBitmap;

    /**
     * 模糊区域颜色
     */
    private final int maskColor;
    private final int resultColor;

    /**
     * 扫描区域边框颜色
     */
    private final int frameColor;

    /**
     * 扫描线颜色
     */
    private final int laserColor;

    /**
     * 四角颜色
     */
    private final int cornerColor;

    /**
     * 扫描点的颜色
     */
    private final int resultPointColor;

    /**
     * 扫描区域提示文本
     */
    private String scanTip;
    /**
     * 扫描区域提示文本颜色
     */
    private final int labelTextColor;
    private final float labelTextSize;

    public static int scannerStart = 0;
    public static int scannerEnd = 0;

    private CopyOnWriteArrayList<ResultPoint> possibleResultPoints;
    private CopyOnWriteArrayList<ResultPoint> lastPossibleResultPoints;
    private CameraManager cameraManager;

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }


    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //初始化自定义属性信息
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewfinderView);
        laserColor = array.getColor(R.styleable.ViewfinderView_laser_color, 0x00FF00);
        cornerColor = array.getColor(R.styleable.ViewfinderView_corner_color, 0x00FF00);
        frameColor = array.getColor(R.styleable.ViewfinderView_frame_color, 0xFFFFFF);
        resultPointColor = array.getColor(R.styleable.ViewfinderView_result_point_color, 0xC0FFFF00);
        maskColor = array.getColor(R.styleable.ViewfinderView_mask_color, 0x60000000);
        resultColor = array.getColor(R.styleable.ViewfinderView_result_color, 0xB0000000);
        labelTextColor = array.getColor(R.styleable.ViewfinderView_label_text_color, 0x90FFFFFF);
        scanTip = array.getString(R.styleable.ViewfinderView_label_text);
        labelTextSize = array.getFloat(R.styleable.ViewfinderView_label_text_size, 36f);

        // Initialize these once for performance rather than calling them every time in onDraw().
        mBound = new Rect();
        paint = new Paint();
        paint.setAntiAlias(true);
        possibleResultPoints = new CopyOnWriteArrayList<>();
    }

    public void setScanTip(String scanTip) {
        this.scanTip = scanTip;
        setTextBounds();
        requestLayout();
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void setTextBounds() {
        paint.getTextBounds(scanTip, 0, scanTip.length(), mBound);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.e("QwyZxing", "onDraw   cameraManager  == null : " + (cameraManager == null));

        if (cameraManager == null) {
            return; // not ready yet, early draw before done configuring
        }
        Rect frame = cameraManager.getFramingRect();
        Log.e("QwyZxing", "onDraw   frame  == null : " + (frame == null));
        if (frame == null) {
            return;
        }
        if (scannerStart == 0 || scannerEnd == 0) {
            scannerStart = frame.top;
            scannerEnd = frame.bottom;
        }

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        // Draw the exterior (i.e. outside the framing rect) darkened
        drawExterior(canvas, frame, width, height);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        } else {
            // Draw a two pixel solid black border inside the framing rect
            drawFrame(canvas, frame);
            // 绘制边角
            drawCorner(canvas, frame);

            // 绘制"laser scanner" line through the middle to show decoding is active
            drawLaserScanner(canvas, frame);

            //绘制提示信息
//            drawTextInfo(canvas, frame);

            CopyOnWriteArrayList<ResultPoint> currentPossible = possibleResultPoints;
            CopyOnWriteArrayList<ResultPoint> currentLast = lastPossibleResultPoints;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new CopyOnWriteArrayList<>();
                lastPossibleResultPoints = currentPossible;
                paint.setAlpha(OPAQUE);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentPossible) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 6.0f, paint);
                }
            }
            if (currentLast != null) {
                paint.setAlpha(OPAQUE / 2);
                paint.setColor(resultPointColor);
                for (ResultPoint point : currentLast) {
                    canvas.drawCircle(frame.left + point.getX(), frame.top + point.getY(), 3.0f, paint);
                }
            }

            // Request another update at the animation interval, but only repaint the laser line,
            // not the entire viewfinder mask.
            //指定重绘区域，该方法会在子线程中执行
            postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top, frame.right, frame.bottom);
        }
    }

    //绘制文本
    private void drawTextInfo(Canvas canvas, Rect frame) {

        if (TextUtils.isEmpty(scanTip)) {
            return;
        }

        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(labelTextColor);
        textPaint.setTextSize(labelTextSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        if (getTextWidth(scanTip, (int) labelTextSize) > canvas.getWidth()) {
            StaticLayout myStaticLayout = new StaticLayout(scanTip, textPaint, frame.width(), Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);
            canvas.translate(frame.left + frame.width() / 2, frame.bottom + CORNER_RECT_HEIGHT * 1.2f);
            myStaticLayout.draw(canvas);
        } else {
            canvas.drawText(scanTip, frame.left + frame.width() / 2, frame.bottom + CORNER_RECT_HEIGHT * 1.2f, paint);
        }
    }

    //绘制边角
    private void drawCorner(Canvas canvas, Rect frame) {
        paint.setColor(cornerColor);
        //左上
        canvas.drawRect(frame.left, frame.top, frame.left + CORNER_RECT_WIDTH, frame.top + CORNER_RECT_HEIGHT, paint);
        canvas.drawRect(frame.left, frame.top, frame.left + CORNER_RECT_HEIGHT, frame.top + CORNER_RECT_WIDTH, paint);
        //右上
        canvas.drawRect(frame.right - CORNER_RECT_WIDTH, frame.top, frame.right, frame.top + CORNER_RECT_HEIGHT, paint);
        canvas.drawRect(frame.right - CORNER_RECT_HEIGHT, frame.top, frame.right, frame.top + CORNER_RECT_WIDTH, paint);
        //左下
        canvas.drawRect(frame.left, frame.bottom - CORNER_RECT_WIDTH, frame.left + CORNER_RECT_HEIGHT, frame.bottom, paint);
        canvas.drawRect(frame.left, frame.bottom - CORNER_RECT_HEIGHT, frame.left + CORNER_RECT_WIDTH, frame.bottom, paint);
        //右下
        canvas.drawRect(frame.right - CORNER_RECT_WIDTH, frame.bottom - CORNER_RECT_HEIGHT, frame.right, frame.bottom, paint);
        canvas.drawRect(frame.right - CORNER_RECT_HEIGHT, frame.bottom - CORNER_RECT_WIDTH, frame.right, frame.bottom, paint);
    }

    //绘制扫描线
    private void drawLaserScanner(Canvas canvas, Rect frame) {
        paint.setColor(laserColor);

        RadialGradient radialGradient = new RadialGradient(
                (float) (frame.left + frame.width() / 2),
                (float) (scannerStart + SCANNER_LINE_HEIGHT / 2),
                360f,
                laserColor,
                shadeColor(laserColor),
                Shader.TileMode.MIRROR);


        paint.setShader(radialGradient);
        if (scannerStart <= scannerEnd) {
            //矩形
//      canvas.drawRect(frame.left, scannerStart, frame.right, scannerStart + SCANNER_LINE_HEIGHT, paint);
            //椭圆
            RectF rectF = new RectF(frame.left + 2 * SCANNER_LINE_HEIGHT, scannerStart, frame.right - 2 * SCANNER_LINE_HEIGHT, scannerStart + SCANNER_LINE_HEIGHT);
            canvas.drawOval(rectF, paint);
            scannerStart += SCANNER_LINE_MOVE_DISTANCE;
        } else {
            scannerStart = frame.top;
        }
        paint.setShader(null);
    }

    //处理颜色模糊
    public int shadeColor(int color) {
        String hax = Integer.toHexString(color);
        String result = "20" + hax.substring(2);
        return Integer.valueOf(result, 16);
    }

    // 绘制扫描区边框 Draw a two pixel solid black border inside the framing rect
    private void drawFrame(Canvas canvas, Rect frame) {
        paint.setColor(frameColor);
        canvas.drawRect(frame.left, frame.top, frame.right + 1, frame.top + 2, paint);
        canvas.drawRect(frame.left, frame.top + 2, frame.left + 2, frame.bottom - 1, paint);
        canvas.drawRect(frame.right - 1, frame.top, frame.right + 1, frame.bottom - 1, paint);
        canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1, frame.bottom + 1, paint);
    }

    // 绘制模糊区域 Draw the exterior (i.e. outside the framing rect) darkened
    private void drawExterior(Canvas canvas, Rect frame, int width, int height) {
        paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);
    }

    public void drawViewfinder() {
        resultBitmap = null;
        invalidate();
    }

    public float getTextWidth(String text, int textSize) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        TextPaint paint = new TextPaint();
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        paint.setTextSize(scaledDensity * textSize);
        return paint.measureText(text);
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live scanning display.
     *
     * @param barcode An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }

}
