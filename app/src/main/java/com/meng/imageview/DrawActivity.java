package com.meng.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DrawActivity extends AppCompatActivity {

    ImageView imageView;
    Button drawButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        imageView = (ImageView) findViewById(R.id.iv_canvas);

        drawButton = (Button) findViewById(R.id.b3);

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap imageBitmap = Bitmap.createBitmap (imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(imageBitmap);
                float scale = getResources().getDisplayMetrics().density;

                Paint p = new Paint();
                p.setColor(Color.RED);
                p.setTextSize(24*scale);
                p.setAntiAlias(true);
                p.setFakeBoldText(true);
                p.setTextAlign(Paint.Align.CENTER);
                p.setTypeface(Typeface.MONOSPACE);
                p.setShadowLayer(5,5,5,Color.CYAN);

                Paint recPaint = new Paint();
                recPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                recPaint.setColor(Color.BLUE);


                Bitmap a = BitmapFactory.decodeResource(getResources(),R.drawable.img2);
                int aWidth = a.getWidth();
                int aHeight = a.getHeight();

                int mOutputX = aWidth;
                int mOutputY = aHeight;

                Bitmap croppedImage = Bitmap.createBitmap(mOutputX, mOutputY, Bitmap.Config.RGB_565);
                Canvas canvax = new Canvas(croppedImage);

//                Rect srcRect = mCrop.getCropRect();
                Rect srcRect = new Rect(0, 0, aWidth, aHeight);
                Rect dstRect = new Rect(0, 0, mOutputX, mOutputY);

                int dx = (srcRect.width() - dstRect.width()) / 2;
                int dy = (srcRect.height() - dstRect.height()) / 2;

                // If the srcRect is too big, use the center part of it.
                srcRect.inset(Math.max(0, dx), Math.max(0, dy));

                // If the dstRect is too big, use the center part of it.
                dstRect.inset(Math.max(0, -dx), Math.max(0, -dy));

                // Draw the cropped bitmap in the center
                canvax.drawBitmap(a, srcRect, dstRect, null);
//
//                canvas.drawRect((float)imageView.getWidth()/2,(float)imageView.getHeight()/2,100f,100f,recPaint);
//
////                canvas.drawCircle(imageView.getWidth()/2,imageView.getHeight()/2,50,p);
//                canvas.drawText("Hello!", imageView.getWidth()/2, imageView.getHeight()/2, p);

                imageView.setImageBitmap(croppedImage);
                // Release bitmap memory as soon as possible
//                mImageView.clear();
                a.recycle();


            }
        });

    }
}
