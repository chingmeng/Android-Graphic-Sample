package com.meng.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CanvasDrawingRotateScale extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RotateScaleImageAnim a = new RotateScaleImageAnim(this);
        setContentView(a);
    }



}
