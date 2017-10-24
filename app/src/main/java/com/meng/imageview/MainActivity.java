package com.meng.imageview;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    RadioGroup radioGroup;
    ImageView imageView;
    Button b1;
    SeekBar sb;
    int numClicks = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        imageView = (ImageView) findViewById(R.id.imageview);
        b1 = (Button) findViewById(R.id.b1);
        sb = (SeekBar) findViewById(R.id.sb);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                imageView.setImageAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch(checkedId) {
                    case R.id.r1:
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                        break;
                    case R.id.r2:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        break;
                    case R.id.r3:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        break;
                    case R.id.r4:
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        break;
                    case R.id.r5:
                        imageView.setScaleType(ImageView.ScaleType.FIT_START);
                        break;
                    case R.id.r6:
                        imageView.setScaleType(ImageView.ScaleType.FIT_END);
                        break;
                    case R.id.r7:
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        break;

                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageView.setPivotX(imageView.getWidth()/2);
                imageView.setPivotY(imageView.getHeight()/2);
                imageView.setRotation(30*numClicks);
                numClicks++;


            }
        });



    }



}
