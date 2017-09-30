package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import static java.lang.Math.sqrt;

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
        if(type == 1) {
            this.speed = 50;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.bullet1);
        }
        else if(type == 2){
            this.speed = 20;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.bullet2);
        }
    }

    public void draw(Canvas canvas, Paint paint){

        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void logic(int x,int y,Bitmap enemyb,int judge){
        if(type == 1||judge == 0)
            this.y-=speed;
        else{
            double dir , dirx, diry, xi, yi;
            dirx = x+enemyb.getWidth()/2-this.x-bitmap.getWidth()/2;
            diry = y+enemyb.getHeight()/2-this.y-bitmap.getHeight()/2;
            if(dirx == 0)
                this.y-=speed;
            else{
                dir = diry/dirx;
                xi = speed/sqrt(1+dir*dir);
                yi = xi*sqrt(dir*dir);
                if(dirx < 0)
                    xi = 0-xi;
                if(diry > 0)
                    yi = 0-yi;
                this.x+=xi;
                this.y-=yi;
            }
        }
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
