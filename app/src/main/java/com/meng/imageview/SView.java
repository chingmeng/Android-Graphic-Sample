package com.meng.imageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class SView extends AppCompatImageView {

    public int width;
    public int height;
    private Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img2);
    private Bitmap mutable = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
    private Canvas mCanvas = new Canvas(mutable);
    public Path mPath;
    Context context;
    public Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    Drawable mResizeDrawableWidth;
    Drawable mResizeDrawableHeight;
    Matrix mMatrix;

    public static final int GROW_NONE        = (1 << 0); // 1
    public static final int GROW_LEFT_EDGE   = (1 << 1); // 2
    public static final int GROW_RIGHT_EDGE  = (1 << 2); // 4
    public static final int GROW_TOP_EDGE    = (1 << 3); // 8
    public static final int GROW_BOTTOM_EDGE = (1 << 4); // 16
    public static final int MOVE             = (1 << 5); // 32
    enum ModifyMode { None, Move, Grow }
    private ModifyMode mMode = ModifyMode.None;

    private final Paint mFocusPaint = new Paint();
    private final Paint mNoFocusPaint = new Paint();
    private final Paint mOutlinePaint = new Paint();

    RectF mCropRect;  // in image space
    private RectF mImageRect;  // in image space
    Rect mDrawRect;  // in screen space

    float mLastX, mLastY;
    int mMotionEdge;
    View mMotionHighlightView;

    final int screenHeight;
    final int screenWidth;


    public SView(Context c, @Nullable AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // Set a new Path
        mPath = new Path();

        // Set a new paint with desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(10f);

        mFocusPaint.setARGB(125, 50, 50, 50);

        mNoFocusPaint.setARGB(125, 50, 50, 50);

        mOutlinePaint.setStrokeWidth(3F);
        mOutlinePaint.setStyle(Paint.Style.STROKE);
        mOutlinePaint.setAntiAlias(true);

        mResizeDrawableWidth = getResources().getDrawable(R.drawable.camera_crop_width);
        mResizeDrawableHeight = getResources().getDrawable(R.drawable.camera_crop_height);


        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) c).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;

        //......
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.aa, options);

        int width = options.outWidth;

        if (width > screenWidth) {
            int widthRatio = Math.round((float) width/ (float) screenWidth);
            // To reduce dimensions of Bitmap, lower quality, the higher, the lower the quality
            options.inSampleSize = widthRatio;
        }

        // Decode by creating bitmap, after updating the options in code above
        options.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aa, options);
        //......

        mMatrix = new Matrix();

        mCropRect = new RectF(0, 0, screenWidth/2, screenHeight/2);
        mImageRect = new RectF(0, 0, screenWidth, screenHeight);
        mDrawRect = computeLayout();

        mMode = ModifyMode.None;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        //Draw canvas onto defined Bitmap
        super.onDraw(canvas);

        canvas.save();
        Path path = new Path();
        canvas.drawBitmap(mBitmap, screenWidth/2 - mBitmap.getWidth()/2,
                screenHeight/2 - mBitmap.getHeight()/2, null);

        Rect viewDrawingRect = new Rect();
        this.getDrawingRect(viewDrawingRect);

        path.addRect(new RectF(mDrawRect), Path.Direction.CW);
        mOutlinePaint.setColor(0xFFFF8A00);

        canvas.clipPath(path, Region.Op.DIFFERENCE);
        canvas.drawRect(viewDrawingRect, hasFocus() ? mFocusPaint : mNoFocusPaint);

        canvas.restore();
        canvas.drawPath(path, mOutlinePaint);
        this.setScaleType(ScaleType.CENTER_INSIDE);

        if (mMode == ModifyMode.Grow) {

            int left = mDrawRect.left + 1;
            int right = mDrawRect.right + 1;
            int top = mDrawRect.top + 4;
            int bottom = mDrawRect.bottom + 3;

            int widthWidth = mResizeDrawableWidth.getIntrinsicWidth() / 2;
            int widthHeight = mResizeDrawableWidth.getIntrinsicHeight() / 2;
            int heightHeight = mResizeDrawableHeight.getIntrinsicHeight() / 2;
            int heightWidth = mResizeDrawableHeight.getIntrinsicWidth() / 2;

            int xMiddle = mDrawRect.left + ((mDrawRect.right - mDrawRect.left) / 2);
            int yMiddle = mDrawRect.top + ((mDrawRect.bottom - mDrawRect.top) / 2);

            mResizeDrawableWidth.setBounds(left - widthWidth,
                    yMiddle - widthHeight,
                    left + widthWidth,
                    yMiddle + widthHeight);
            mResizeDrawableWidth.draw(canvas);

            mResizeDrawableWidth.setBounds(right - widthWidth,
                    yMiddle - widthHeight,
                    right + widthWidth,
                    yMiddle + widthHeight);
            mResizeDrawableWidth.draw(canvas);

            mResizeDrawableHeight.setBounds(xMiddle - heightWidth,
                    top - heightHeight,
                    xMiddle + heightWidth,
                    top + heightHeight);
            mResizeDrawableHeight.draw(canvas);

            mResizeDrawableHeight.setBounds(xMiddle - heightWidth,
                    bottom - heightHeight,
                    xMiddle + heightWidth,
                    bottom + heightHeight);
            mResizeDrawableHeight.draw(canvas);
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int edge = getHit(event.getX(), event.getY());
                if (edge != GROW_NONE) {
                    mMotionEdge = edge;
                    mLastX = event.getX();
                    mLastY = event.getY();
                    setMode((edge == MOVE) ? ModifyMode.Move : ModifyMode.Grow);
                }
                break;

            case MotionEvent.ACTION_UP:
                setMode(ModifyMode.None);
                mMotionEdge = GROW_NONE;
                break;

            case MotionEvent.ACTION_MOVE:
                handleMotion(mMotionEdge, event.getX() - mLastX, event.getY() - mLastY);
                mLastX = event.getX();
                mLastY = event.getY();
                break;
        }

        return true;
    }

    public void setMode(ModifyMode mode) {
        if (mode != mMode) {
            mMode = mode;
            this.invalidate();
        }
    }

    // Maps the cropping rectangle from image space to screen space.
    private Rect computeLayout() {
        RectF r = new RectF(mCropRect.left, mCropRect.top, mCropRect.right, mCropRect.bottom);
        mMatrix.mapRect(r);
        return new Rect(Math.round(r.left), Math.round(r.top), Math.round(r.right), Math.round(r.bottom));
    }

    // Determines which edges are hit by touching at (x, y).
    public int getHit(float x, float y) {
        Rect r = computeLayout();
        final float hysteresis = 20F; // tolerance
        int retval = GROW_NONE;

        // verticalCheck makes sure the position is between the top and
        // the bottom edge (with some tolerance). Similar for horizCheck.
        boolean verticalCheck = (y >= r.top - hysteresis) && (y < r.bottom + hysteresis);
        boolean horizCheck = (x >= r.left - hysteresis) && (x < r.right + hysteresis);

        // Check whether the position is near some edge(s).
        if ((Math.abs(r.left - x)     < hysteresis)  &&  verticalCheck) {
            retval |= GROW_LEFT_EDGE;
        }
        if ((Math.abs(r.right - x)    < hysteresis)  &&  verticalCheck) {
            retval |= GROW_RIGHT_EDGE;
        }
        if ((Math.abs(r.top - y)      < hysteresis)  &&  horizCheck) {
            retval |= GROW_TOP_EDGE;
        }
        if ((Math.abs(r.bottom - y)   < hysteresis)  &&  horizCheck) {
            retval |= GROW_BOTTOM_EDGE;
        }

        // Not near any edge but inside the rectangle: move.
        if (retval == GROW_NONE && r.contains((int) x, (int) y)) {
            retval = MOVE;
        }

        return retval;
    }

    // Handles motion (dx, dy) in screen space.
    // The "edge" parameter specifies which edges the user is dragging.
    void handleMotion(int edge, float dx, float dy) {
        Rect r = computeLayout();
        if (edge == GROW_NONE) {

        } else if (edge == MOVE) {

            // Convert to image space before sending to moveBy().
            moveBy(dx * (mCropRect.width() / r.width()), dy * (mCropRect.height() / r.height()));

        } else {

            if (((GROW_LEFT_EDGE | GROW_RIGHT_EDGE) & edge) == 0) {

                dx = 0;

            }

            if (((GROW_TOP_EDGE | GROW_BOTTOM_EDGE) & edge) == 0) {

                dy = 0;

            }

            // Convert to image space before sending to growBy().
            float xDelta = dx * (mCropRect.width() / r.width());
            float yDelta = dy * (mCropRect.height() / r.height());
            growBy((((edge & GROW_LEFT_EDGE) != 0) ? -1 : 1) * xDelta,
                    (((edge & GROW_TOP_EDGE) != 0) ? -1 : 1) * yDelta);

        }
    }

    // Grows the cropping rectange by (dx, dy) in image space.
    void moveBy(float dx, float dy) {
        Rect invalRect = new Rect(mDrawRect);

        mCropRect.offset(dx, dy);

        // Put the cropping rectangle inside image rectangle.
        mCropRect.offset(
                Math.max(0, mImageRect.left - mCropRect.left),
                Math.max(0, mImageRect.top  - mCropRect.top));

        mCropRect.offset(
                Math.min(0, mImageRect.right  - mCropRect.right),
                Math.min(0, mImageRect.bottom - mCropRect.bottom));

        mDrawRect = computeLayout();
        invalRect.union(mDrawRect);
        invalRect.inset(-10, -10);
        this.invalidate(mDrawRect);
    }

    // Grows the cropping rectange by (dx, dy) in image space.
    void growBy(float dx, float dy) {

        // Don't let the cropping rectangle grow too fast.
        // Grow at most half of the difference between the image rectangle and
        // the cropping rectangle.
        RectF r = new RectF(mCropRect);
        if (dx > 0F && r.width() + 2 * dx > mImageRect.width()) {
            float adjustment = (mImageRect.width() - r.width()) / 2F;
            dx = adjustment;
        }
        if (dy > 0F && r.height() + 2 * dy > mImageRect.height()) {
            float adjustment = (mImageRect.height() - r.height()) / 2F;
            dy = adjustment;
        }

        // If the size of the mCropRect is smaller than required, then, stop skipp inset!
        // Eg, if (r.bottom - r.top) < 100 || (r.right - r.left) < 100)
        // only if not true, then inset is being done

        // At the point when this is true, resize cannot be done, maybe snap back a bit?
            r.inset(-dx, -dy);

        // Put the cropping rectangle inside the image rectangle.
        if (r.left < mImageRect.left) {
            r.offset(mImageRect.left - r.left, 0F);
        } else if (r.right > mImageRect.right) {
            r.offset(-(r.right - mImageRect.right), 0);
        }
        if (r.top < mImageRect.top) {
            r.offset(0F, mImageRect.top - r.top);
        } else if (r.bottom > mImageRect.bottom) {
            r.offset(0F, -(r.bottom - mImageRect.bottom));
        }


        // Don't let the cropping rectangle shrink too fast.
        final float widthCap = 25F;
        if (r.width() < widthCap) {
            r.inset(-(widthCap - r.width()) / 2F, 0F);
        }
        float heightCap = widthCap;
        if (r.height() < heightCap) {
            r.inset(0F, -(heightCap - r.height()) / 2F);
        }

        // Put the cropping rectangle inside the image rectangle.
        if (r.left < mImageRect.left) {
            r.offset(mImageRect.left - r.left, 0F);
        } else if (r.right > mImageRect.right) {
            r.offset(-(r.right - mImageRect.right), 0);
        }
        if (r.top < mImageRect.top) {
            r.offset(0F, mImageRect.top - r.top);
        } else if (r.bottom > mImageRect.bottom) {
            r.offset(0F, -(r.bottom - mImageRect.bottom));
        }

        mCropRect.set(r);
        mDrawRect = computeLayout();
        this.invalidate();
    }


    // Returns the cropping rectangle in image space.
    public Rect getCropRect() {
        return new Rect((int) mCropRect.left, (int) mCropRect.top,
                (int) mCropRect.right, (int) mCropRect.bottom);
    }

    // Returns the cropping rectangle in image space.
    public Bitmap getCropImage() {
        RectF rrr = mCropRect;
        Bitmap b = Bitmap.createBitmap(mBitmap, (int) rrr.left, (int) rrr.top,
                (int)rrr.right - (int)rrr.left, (int)rrr.bottom - (int) rrr.top);


        return b;
    }


    /*        @Override
        public boolean onTouchEvent(MotionEvent event) {

            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    myToast.mToast(getContext(), "Context Width: " + String.valueOf(contextWidth));
                    myToast.mToast(getContext(), "Context Height: " + String.valueOf(contextHeight));
                    startTouch(x, y);
                    invalidate();
                    break;

                case MotionEvent.ACTION_MOVE:
                    moveTouch(x, y);
                    invalidate();
                    break;

                case MotionEvent.ACTION_UP:
                    upTouch();
                    invalidate();
                    break;
            }

            return true;

        }*/

    // When ACTION_DOWN start touch according to the x, y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;

    }

    // When ACTION_MOVE move touch according to the x, y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);

        if (dx >= TOLERANCE || dy >= TOLERANCE) {

            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }

    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();

    }

    // When ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }







}