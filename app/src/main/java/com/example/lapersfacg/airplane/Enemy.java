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
    private int time = 0;
    private Bitmap bitmap;
    private Context mContext;

    private int left;
    private int right;

    public Enemy(Context context,int type,int x,int y){
        mContext = context;
        this.type = type;
        this.x = x;
        this.y = y;
        if(type == 1){
            this.speed = 20;
            this.life = type*3000;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.enemy3);
        }
        else if(type == 2){
            this.speed = 20;
            this.life = type*300;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.enemy4);
        }
        left=0;
        right=1;
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

    public int getType(){
        return type;
    }

    private int temp = 0;
    public void logic(){
        time++;
        /*
        if(type == 1){
            if(temp == 0)
                this.x-=speed;
            else
                this.x+=speed;
            if(this.x<=0||(this.x>=540&&this.x<=680))
                temp = 1;
            if((this.x>=400&&this.x<540)||this.x>=1080)
                temp = 0;
            this.y+=speed;
        }
        else if(type==2){
            ;
        }*/

        if(right==0)
        {
            this.x+=speed;
            if(this.x>1000)
            {
                right=1;
            }
        }
        else
        {
            this.x-=speed;
            if(this.x<100)
            {
                right=0;
            }
        }


    }

    public boolean isTimeEnough(){
       if(time%10==0)
            return true;
        return false;
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
