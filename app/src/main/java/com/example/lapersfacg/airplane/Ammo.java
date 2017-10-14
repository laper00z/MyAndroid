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
    private Bitmap bitmap;
    private Context mContext;
    private int angle;//子弹运行角度
    private boolean tag;
    private int temp = 0;
    public Ammo(Context context, int type,int speed, int x, int y, int HeroX, int HeroY,Bitmap enemyBitmap,Bitmap heroBitmap,int angle){
        mContext = context;
        this.type = type;
        this.speed = speed;
        if(this.type==1) {
            this.angle=angle;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ammo1);
        }
        else {
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.ammo2);
            int ammoBitmapX = x+enemyBitmap.getWidth()/2-bitmap.getWidth()/2;
            int ammoBitmapY = y+enemyBitmap.getHeight()/2-bitmap.getHeight()/2;
            int ammoPotX = GetXY.PotX(bitmap,ammoBitmapX);
            int ammoPotY = GetXY.PotY(bitmap,ammoBitmapY);
            int heroBitmapX = HeroX;
            int heroBitmapY = HeroY;
            int heroPotX = GetXY.PotX(heroBitmap,heroBitmapX);
            int heroPotY = GetXY.PotY(heroBitmap,heroBitmapY);
            if(heroPotX - ammoPotX == 0&&heroPotY - ammoPotY>0) {
                this.angle = 90;
            }
            else if(heroPotX - ammoPotX == 0&&heroPotY - ammoPotY<0){
                this.angle = 270;
            }
            else {
                if(heroPotX - ammoPotX > 0&&heroPotY - ammoPotY > 0)
                    this.angle = (int) (Math.atan(((double)(heroPotY - ammoPotY)
                            / (double)(heroPotX - ammoPotX))) * 180 / 3.1415926);
                else if(heroPotX - ammoPotX < 0&&heroPotY - ammoPotY > 0)
                    this.angle = 180+(int) (Math.atan(((double)(heroPotY - ammoPotY)
                            / (double)(heroPotX - ammoPotX))) * 180 / 3.1415926);
                else if(heroPotX - ammoPotX < 0&&heroPotY - ammoPotY <= 0)
                    this.angle = 180+(int) (Math.atan(((double)(heroPotY - ammoPotY)
                            / (double)(heroPotX - ammoPotX))) * 180 / 3.1415926);
                else if(heroPotX - ammoPotX > 0&&heroPotY - ammoPotY <= 0)
                    this.angle = 360+(int) (Math.atan(((double)(heroPotY - ammoPotY)
                            / (double)(heroPotX - ammoPotX))) * 180 / 3.1415926);
            }
        }
       this.x=x+enemyBitmap.getWidth()/2-bitmap.getWidth()/2;
       this.y=y+enemyBitmap.getHeight()/2-bitmap.getHeight()/2;
       tag=true;
    }

    public void draw(Canvas canvas,Paint paint){
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void logic(){
        if(this.angle==0){
            x+=speed;
        }else if(this.angle==90){
            y+=speed;
        }else if(this.angle==180){
            x-=speed;
        }else if(this.angle==270){
            y-=speed;
        }else {
            x+=speed*Math.cos(angle*3.1415926/180);
            y+=speed*Math.sin(angle*3.1415926/180);
        }
        /*
        圆形弹幕
         */
       // x+=speed;
        //y+=speed*dir;
        /*
        x=(int)(x+(speed*Math.cos(angle*3.1415926/180)));
        y=(int)(y+(speed*Math.sin(angle*3.1415926/180)));
        */

        /*
       // 螺旋扩散弹幕
        double t=(angle+120)*3.1415926/180;
        if(tag)
        {
            x=(int)(x+(0.5*angle)*Math.cos(angle*3.1415926/180));
            y=(int)(y+(0.5*angle)*Math.sin(angle*3.1415926/180));
            tag=false;
        }
        x=(int)(x+(speed)*Math.cos(t));
        y=(int)(y+(speed)*Math.sin(t));
        */
        /*
        //星形线
        if(tag)
        {
            x=(int)(x+100*Math.pow(Math.cos(angle*3.1415926/180),3));
            y=(int)(y+100*Math.pow(Math.sin(angle*3.1415926/180),3))+50;
            tag=false;
        }
        y+=speed;
        */
    }

    public boolean isDead(){
        if(y<-bitmap.getHeight())
            return true;
        if(x>1080)
            return true;
        if(x+bitmap.getWidth()<0)
            return true;
        if(y>1920)
            return true;

        return false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }
}
