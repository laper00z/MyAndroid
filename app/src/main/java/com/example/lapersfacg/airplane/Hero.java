package com.example.lapersfacg.airplane;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class Hero {
    private int type;
    private int life = 1;
    private int grade;
    private int power;
    private int x;
    private int y;
    private Bitmap bitmap;
    private Context mContext;

    public Hero(Context context,int life,int type){
        mContext = context;
        this.type = type;
        this.life = life;
        this.bitmap = BitmapFactory.decodeResource(
                context.getResources(),
                R.drawable.hero2);
        this.x = 540 - bitmap.getWidth()/2;
        this.y = 1200;
        this.power = 100;
        this.grade = 0;
    }

    public void draw(Canvas canvas,Paint paint){
        canvas.drawBitmap(bitmap, x, y, paint);
    }
    int temp = 0;
    public void logic(int x,int y){
        this.x = x - bitmap.getWidth() / 2;
        this.y = y - bitmap.getHeight() / 2;;
    }

    public int getX(){
        return x;
    }

    public  int getY(){
        return y;
    }

    public int getLife(){
        return life;
    }

    public int getPower(){
        return power;
    }

    public int getType(){
        return type;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void addLife(){
        life++;
    }

    public void minusLife(){
        life--;
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
}
