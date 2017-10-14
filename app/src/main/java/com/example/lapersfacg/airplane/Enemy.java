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
    private int circletime[]={24,28,32,36,40,124,128,132,136,140,
            224,228,232,236,240,324,328,332,336,340};
    private int linetime1[]={22,24,26,28,30,122,124,126,128,130,
            222,224,226,228,230,322,324,326,328,330};
    private int linetime2[]={32,34,36,38,40,132,134,136,138,140,
            232,234,236,238,240,332,334,336,338,340};
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
            this.life = type*30000;
            bitmap = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.enemy3);
        }
        else if(type == 2){
            this.life = type*30000;
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

    public void logic(){
        if(time==420)
            time = 21;
        //移动
        if(time<20) {
            speed = 20;
            y += speed;
        }
        else if(time>110&&time<120) {
            speed = 30;
            x += speed;
        }
        else if(time>210&&time<220){
            speed = 30;
            x -= speed;
        }else if(time>310&&time<320){
            speed = 30;
            x -= speed;
        }else if(time>410&&time<420) {
            speed = 30;
            x += speed;
        }
        time++;
    }

    public boolean isTime1(){
        for(int i=0;i<circletime.length;i++){
            if(time==circletime[i])
                return true;
        }
        return false;
    }

    public boolean isTime2(){
        for(int i=0;i<linetime1.length;i++){
            if(time==linetime1[i])
                return true;
        }
        return false;
    }

    public boolean isTime3(){
        for(int i=0;i<linetime1.length;i++){
            if(time==linetime2[i])
                return true;
        }
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
