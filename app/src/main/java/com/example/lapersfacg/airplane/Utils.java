package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Administrator on 2017/10/10.
 */

public class Utils {
    public static Typeface typeface;
    public Utils(Context context)
    {
        typeface = Typeface.createFromAsset(context.getAssets(),"fonts/HaTian-JingZhi.ttf");
    }
    public static Typeface getFonts()
    {
        return typeface;
    }
}
