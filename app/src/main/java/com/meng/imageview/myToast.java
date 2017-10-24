package com.meng.imageview;

import android.content.Context;
import android.widget.Toast;

public class myToast {

    public static void mToast(Context c, String text) {
        Toast.makeText(c , text, Toast.LENGTH_SHORT).show();
    }

}

