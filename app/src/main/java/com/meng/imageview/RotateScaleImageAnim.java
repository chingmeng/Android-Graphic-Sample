package com.meng.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;

public class RotateScaleImageAnim extends AppCompatImageView {

    private int rotationDegrees = 0;
    private float scale;
    private int directionScale;

    public RotateScaleImageAnim(Context context) {
        super(context);
        init();
    }

    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.rotate(rotation(3));

        float scaleFactor = scale(0.01f);

        canvas.scale(scaleFactor, scaleFactor);
        canvas.translate(-canvas.getWidth()/2, -canvas.getHeight()/2);

        postInvalidateOnAnimation();
        super.onDraw(canvas);
    }

    private float scale(float delta) {
        scale = (scale + delta * directionScale);
        if (scale <= 0) {
            directionScale = 1;
            scale = 0;
        } else if (scale >= 1) {
            directionScale = -1;
            scale = 1;
        }
        return scale;
    }

    private int rotation(int delta) {
        rotationDegrees = (rotationDegrees + delta) % 360;
        return rotationDegrees;
    }
}