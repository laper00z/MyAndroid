package com.example.lapersfacg.airplane;

import android.graphics.Bitmap;

/**
 * Created by laper sfacg on 2017/10/13.
 */

public final class GetXY {
    public static int BitmapX(Bitmap bitmap, int potX){
        return potX-bitmap.getWidth()/2;
    }
    public static int BitmapY(Bitmap bitmap,int potY){
        return potY-bitmap.getHeight()/2;
    }
    public static int PotX(Bitmap bitmap,int bitmapX){
        return bitmapX+bitmap.getWidth()/2;
    }
    public static int PotY(Bitmap bitmap,int bitmapY){
        return bitmapY+bitmap.getHeight()/2;
    }
}
