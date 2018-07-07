package com.meng.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import static android.graphics.Bitmap.Config.ARGB_8888;

public class CanvasView extends AppCompatImageView {

    public int width;
    public int height;
    private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.img2);
    private Bitmap mutable = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
    private Canvas mCanvas = new Canvas(mutable);
    public Path mPath;
    Context context;
    public Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    public CanvasView(Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);
         context = c;

        // Set a new Path
        mPath = new Path();

        // Set a new paint with desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        //Draw canvas onto defined Bitmap
        super.onDraw(canvas);

        // Draw the mPath with mPaint on the canvas
        canvas.drawPath(mPath, mPaint);

    }

    // When ACTION_DOWN start touch according to the x, y values
    private void startTouch (float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

    }

    // When ACTION_MOVE move touch according to the x, y values
    private void moveTouch (float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if(dx >= TOLERANCE || dy >= TOLERANCE) {

            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }

    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();

    }

    // When ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }

        return true;

    }
}
