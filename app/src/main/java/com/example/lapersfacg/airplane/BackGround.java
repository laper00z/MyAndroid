package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.WindowManager;

import static java.security.AccessController.getContext;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class BackGround {

    private Bitmap bitmap;
    private int y;
    private int y2;
    private Context mContext;
    private int display_x;//手机屏幕宽度
    private int display_y;//手机屏幕高度

    public BackGround(Context context){
        mContext = context;
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.background1);
        y=0;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display_x = wm.getDefaultDisplay().getWidth();
        display_y = wm.getDefaultDisplay().getHeight();

    }

    public void draw(Canvas canvas,Paint paint){
        //canvas.drawBitmap(bitmap,0,0,paint);
        canvas.drawBitmap(bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(0,-display_y+y,display_x,y),paint);
        canvas.drawBitmap(bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(0,y,display_x,display_y+y),paint);
    }

    public void logic(){
        y=y+5;
        if(y>=display_y)
            y=0;

    }
}
