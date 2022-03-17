package com.qwy.scan;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.zxing.QrCodeViewInterface;
import com.google.zxing.client.android.camera.CameraManager;

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
            paint.setTextSize(getScanTextSize());
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getScanTextColor());
            canvas.drawText(getScanText(), getScanTextLeft(), framingRect.bottom + dip2px(context, 30.0f), paint);


            //add扫描点
            CopyOnWriteArrayList<ResultPoint> currentPossible = possibleResultPoints;
            CopyOnWriteArrayList<ResultPoint> currentLast = lastPossibleResultPoints;

//            Log.e("QwyPoint", "addPoint :  " + addPoint());

            if (addPoint()) {
//                Log.e("QwyPoint", "currentPossible isEmpty :  " + currentPossible.isEmpty());

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


            //绘制扫描线


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
        canvas.drawRect(rect.left - 10, rect.top - 10, rect.left + 30, rect.top, paint);
        canvas.drawRect(rect.left - 10, rect.top, rect.left, rect.top + 30, paint);
        canvas.drawRect(rect.right - 30, rect.top - 10, rect.right, rect.top, paint);
        canvas.drawRect(rect.right, rect.top - 10, rect.right + 10, rect.top + 30, paint);
        canvas.drawRect(rect.left, rect.bottom, rect.left + 30, rect.bottom + 10, paint);
        canvas.drawRect(rect.left - 10, rect.bottom - 30, rect.left, rect.bottom + 10, paint);
        canvas.drawRect(rect.right - 30, rect.bottom, rect.right, rect.bottom + 10, paint);
        canvas.drawRect(rect.right, rect.bottom - 30, rect.right + 10, rect.bottom + 10, paint);
    }


    public void addPossibleResultPoint(ResultPoint point) {
        possibleResultPoints.add(point);
    }

    @Override
    public void setCameraManager(CameraManager cameraManager) {
        mCameraManager = cameraManager;
    }
}