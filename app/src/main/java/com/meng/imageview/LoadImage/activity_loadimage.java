package com.meng.imageview.LoadImage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.meng.imageview.CanvasView;
import com.meng.imageview.R;
import com.meng.imageview.myToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class activity_loadimage extends AppCompatActivity {

//    private static final String TAG = "CropImage";

 /*   // These are various options can be specified in the intent.
    private Bitmap.CompressFormat mOutputFormat = Bitmap.CompressFormat.JPEG; // only used with mSaveUri
    private Uri mSaveUri = null;
    private boolean mSetWallpaper = false;
    private int mAspectX, mAspectY;
    private boolean mDoFaceDetection = true;
    private boolean mCircleCrop = false;
    private final Handler mHandler = new Handler();

    // These options specifiy the output image size and whether we should
    // scale the output to fit it (or just crop it).
    private int mOutputX, mOutputY;
    private boolean mScale;
    private boolean mScaleUp = true;

    boolean mWaitingToPick; // Whether we are wait the user to pick a face.
    boolean mSaving;  // Whether the "save" button is already clicked.

    private CropImageView mImageView;
    private ContentResolver mContentResolver;

    private Bitmap mBitmap;
    HighlightView mCrop;

    private IImageList mAllImages;
    private IImage mImage;*/


    ImageView myImage;
//    CanvasView myImage;
    Button mButton;
    Button mButton2;
    Button mButton3;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadimage);
        verifyStoragePermissions(this);
        myImage = (ImageView) findViewById(R.id.loadimgview);
        mButton = (Button) findViewById(R.id.loadimage_button);
        mButton2 = (Button) findViewById(R.id.loadimage_button2);
        mButton3 = (Button) findViewById(R.id.loadimage_button3);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
                myToast.mToast(getApplicationContext(),"Loading image");
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unloadImage();
                myToast.mToast(getApplicationContext(),"Unloading image");

            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOutput2();
                myToast.mToast(getApplicationContext(),"Saving output");

            }
        });



    }


    private void loadImage() {
        String FILE_DIR = "STKRecorder";
        String sd = Environment.getExternalStorageDirectory() + File.separator + FILE_DIR + File.separator;
        File imgFile = new  File(sd + "test.jpg");


        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            myImage.setImageBitmap(myBitmap);

        }
    }

    private void unloadImage() {
            myImage.setImageBitmap(null);
    }

    private void saveOutput() {
        String FILE_DIR = "STKRecorder";
        String sd = Environment.getExternalStorageDirectory() + File.separator + FILE_DIR + File.separator;
        File imgFile = new  File(sd + "test2.jpg");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imgFile);

            Bitmap myBitmap = ((BitmapDrawable)myImage.getDrawable()).getBitmap();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public Bitmap combineImages(Bitmap c, Bitmap s) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom
        Bitmap cs = null;

        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = c.getWidth() + s.getWidth();
            height = c.getHeight();
        } else {
            width = s.getWidth() + s.getWidth();
            height = c.getHeight();
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, c.getWidth(), 0f, null);

        return cs;
    }


    private void saveOutput2() {
        String FILE_DIR = "STKRecorder";
        String sd = Environment.getExternalStorageDirectory() + File.separator + FILE_DIR + File.separator;
        File imgFile = new  File(sd + "test3.jpg");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(imgFile);
            Bitmap bitmapA = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
            Bitmap bitmapB = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

            Bitmap bs = combineImages(bitmapA, bitmapB);
            bs.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public static boolean verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            Toast.makeText(activity, "Need permission to access storage", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
            return false;
        }
        return true;
    }


/*

    private void saveOutput(Bitmap croppedImage) {
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = mContentResolver.openOutputStream(mSaveUri);
                if (outputStream != null) {
                    croppedImage.compress(mOutputFormat, 75, outputStream);
                }
            } catch (IOException ex) {
                // TODO: report error to caller
                Log.e(TAG, "Cannot open file: " + mSaveUri, ex);
            } finally {
                Util.closeSilently(outputStream);
            }
            Bundle extras = new Bundle();
            setResult(RESULT_OK, new Intent(mSaveUri.toString())
                    .putExtras(extras));
        } else if (mSetWallpaper) {
            try {
                WallpaperManager.getInstance(this).setBitmap(croppedImage);
                setResult(RESULT_OK);
            } catch (IOException e) {
                Log.e(TAG, "Failed to set wallpaper.", e);
                setResult(RESULT_CANCELED);
            }
        } else {
            Bundle extras = new Bundle();
            extras.putString("rect", mCrop.getCropRect().toString());

            File oldPath = new File(mImage.getDataPath());
            File directory = new File(oldPath.getParent());

            int x = 0;
            String fileName = oldPath.getName();
            fileName = fileName.substring(0, fileName.lastIndexOf("."));

            // Try file-1.jpg, file-2.jpg, ... until we find a filename which
            // does not exist yet.
            while (true) {
                x += 1;
                String candidate = directory.toString()
                        + "/" + fileName + "-" + x + ".jpg";
                boolean exists = (new File(candidate)).exists();
                if (!exists) {
                    break;
                }
            }

            try {
                int[] degree = new int[1];
                Uri newUri = ImageManager.addImage(
                        mContentResolver,
                        mImage.getTitle(),
                        mImage.getDateTaken(),
                        null,    // TODO this null is going to cause us to lose
                        // the location (gps).
                        directory.toString(), fileName + "-" + x + ".jpg",
                        croppedImage, null,
                        degree);

                setResult(RESULT_OK, new Intent()
                        .setAction(newUri.toString())
                        .putExtras(extras));
            } catch (Exception ex) {
                // basically ignore this or put up
                // some ui saying we failed
                Log.e(TAG, "store image fail, continue anyway", ex);
            }
        }

        final Bitmap b = croppedImage;
        mHandler.post(new Runnable() {
            public void run() {
                mImageView.clear();
                b.recycle();
            }
        });

        finish();
    }
*/



}
