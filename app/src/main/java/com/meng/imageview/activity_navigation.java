package com.meng.imageview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meng.imageview.LoadImage.activity_loadimage;
import com.meng.imageview.apidemos.AlphaBitmap;
import com.meng.imageview.apidemos.AnimateDrawables;
import com.meng.imageview.apidemos.Arcs;
import com.meng.imageview.apidemos.BitmapDecode;
import com.meng.imageview.apidemos.BitmapMesh;
import com.meng.imageview.apidemos.BitmapPixels;
import com.meng.imageview.apidemos.CameraPreview;
import com.meng.imageview.apidemos.ColorFilters;
import com.meng.imageview.apidemos.ColorMatrixSample;
import com.meng.imageview.apidemos.Compass;
import com.meng.imageview.apidemos.DrawPoints;
import com.meng.imageview.apidemos.Regions;
import com.meng.imageview.apidemos.ScaleToFit;
import com.meng.imageview.apidemos.SurfaceViewOverlay;
import com.meng.imageview.apidemos.Sweep;
import com.meng.imageview.apidemos.TouchPaint;
import com.meng.imageview.apidemos.TriangleActivity;
import com.meng.imageview.apidemos.UnicodeChart;
import com.meng.imageview.apidemos.WindowSurface;
import com.meng.imageview.apidemos.Xfermodes;
import com.meng.imageview.google.CropImage;

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
//                        try {
                            moveToPage(CropImage.class);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
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

                    case 15:
                        moveToPage(clipRegionSample.class);
                        break;

                    case 16:
                        moveToPage(mHighlightView.class);
                        break;

                    case 17:
                        moveToPage(DrawPoints.class);
                        break;

                    case 18:
                        moveToPage(TouchPaint.class);
                        break;

                    case 19:
                        moveToPage(Arcs.class);
                        break;

                    case 20:
                        moveToPage(BitmapMesh.class);
                        break;

                    case 21:
                        moveToPage(ColorMatrixSample.class);
                        break;

                    case 22:
                        moveToPage(UnicodeChart.class);
                        break;

                    case 23:
                        moveToPage(WindowSurface.class);
                        break;

                    case 24:
                        moveToPage(Sweep.class);
                        break;

                    case 25:
                        moveToPage(AlphaBitmap.class);
                        break;

                    case 26:
                        moveToPage(AnimateDrawables.class);
                        break;

                    case 27:
                        moveToPage(BitmapPixels.class);
                        break;

                    case 28:
                        moveToPage(BitmapDecode.class);
                        break;

                    case 29:
                        moveToPage(CameraPreview.class);
                        break;

                    case 30:
                        moveToPage(ColorFilters.class);
                        break;

                    case 31:
                        moveToPage(Regions.class);
                        break;

                    case 32:
                        moveToPage(Xfermodes.class);
                        break;

                    case 33:
                        moveToPage(ScaleToFit.class);
                        break;

                    case 34:
                        moveToPage(SurfaceViewOverlay.class);
                        break;

                    case 35:
                        moveToPage(Compass.class);
                        break;

                    case 36:
                        moveToPage(TriangleActivity.class);
                        break;

                    case 37:
                        moveToPage(activity_PieChart.class);
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
