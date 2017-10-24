package com.meng.imageview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meng.imageview.LoadImage.activity_loadimage;

import java.util.ArrayList;

public class activity_navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ListView lv = (ListView) findViewById(R.id.navi_list);
        String[] strlist = getResources().getStringArray(R.array.Navigation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,strlist);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        moveToPage(MainActivity.class);
                        break;

                    case 1:
                        moveToPage(LargeImageActivity.class);

                        break;

                    case 2:
                        moveToPage(DrawActivity.class);
                        break;

                    case 3:
                        moveToPage(activity_drawable.class);
                        break;

                    case 4:
                        moveToPage(activity_customdrawableview.class);
                        break;

                    case 5:
                        moveToPage(activity_cropimage.class);
                        break;

                    case 6:
                        moveToPage(activity_sketch.class);
                        break;

                    case 7:
                        moveToPage(CanvasDrawing.class);
                        break;

                    case 8:
                        moveToPage(CanvasDrawingRotate.class);
                        break;

                    case 9:
                        moveToPage(CanvasDrawingRotateScale.class);
                        break;

                    case 10:
                        moveToPage(CanvasDrawingShader.class);
                        break;

                    case 11:
                        moveToPage(CanvasDrawingShaderScale.class);
                        break;

                    case 12:
                        moveToPage(ImageToBitmap.class);
                        break;

                    case 13:
                        moveToPage(activity_loadimage.class);
                        break;

                    case 14:
                        moveToPage(combineImage.class);
                        break;

                }
            }
        });


    }

    private void moveToPage(Class clss) {
        Intent i = new Intent(this, clss);
        startActivity(i);
    }

}
