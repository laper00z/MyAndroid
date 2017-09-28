package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class BackGround {

    private Bitmap bitmap;
    private int y;
    private int y2;
    private Context mContext;

    public BackGround(Context context){
        mContext = context;
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.background1);
    }

    public void draw(Canvas canvas,Paint paint){
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    public void logic(){

    }
}
