package com.example.lapersfacg.airplane;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by laper sfacg on 2017/9/24.
 */


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private DrawThread drawThread;
    private GestureDetector mGestureDetector;


    public GameView(Context context,int life,int type) {
        super(context);
        init(life,type);
    }

    private void init(int life,int type) {
        holder = this.getHolder();
        holder.addCallback(this);
        drawThread = new DrawThread(getContext(),holder,life,type);//创建一个绘图线程
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread.isRun = true;
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        drawThread.isRun = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        drawThread.onTouchEvent(event);
        return true;
    }
}
