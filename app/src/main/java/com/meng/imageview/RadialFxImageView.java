package com.meng.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatImageView;

public class RadialFxImageView extends AppCompatImageView {
    private static final int STROKE_WIDTH_DP = 6;
    private Paint paintBorder;
    private Bitmap bitmap;
    private int strokeWidthPx;
    private RectF rectF;
    private RadialGradient radialGradient;

    public RadialFxImageView(Context context) {
        super(context);
        init();
    }


    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        strokeWidthPx = (int) (STROKE_WIDTH_DP * getResources().getDisplayMetrics().density);
        int halfStrokeWidthPx = strokeWidthPx / 2;

        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.FILL);

        int totalWidth = bitmap.getWidth() + strokeWidthPx * 2;
        int totalHeight = bitmap.getHeight() + strokeWidthPx * 2;
        radialGradient = new RadialGradient(totalWidth /2, totalHeight /2, totalWidth /2, new int[]
                {Color.BLACK, Color.GREEN}, null, Shader.TileMode.MIRROR);
        paintBorder.setShader(radialGradient);
        setImageBitmap(Bitmap.createBitmap(totalWidth, totalHeight, Bitmap.Config.ARGB_8888));

        rectF = new RectF(halfStrokeWidthPx, halfStrokeWidthPx, totalWidth - halfStrokeWidthPx, totalHeight - halfStrokeWidthPx);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(rectF, 40, 40, paintBorder);
        canvas.drawBitmap(bitmap,strokeWidthPx, strokeWidthPx, null);
    }
}
