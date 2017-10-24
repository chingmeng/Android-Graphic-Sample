package com.meng.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatImageView;

/**
 * Created by elc on 9/10/2017.
 */

public class BorderedImageView extends AppCompatImageView {
    private static final int STROKE_WIDTH_DP = 6;
    private Paint paintBorder;
    private Bitmap bitmap;
    private int strokeWidthPx;
    private RectF rectF;

    public BorderedImageView(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // A round rect will be printed.
        canvas.drawRoundRect(rectF, 10, 10, paintBorder);
        // The bitmap for the resource android icon
        // Note the Paint for the bitmap is null, we will talk about in a moment...
        canvas.drawBitmap(bitmap, strokeWidthPx,strokeWidthPx, null);

        Paint paint = new Paint();
        paint.setShadowLayer(30, 30, 30, 0xFF555555);
        canvas.drawBitmap(bitmap, 500, 500, paint);


    }

    private void init() {

        // The resource is embedded, but it can be easily moved in the constructor.
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        // The same goes for the stroke width in dp.
        strokeWidthPx = (int) (STROKE_WIDTH_DP * getResources().getDisplayMetrics().density);
        int halfStrokeWidthPx = strokeWidthPx/2;

        paintBorder = new Paint();
        paintBorder.setStyle(Paint.Style.STROKE);
        // Stroke width is in pixels
        paintBorder.setStrokeWidth(strokeWidthPx);
        // Our color for the border.
        paintBorder.setColor(Color.BLUE);

        int totalWidth = bitmap.getWidth() + strokeWidthPx * 2;
        int totalHeight = bitmap.getHeight() + strokeWidthPx * 2;

        // An empty bitmap with the same size of our resource to display,
        // increased of the desired border width.
        setImageBitmap(Bitmap.createBitmap(totalWidth,totalHeight, Bitmap.Config.ARGB_8888));

        // The rectangle that will be used for drawing the colored border
        rectF = new RectF(halfStrokeWidthPx, halfStrokeWidthPx, totalWidth - halfStrokeWidthPx,
                totalHeight - halfStrokeWidthPx);

    }

}