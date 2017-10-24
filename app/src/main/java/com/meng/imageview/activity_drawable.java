package com.meng.imageview;

import android.app.ActionBar;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class activity_drawable extends AppCompatActivity {

    LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create a LinearLayout in which to add the ImageView
        mLinearLayout = new LinearLayout(this);

        // Instantiate an ImageView and define its properties
        TransitionDrawable transitionDrawable = (TransitionDrawable) this.getResources().getDrawable(R.drawable.transition);
        ImageView i = new ImageView(this);
//        i.setImageResource(R.drawable.aa);

        // Set the ImageView bounds to match the Drawables's dimensions
        i.setAdjustViewBounds(true);
        i.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.MATCH_PARENT, Gallery.LayoutParams.MATCH_PARENT));
        i.setScaleType(ImageView.ScaleType.FIT_XY);
        i.setImageDrawable(transitionDrawable);

        // Start Transition
        transitionDrawable.startTransition(1000);

        // Add the ImageView to the layout and set the layout as the context view
        mLinearLayout.addView(i);
        setContentView(mLinearLayout);

    }
}
