package com.meng.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CanvasDrawingShader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BorderedImageView a = new BorderedImageView(this);
        RadialFxImageView a = new RadialFxImageView(this);
        setContentView(a);
//        setContentView(R.layout.activity_canvas_drawing);
    }



}
