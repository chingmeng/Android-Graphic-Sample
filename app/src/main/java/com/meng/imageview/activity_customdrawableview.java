package com.meng.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class activity_customdrawableview extends AppCompatActivity {

    CustomDrawableView mCustomDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCustomDrawableView = new CustomDrawableView(this);

        setContentView(mCustomDrawableView);
    }


    public class CustomDrawableView extends View {

        private ShapeDrawable mDrawable;

        public CustomDrawableView(Context context) {
            super(context);

            int x = 500;
            int y = 500;
            int width = 300;
            int height = 50;

            mDrawable = new ShapeDrawable(new OvalShape());
            // If the color isn't set, the shape uses black as the default.
            mDrawable.getPaint().setColor(0xff74AC23);
            // If the bounds aren't set, the shape can't be drawn.
            mDrawable.setBounds(x, y, x + width, y + height);

        }

        @Override
        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
            mDrawable.draw(canvas);

        }
    }


}
