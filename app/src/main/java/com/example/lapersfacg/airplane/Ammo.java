package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class Ammo {

    private int type;
    private int speed;
    private int x;
    private int y;
    private double dir;
    private Bitmap bitmap;
    private Context mContext;

    public Ammo(Context context, int type, int speed, int x, int y, int HeroX, int HeroY){
        mContext = context;
        this.type = type;
        this.speed = speed;
        this.x = x;
        this.y = y;
        dir = (HeroY - y)/(HeroX - x);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.ammo1);
    }

    public void draw(Canvas canvas,Paint paint){
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void logic(){
        x+=speed;
        y+=speed*dir;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
