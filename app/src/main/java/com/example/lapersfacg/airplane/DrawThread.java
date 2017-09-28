package com.example.lapersfacg.airplane;

/**
 * Created by laper sfacg on 2017/9/24.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;

/**
 * Created by laper sfacg on 2016/7/20.
 */
public class DrawThread extends Thread {
    private SurfaceHolder holder;
    public boolean isRun;
    public Hero hero;
    private int life;
    private int type;
    private final GameLogic logic;
    Context mContext;


    public DrawThread(Context context, SurfaceHolder holder,int life,int type) {
        this.life = life;
        this.type = type;
        mContext = context;
        this.holder = holder;
        isRun = true;
        logic = new GameLogic(context,life,type);
    }



    @Override
    public void run() {
        hero = new Hero(mContext,life,type);
        while (hero.getLife()>=1) {
            Canvas c = null;
            try {
                synchronized (holder) {
                    c = holder.lockCanvas();//锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
                    logic.logic();
                    logic.draw(c);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (c != null) {
                    holder.unlockCanvasAndPost(c);//结束锁定画图，并提交改变。
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("data",mContext.MODE_PRIVATE);
        int score10 = sharedPreferences.getInt("grade"+10,000);

    }

    public void onTouchEvent(MotionEvent event1,MotionEvent event2) {
        //滑动响应
        logic.onTouchEvent(event1,event2);
    }

}
