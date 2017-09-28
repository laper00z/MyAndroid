package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class Bullet {

    private int type;
    private int speed;
    private int x;
    private int y;
    private int power;
    private Bitmap bitmap;
    private Context mContext;

    public Bullet(Context context,int type,int x,int y,int power){
        mContext = context;
        this.type = type;
        this.x = x;
        this.y = y;
        this.power = power;
        this.speed = 50*(2+type);
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.bullet1);
    }

    public void draw(Canvas canvas, Paint paint){

        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void logic(int x,int y){
        this.y-=speed;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public boolean isDead(){
        if(y<-bitmap.getHeight())
            return true;
        if(x>1080)
            return true;
        if(x+bitmap.getWidth()<0)
            return true;

        return false;
    }
}
