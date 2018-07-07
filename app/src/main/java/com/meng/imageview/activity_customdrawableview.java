package com.meng.imageview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class activity_customdrawableview extends AppCompatActivity {

    CustomDrawableView mCustomDrawableView;
    myView mV, mV2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout ll = new LinearLayout(this);

        mCustomDrawableView = new CustomDrawableView(this);
        mCustomDrawableView.setLayoutParams(new ViewGroup.LayoutParams(400,300));

        TextView tv = new TextView(this);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        CircleView cv = new CircleView(this);
        cv.setLayoutParams(new ViewGroup.LayoutParams(300, 300));



        tv.setText("hello! WOrld!?");
        tv1.setText("Hey! WOrld!?");
        tv2.setText("Huiyo! WOrld!?");

        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(80,50,80,50);
        ll.setBackgroundColor(getResources().getColor(R.color.emerald));
        ll.addView(mCustomDrawableView);
        ll.addView(tv);
        ll.addView(tv1);
        ll.addView(tv2);
        ll.addView(cv);


        setContentView(ll);
    }





}
