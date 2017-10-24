package com.meng.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CanvasDrawingRotate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BorderedImageView a = new BorderedImageView(this);
        RotatingImageView a = new RotatingImageView(this);
        setContentView(a);
//        setContentView(R.layout.activity_canvas_drawing);
    }



}
