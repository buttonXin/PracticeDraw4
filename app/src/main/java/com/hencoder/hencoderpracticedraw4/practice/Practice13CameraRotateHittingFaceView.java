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
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.hencoder.hencoderpracticedraw4.R;

import static android.content.ContentValues.TAG;

public class Practice13CameraRotateHittingFaceView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Point point = new Point(200, 50);
    Camera camera = new Camera();
    Matrix matrix = new Matrix();
    int degree;
    ObjectAnimator animator = ObjectAnimator.ofInt(this, "degree", 0, 360);

    public Practice13CameraRotateHittingFaceView(Context context) {
        super(context);
    }

    public Practice13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13CameraRotateHittingFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, true);
        bitmap.recycle();
        bitmap = scaledBitmap;

        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);

        camera.setLocation(0 , 0 ,- (int) (getResources().getDisplayMetrics().density * 6));

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: start");
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow: end");

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
        int centerX = point.x + bitmapWidth / 2;
        int centerY = point.y + bitmapHeight / 2;

        canvas.save();
        //先进行camera的旋转 ， 并赋值到matrix上
        camera.save();
        matrix.reset();
        camera.rotateX(degree);
//        camera.getMatrix(matrix);

        canvas.translate(centerX , centerY);
        camera.applyToCanvas(canvas);
        canvas.translate(-centerX , -centerY);
        camera.restore();

        //然后将旋转 移动到的中心点 , pre是将旋转（之间）的图片移动到view或者图片中心， post是将位置再移动回来！！！！
//        matrix.preTranslate(-centerX , -centerY);
//        matrix.postTranslate(centerX , centerY);

        //最后将matrix放到canvas中
//        canvas.save();
//        canvas.concat(matrix);
        canvas.drawBitmap(bitmap , point.x , point.y , paint);
        canvas.restore();


        /**
         * 注意一下  这2行实现的效果一样，但是参数是反的！！！！！！！！！！！！！！
         canvas.translate(centerX , centerY);
         camera.applyToCanvas(canvas);
         canvas.translate(-centerX , -centerY);

         matrix.preTranslate(-centerX , -centerY);
         matrix.postTranslate(centerX , centerY);
         *
         *
         */
    }
}