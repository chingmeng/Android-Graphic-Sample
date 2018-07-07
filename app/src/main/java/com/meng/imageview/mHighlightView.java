package com.meng.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class mHighlightView extends Activity {

    ImageView resultView;
    SView sview;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlightview);

        sview = (SView) findViewById(R.id.mimg1);
        resultView = (ImageView) findViewById(R.id.result);
        button = (Button) findViewById(R.id.capturebutton_highlightview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResultImage();
            }
        });

    }

    public void getResultImage() {
        resultView.setImageBitmap(null);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);

        Bitmap mBitmap = Bitmap.createBitmap(resultView.getWidth(), resultView.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap android = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Canvas c = new Canvas(mBitmap);

        c.drawBitmap(sview.getCropImage(), 0f, 0f, null);
        c.drawBitmap(android, 0f, 0f, null);

        resultView.draw(c);
        resultView.setImageBitmap(mBitmap);
    }
}


