package com.hencoder.hencoderpracticedraw4.practice;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hencoder.hencoderpracticedraw4.R;

public class Practice14FlipboardView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();
    Matrix mMatrix = new Matrix();
    int degree;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 180);

    public Practice14FlipboardView(Context context) {
        super(context);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14FlipboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

        animator.setDuration(2500);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @SuppressWarnings("unused")
    public void setDegree(int degree) {
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = centerX - bitmapWidth / 2;
        int y = centerY - bitmapHeight / 2;


        //这里是先截取上半部分，固定不动，然后下面根据度数进行截取动画！！！
        canvas.save();
        canvas.clipRect(0 , 0 , getWidth() , centerY);
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();


        camera.save();
        //小于90度 只要截取view的下半部分，做动画就行，
        // (0 ,  getHeight() / 2 ，getWidth() ,getHeight() )

        //大于90度， 只截取View的上半部分 ， 做动画
        //0 ， 0 ， getWidth ， getHeight / 2 .
        if (degree< 90){
            canvas.clipRect(0 , centerY,getWidth() , getHeight() );
        }else {
            canvas.clipRect(0 , 0,getWidth() , centerY );
        }
        camera.rotateX(degree);
//        canvas.translate(centerX, centerY);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-centerX, -centerY);
        mMatrix.reset();
        camera.getMatrix(mMatrix);
        camera.restore();

        mMatrix.preTranslate(-centerX , -centerY);
        mMatrix.postTranslate(centerX , centerY);

        canvas.save();
        canvas.concat(mMatrix);
        canvas.drawBitmap(bitmap, x, y, paint);
        canvas.restore();
    }
}
