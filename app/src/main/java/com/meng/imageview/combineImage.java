package com.meng.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class combineImage extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.combineimage);

        img= (ImageView) findViewById(R.id.mergedPhoto);
    }
    public void buttonMerge(View view) {

        Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.img1);
        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
        Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage);

        img.setImageBitmap(mergedImages);
    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 10, 10, null);
        return result;
    }
}