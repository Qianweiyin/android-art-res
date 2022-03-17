package com.qwy.scan;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.qwy.chapter_01.R;


/**
 * 暂时不知道用途
 */
public class CardViewfinderView extends View {
    private static final String dmw = "将名片放入取景框，扫描拍照";
    private static final String dmx = "点击扫描";
    private Context context;
    private int dmA;
    private int dmB;
    private Bitmap mBitmap;
    private ValueAnimator mValueAnimator;
    private Path dmz;
    private int width;
    private int height;
    private RectF mCropRect;
    private float dmG = 0.0f;
    private boolean dmH = false;
    private Paint dmy = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF dmF = new RectF();
    private Paint dmE = new Paint(Paint.ANTI_ALIAS_FLAG);

    public interface a {
        void amQ();
    }

    public CardViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        Resources resources = context.getResources();
        this.dmy.setColor(resources.getColor(android.R.color.transparent));
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setColor(resources.getColor(android.R.color.transparent));
        this.mTextPaint.setTextSize(TypedValue.applyDimension(1, 22.0f, resources.getDisplayMetrics()));
        this.dmE.setColor(resources.getColor(R.color.teal_200));
    }

    private void T(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        canvas.save();
        canvas.drawRect(this.mCropRect, this.dmy);
        this.dmy.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmap, this.mCropRect.left, this.mCropRect.top, (Paint) null);
        canvas.restore();
        this.dmF.set(
                this.mCropRect.left + this.mCropRect.width(),
                this.mCropRect.top,
                this.mCropRect.right + this.mCropRect.width(),
                this.mCropRect.bottom);
        this.dmF.offset(this.dmG, 0.0f);
        canvas.drawRect(this.dmF, this.dmE);
        U(canvas);
        Paint paint = new Paint();
        paint.setColor(this.context.getResources().getColor(R.color.teal_200));
        canvas.drawRect(this.mCropRect.right, this.mCropRect.top, this.width, this.mCropRect.bottom, paint);
    }

    private void U(Canvas canvas) {
        if (this.dmz == null) {
            this.dmz = new Path();
            this.mTextPaint.setColor(this.context.getResources().getColor(R.color.teal_200));
            this.mTextPaint.setTextSize(this.context.getResources().getDimension(R.dimen.y18));
            float measureText = this.mTextPaint.measureText(dmw);
            float textSize = this.mCropRect.right + this.mTextPaint.getTextSize();
            float f = ((this.mCropRect.top + this.mCropRect.bottom) - measureText) / 2.0f;
            this.dmz.moveTo(textSize, f);
            this.dmz.lineTo(textSize, f + measureText);
        }
        canvas.drawTextOnPath(dmw, this.dmz, 0.0f, 0.0f, this.mTextPaint);
    }

    private void amP() {
        this.width = SizeUtils.getWindowWidth((Activity) this.context);
        int R = SizeUtils.getWindowHeight((Activity) this.context) - this.dmA;
        int i = this.dmB;
        this.height = R - i;
        if (this.mCropRect == null) {
            int i2 = this.height;
            float f = (int) (i2 - (i2 * 0.12f));
            float f2 = 0.618f * f;
            int i3 = this.width;
            float f3 = (i3 - f2) / 2.0f;
            this.mCropRect = new RectF(f3,
                    ((i2 - f) / 2.0f) + i,
                    i3 - f3,
                    (SizeUtils.getWindowHeight((Activity) this.context) - this.dmA) - ((this.height - f) / 2.0f));
        }
    }

    public void a(final a aVar) {
        mValueAnimator = ValueAnimator.ofFloat(0.0f, this.mCropRect.width());
        mValueAnimator.setDuration(3000L);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CardViewfinderView.this.dmG = -((Float) valueAnimator.getAnimatedValue()).floatValue();
                CardViewfinderView.this.postInvalidate();
            }
        });
        mValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                aVar.amQ();
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }

            @Override
            public void onAnimationStart(Animator animator) {
            }
        });
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.start();
    }

    public void amO() {
        this.dmH = true;
        invalidate();
    }

    public RectF getCropRect() {
        return this.mCropRect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mBitmap != null) {
            T(canvas);
            return;
        }
        canvas.drawRect(0.0f, 0.0f, this.width, this.mCropRect.top, this.dmy);
        canvas.drawRect(0.0f, this.mCropRect.top, this.mCropRect.left, this.mCropRect.bottom, this.dmy);
        canvas.drawRect(this.mCropRect.right, this.mCropRect.top, this.width, this.mCropRect.bottom, this.dmy);
        canvas.drawRect(0.0f, this.mCropRect.bottom, this.width, canvas.getHeight(), this.dmy);
        Paint paint = new Paint();
        paint.setColor(this.context.getResources().getColor(R.color.c_db4b44));
        int i = this.width / 20;
        float f = i / 4;
        float f2 = i;
        canvas.drawRect(this.mCropRect.left - f, this.mCropRect.top - f, this.mCropRect.left, this.mCropRect.top + f2, paint);
        canvas.drawRect(this.mCropRect.left - f, this.mCropRect.top - f, this.mCropRect.left + f2, this.mCropRect.top, paint);
        canvas.drawRect(this.mCropRect.right, this.mCropRect.top - f, this.mCropRect.right + f, this.mCropRect.top + f2, paint);
        canvas.drawRect(this.mCropRect.right - f2, this.mCropRect.top - f, this.mCropRect.right + f, this.mCropRect.top, paint);
        canvas.drawRect(this.mCropRect.left - f, this.mCropRect.bottom - f2, this.mCropRect.left, this.mCropRect.bottom + f, paint);
        canvas.drawRect(this.mCropRect.left - f, this.mCropRect.bottom, this.mCropRect.left + f2, this.mCropRect.bottom + f, paint);
        canvas.drawRect(this.mCropRect.right, this.mCropRect.bottom - f2, this.mCropRect.right + f, this.mCropRect.bottom + f, paint);
        canvas.drawRect(this.mCropRect.right - f2, this.mCropRect.bottom, this.mCropRect.right + f, this.mCropRect.bottom + f, paint);
        U(canvas);
    }

    public void setMerger(int i, int i2) {
        this.dmA = i2;
        this.dmB = i;
        amP();
        invalidate();
    }

    public void setResultBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        postInvalidate();
    }
}

