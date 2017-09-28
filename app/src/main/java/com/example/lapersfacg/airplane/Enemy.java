package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class Enemy {

    private int type;
    private int speed;
    private int x;
    private int y;
    private int life;
    private Bitmap bitmap;
    private Context mContext;

    public Enemy(Context context,int type,int x,int y){
        mContext = context;
        this.type = type;
        this.x = x;
        this.y = y;
        this.life = type*300;
        bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.enemy3);
    }

    public void draw(Canvas canvas, Paint paint){

        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public boolean minusLife(int power){
        life-=power;
        if(life<=0)
            return true;
        return false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void logic(int x,int y){
        this.y+=20;
    }

    public boolean isConnectionWithItem(Bitmap iBitmap,int ItemX,int ItemY){
        //右边
        if(ItemX>x+bitmap.getWidth())
            return false;
        //下边
        if(ItemY>y+bitmap.getHeight())
            return false;
        //左边
        if(ItemX+iBitmap.getWidth()<x)
            return false;
        //上边
        if(ItemY+iBitmap.getHeight()<y)
            return false;

        return true;
    }

    public boolean isDead(){
        if(y<-bitmap.getHeight())
            return true;
        if(y>1920)
            return true;
        if(x>1080)
            return true;
        if(x+bitmap.getWidth()<0)
            return true;

        return false;
    }
}
