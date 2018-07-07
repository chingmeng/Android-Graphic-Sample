package com.meng.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomDrawableView extends View {

    private ShapeDrawable mDrawable;
    Paint p;


    public CustomDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawableView(Context context) {
        super(context);

        int x = 20;
        int y = 20;
        int width = 300;
        int height = 50;

        mDrawable = new ShapeDrawable(new OvalShape());
        // If the color isn't set, the shape uses black as the default.
        mDrawable.getPaint().setColor(0xff74AC23);
        // If the bounds aren't set, the shape can't be drawn.
        mDrawable.setBounds(x, y, x + width, y + height);

        p = new Paint();
        p.setColor(getResources().getColor(R.color.colorAccent));

    }

    @Override
    protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//            mDrawable.draw(canvas);
        canvas.drawCircle(100,100,200, p);

    }
}