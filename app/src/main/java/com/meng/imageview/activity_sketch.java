package com.meng.imageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class activity_sketch extends AppCompatActivity {

    private CanvasView customCanvas;
    ImageView img, img2, img3;
    Canvas canvas;
    Bitmap bmap, bmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_sketch);
        } catch (Exception e) {
            e.printStackTrace();
        }

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        img = (ImageView) findViewById(R.id.save_view);
        img2 = (ImageView) findViewById(R.id.save_view2);
        img3 = (ImageView) findViewById(R.id.mergedimage);

    }

    public void clearCanvas (View v) {

        customCanvas.clearCanvas();

    }

    public void copyCanvas (View v) {
        try {
            bmap = Bitmap.createBitmap(customCanvas.getWidth(), customCanvas.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bmap);
            customCanvas.draw(canvas);
            img.setImageBitmap(bmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void copyCanvas2 (View v) {
        try {
            bmap2 = Bitmap.createBitmap(customCanvas.getWidth(), customCanvas.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas2 = new Canvas(bmap2);
            customCanvas.draw(canvas2);
            img2.setImageBitmap(bmap2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void combineBitmap(View v) {

        /*try {
            Bitmap cs = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
            Canvas canvasCombo = new Canvas(cs);
            Bitmap a = ((BitmapDrawable) img.getDrawable()).getBitmap();
            Bitmap b = ((BitmapDrawable) img2.getDrawable()).getBitmap();
//            Bitmap a = BitmapFactory.decodeResource(getResources(),R.drawable.img1);
//            Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.img2);
//            canvasCombo.drawBitmap(a, 0f, 0f, null);
//            canvasCombo.drawBitmap(b, 10, 10, null);
            canvasCombo.drawBitmap(a, 0f, 0f, null);
            canvasCombo.drawBitmap(b, 10, 10, null);
            img3.draw(canvasCombo);
            img3.setImageBitmap(cs);

        } catch (Exception e) {
            e.printStackTrace();
        }

*/
    }



}
