package com.meng.imageview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class LargeImageActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);

        Button b2 = (Button) findViewById(R.id.b2);
        final EditText et = (EditText) findViewById(R.id.et1);



        ImageView imageView = (ImageView) findViewById (R.id.iv2);
        Display display = getWindowManager().getDefaultDisplay();
        int displayWidth = display.getWidth();

        // Used to determine size only without creating the bitmap, as the bitmap is expensive
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.largeimage, options);

        int width = options.outWidth;

        if (width > displayWidth) {
            int widthRatio = Math.round((float) width/ (float) displayWidth);
            // To reduce dimensions of Bitmap, lower quality, the higher, the lower the quality
            options.inSampleSize = 10000;
        }

        // Decode by creating bitmap, after updating the options in code above
        options.inJustDecodeBounds = false;
        Bitmap scaledBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.largeimage, options);
        imageView.setImageBitmap(scaledBitmap);

        final BitmapFactory.Options op =  options;
        final ImageView iv =  imageView;

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(et.getText().toString());
                op.inSampleSize = a;
                op.inJustDecodeBounds = false;
                Bitmap scaledBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.largeimage, op);
                iv.setImageBitmap(scaledBitmap);

            }
        });


    }



}
