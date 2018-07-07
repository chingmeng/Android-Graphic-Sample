package com.meng.imageview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;

public class CircleView extends View{

    Paint p;

    private Rect rectangle;
    private Paint paint;

    public CircleView(Context context) {
        super(context);
//        int x = 50;
//        int y = 50;
//        int sideLength = 200;
//
//         create a rectangle that we'll draw later
//        rectangle = new Rect(x, y, sideLength, sideLength);

        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorAccent));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
//        canvas.drawRect(rectangle, paint);
        canvas.drawCircle(100, 100, 20, paint);

    }
}



//    Paint paint;

//    public CircleView(Context context) {
//        super(context);
//        paint = new Paint();
//        paint.setColor(getResources().getColor(R.color.colorAccent));
//
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(10, 10, 50, paint);
//
//    }

//}
