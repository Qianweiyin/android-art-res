package com.qwy.qrcode.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.zxing.QrCodeViewInterface;
import com.google.zxing.camera.CameraManager;
import com.qwy.scan.ResultPoint;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 二维码扫描界面
 */
public abstract class QrCodeForegroundView extends View implements QrCodeViewInterface {
    protected CameraManager mCameraManager;
    private Context context;
    private Bitmap resultBitmap;
    protected Resources resources = getResources();
    private Paint paint;
    private CopyOnWriteArrayList<ResultPoint> possibleResultPoints;
    private CopyOnWriteArrayList<ResultPoint> lastPossibleResultPoints;

    public static int scannerStart = 0;
    public static int scannerEnd = 0;
    //扫描线宽度
    private static final int SCANNER_LINE_HEIGHT = 10;
    //扫描线移动距离
    private static final int SCANNER_LINE_MOVE_DISTANCE = 5;


    private static final int CORNER_RECT_WIDTH = 8;
    private static final int CORNER_RECT_HEIGHT = 40;


    public QrCodeForegroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;

        paint = new Paint();
        paint.setAntiAlias(true);
        possibleResultPoints = new CopyOnWriteArrayList<>();

    }


    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override
    public void drawViewfinder() {
        Log.e("QwyZxing", "ScanSurfaceHolder drawViewfinder 222 ");
        resultBitmap = null;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        Rect framingRect;
        CameraManager cameraManager = mCameraManager;
        if (!(cameraManager == null || (framingRect = cameraManager.getFramingRect()) == null)) {

            if (scannerStart == 0 || scannerEnd == 0) {
                scannerStart = framingRect.top;
                scannerEnd = framingRect.bottom;
            }

            int width = getWidth();
            int height = getHeight();
            // 绘制模糊区域 Draw the exterior (i.e. outside the framing rect) darkened
            drawExterior(canvas, framingRect, width, height);

            if (resultBitmap != null) {
                paint.setAlpha(getOpaque());
                canvas.drawBitmap(resultBitmap, framingRect.left, framingRect.top, paint);
                return;
            }
            canvas.drawRect(framingRect.left, framingRect.top, framingRect.right, framingRect.top, paint);
            canvas.drawRect(framingRect.left, framingRect.top, framingRect.left, framingRect.bottom, paint);
            canvas.drawRect(framingRect.right, framingRect.top, framingRect.right, framingRect.bottom, paint);
            canvas.drawRect(framingRect.left, framingRect.bottom, framingRect.right, framingRect.bottom, paint);
            paint.setColor(getFrameLineColor());
            canvas.drawRect(framingRect.left, framingRect.top, framingRect.right, framingRect.top + 1, paint);
            canvas.drawRect(framingRect.left, framingRect.top, framingRect.left + 1, framingRect.bottom, paint);
            canvas.drawRect(framingRect.left, framingRect.bottom - 1, framingRect.right, framingRect.bottom, paint);
            canvas.drawRect(framingRect.right - 1, framingRect.top, framingRect.right, framingRect.bottom, paint);


            //绘制边角
            drawCorner(canvas, framingRect);

            //绘制扫描线
            drawLaserScanner(canvas, framingRect);


            paint.setTextSize(getScanTextSize());
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getScanTextColor());
            //绘制text
            canvas.drawText(getScanText(), getScanTextLeft(), framingRect.top - dip2px(context, 40.0f), paint);


            //add扫描点
            CopyOnWriteArrayList<ResultPoint> currentPossible = possibleResultPoints;
            CopyOnWriteArrayList<ResultPoint> currentLast = lastPossibleResultPoints;

            if (addPoint()) {
                if (currentPossible.isEmpty()) {
                    lastPossibleResultPoints = null;
                } else {
                    lastPossibleResultPoints = currentPossible;
                    paint.setAlpha(getOpaque());
                    paint.setColor(getPointColor());
                    for (ResultPoint point : currentPossible) {
                        canvas.drawCircle(framingRect.left + point.getX(), framingRect.top + point.getY(), 6.0f, paint);
                    }
                }
                if (currentLast != null) {
                    paint.setAlpha(getOpaque() / 2);
                    paint.setColor(getPointColor());
                    for (ResultPoint point : currentLast) {
                        canvas.drawCircle(framingRect.left + point.getX(), framingRect.top + point.getY(), 3.0f, paint);
                    }
                }
            }


            // Request another update at the animation interval, but only repaint the laser line,
            // not the entire viewfinder mask.
            //指定重绘区域，该方法会在子线程中执行
            postInvalidateDelayed(getDelayMilliseconds(), framingRect.left, framingRect.top, framingRect.right, framingRect.bottom);
        }
    }

    //绘制模糊区域
    private void drawExterior(Canvas canvas, Rect framingRect, int width, int height) {
        paint.setColor(resultBitmap != null ? getResultColor() : getMaskColor());
        canvas.drawRect(0.0f, 0.0f, width, framingRect.top, paint);
        canvas.drawRect(0.0f, framingRect.top, framingRect.left, framingRect.bottom, paint);
        canvas.drawRect(framingRect.right, framingRect.top, (float) width, framingRect.bottom, paint);
        canvas.drawRect(0.0f, framingRect.bottom, (float) width, height, paint);
    }

    //绘制边角
    private void drawCorner(Canvas canvas, Rect rect) {
        paint.setColor(getCornerColor());
        //左上
        canvas.drawRect(rect.left, rect.top, rect.left + CORNER_RECT_WIDTH, rect.top + CORNER_RECT_HEIGHT, paint);
        canvas.drawRect(rect.left, rect.top, rect.left + CORNER_RECT_HEIGHT, rect.top + CORNER_RECT_WIDTH, paint);
        //右上
        canvas.drawRect(rect.right - CORNER_RECT_WIDTH, rect.top, rect.right, rect.top + CORNER_RECT_HEIGHT, paint);
        canvas.drawRect(rect.right - CORNER_RECT_HEIGHT, rect.top, rect.right, rect.top + CORNER_RECT_WIDTH, paint);
        //左下
        canvas.drawRect(rect.left, rect.bottom - CORNER_RECT_WIDTH, rect.left + CORNER_RECT_HEIGHT, rect.bottom, paint);
        canvas.drawRect(rect.left, rect.bottom - CORNER_RECT_HEIGHT, rect.left + CORNER_RECT_WIDTH, rect.bottom, paint);
        //右下
        canvas.drawRect(rect.right - CORNER_RECT_WIDTH, rect.bottom - CORNER_RECT_HEIGHT, rect.right, rect.bottom, paint);
        canvas.drawRect(rect.right - CORNER_RECT_HEIGHT, rect.bottom - CORNER_RECT_WIDTH, rect.right, rect.bottom, paint);
    }

    //绘制扫描线
    private void drawLaserScanner(Canvas canvas, Rect rect) {
        paint.setColor(getLaserColor());

        RadialGradient radialGradient = new RadialGradient(
                (float) (rect.left + rect.width() / 2),
                (float) (scannerStart + SCANNER_LINE_HEIGHT / 2),
                360f,
                getLaserColor(),
                shadeColor(getLaserColor()),
                Shader.TileMode.MIRROR);


        paint.setShader(radialGradient);
        if (scannerStart <= scannerEnd) {
            RectF rectF = new RectF(
                    rect.left + 2 * SCANNER_LINE_HEIGHT,
                    scannerStart,
                    rect.right - 2 * SCANNER_LINE_HEIGHT,
                    scannerStart + SCANNER_LINE_HEIGHT);
            canvas.drawOval(rectF, paint);
            scannerStart += SCANNER_LINE_MOVE_DISTANCE;
        } else {
            scannerStart = rect.top;
        }
        paint.setShader(null);
    }


    //处理颜色模糊
    public int shadeColor(int color) {
        String hax = Integer.toHexString(color);
        String result = "20" + hax.substring(2);
        return Integer.valueOf(result, 16);
    }


    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }

    @Override
    public void setCameraManager(CameraManager cameraManager) {
        mCameraManager = cameraManager;
    }
}