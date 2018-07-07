package com.meng.imageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class myView extends ViewGroup {

    public myView(Context context) {
        super(context);
        init();

    }

    public myView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // attrs contains the raw values for the XML attributes
        // that were specified in the layout, which don't include
        // attributes set by styles or themes, and which may have
        // unresolved references. Call obtainStyledAttributes()
        // to get the final values for each attribute.
        //
        // This call uses R.styleable.PieChart, which is an array of
        // the custom attributes that were declared in attrs.xml.
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0
        );

        try {
            // Retrieve the values from the TypedArray and store into
            // fields of this class.
            //
            // The R.styleable.PieChart_* constants represent the index for
            // each custom attribute in the R.styleable.PieChart array.
//            mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
//            mTextY = a.getDimension(R.styleable.PieChart_labelY, 0.0f);
//            mTextWidth = a.getDimension(R.styleable.PieChart_labelWidth, 0.0f);
//            mTextHeight = a.getDimension(R.styleable.PieChart_labelHeight, 0.0f);
//            mTextPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
//            mTextColor = a.getColor(R.styleable.PieChart_labelColor, 0xff000000);
//            mHighlightStrength = a.getFloat(R.styleable.PieChart_highlightStrength, 1.0f);
//            mPieRotation = a.getInt(R.styleable.PieChart_pieRotation, 0);
//            mPointerRadius = a.getDimension(R.styleable.PieChart_pointerRadius, 2.0f);
//            mAutoCenterInSlice = a.getBoolean(R.styleable.PieChart_autoCenterPointerInSlice, false);
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }

        init();

    }


    private void init() {
        circleView cView = new circleView(getContext());
        addView(cView);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();

        int w = Math.max(minw, MeasureSpec.getSize(widthMeasureSpec));

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = (w - (int) 20) + getPaddingBottom() + getPaddingTop();
        int h = Math.min(MeasureSpec.getSize(heightMeasureSpec), minh);

        setMeasuredDimension(w, h);

    }


    public class circleView extends View {

        Paint p;

        private Rect rectangle;
        private Paint paint;

        public circleView(Context context) {
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
            canvas.drawCircle(500, 500, 50, paint);

        }

    }


    public class gesture extends GestureDetector.SimpleOnGestureListener {

        public gesture() {
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }


        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return super.onDoubleTap(e);
        }


        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }



    }

}
