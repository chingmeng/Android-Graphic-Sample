package com.meng.imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CanvasDrawing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BorderedImageView a = new BorderedImageView(this);
//        RotatingImageView a = new RotatingImageView(this);
        setContentView(a);
//        setContentView(R.layout.activity_canvas_drawing);
    }



}
