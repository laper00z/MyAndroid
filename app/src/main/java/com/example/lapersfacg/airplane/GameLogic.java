package com.example.lapersfacg.airplane;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by laper sfacg on 2017/9/24.
 */

public class GameLogic {
    private Context context;
    private BackGround backGround;
    private Paint paint;
    private boolean flag;
    private boolean isPressed;

    Hero hero;
    ArrayList<Enemy> allEnemys = new ArrayList<Enemy>();
    ArrayList<Ammo> allAmmos = new ArrayList<Ammo>();
    ArrayList<Bullet> allBullets = new ArrayList<Bullet>();
    ArrayList<Prop> allProps = new ArrayList<Prop>();
    public Context getContext() {
        return context;
    }

    public GameLogic(Context context,int life,int type) {
        this.context = context;
        backGround = new BackGround(context);
        paint = new Paint();
        hero = new Hero(context,life,type);
        flag=true;
        isPressed=false;
    }

    public void draw(Canvas canvas){
        backGround.draw(canvas,paint);
        hero.draw(canvas,paint);

        for (int i = 0; i < allEnemys.size(); i++) {
            Enemy enemy = allEnemys.get(i);
            enemy.draw(canvas, paint);
        }

        for (int i = 0; i < allBullets.size(); i++) {
            Bullet bullet = allBullets.get(i);
            bullet.draw(canvas, paint);
        }

        for (int i = 0; i < allAmmos.size(); i++) {
            Ammo ammo = allAmmos.get(i);
            ammo.draw(canvas, paint);
        }
    }

    int count = 0;
    public void logic(){
        //背景移动
        backGround.logic();

        //hero.logic(0,0);

        //自机子弹生成
        if((hero.getType()==1&&count%5==0)||(hero.getType()==2&&count%10==0))
            addBullet(hero.getType());

        //自机子弹碰撞测试（与敌机）
        for (int i = 0; i < allBullets.size(); i++) {
            Bullet bullet = allBullets.get(i);
            for (int j = 0; j < allEnemys.size(); j++) {
                Enemy enemy = allEnemys.get(j);
                if (enemy.isConnectionWithItem(bullet.getBitmap(), bullet.getX(), bullet.getY()) == true) {

                    //自机子弹消除（碰撞敌机消除）
                    allBullets.remove(i);
                    i--;
                    //敌机闪烁，表示被击中

                    //敌机消除，计分
                    if (enemy.minusLife(hero.getPower()))
                        allEnemys.remove(j);
                    break;
                }
            }
        }

        //自机子弹移动
        for (int i = 0; i < allBullets.size(); i++) {
            Bullet bullet = allBullets.get(i);
            if(allEnemys.size()==0)
                bullet.logic(0,0,null,0);
            else {
                Enemy enemy = allEnemys.get(0);
                bullet.logic(enemy.getX(), enemy.getY(),enemy.getBitmap(),1);
            }
        }

        for(int i = 0;i<allBullets.size();i++) {
            Bullet bullet = allBullets.get(i);
            // 自机子弹出界消除
            if (bullet.isDead())
                allBullets.remove(i);
        }

        // 敌机生成
        if(count%100==0&&flag){
           addEnemy(count/4);
            flag=false;
            }

        // 敌机移动
        for(int i = 0;i < allEnemys.size();i++){
            Enemy enemy = allEnemys.get(i);
            enemy.logic();
        }

        // 敌机碰撞测试（与自机，不是与自机子弹）
        for(int i = 0;i < allEnemys.size();i++){
            Enemy enemy = allEnemys.get(i);
            if(hero.isConnectionWithItem(enemy.getBitmap(),enemy.getX(),enemy.getY())){
                // 自机消除，生命减少，游戏结束判断
                hero.minusLife();
            }
        }
        // 敌机出界消
        for(int i = 0;i < allEnemys.size();i++){
            Enemy enemy = allEnemys.get(i);
            if(enemy.isDead()){
                allEnemys.remove(i);
            }
        }

        // 敌机子弹生成
        for(int i = 0;i < allEnemys.size();i++){
            Enemy enemy = allEnemys.get(i);
            if(enemy.isTime1())
                produceCircle(enemy);
            if(enemy.isTime2())
                addAmmo(enemy, 2, 0, 60, 0);
            if(enemy.isTime3())
                addAmmo(enemy, 2, 0, -60, 0);

        }

        // 敌机子弹移动
        for(int i = 0;i < allAmmos.size();i++) {
            Ammo ammo = allAmmos.get(i);
            ammo.logic();
        }

        // 敌机子弹碰撞测试（与自机）
        for(int i = 0;i < allAmmos.size();i++) {
            Ammo ammo = allAmmos.get(i);
            if(hero.isConnectionWithItem(ammo.getBitmap(),ammo.getX(),ammo.getY())){
                // 自机消除，生命减少，游戏结束判断
            }
        }

        // 敌机子弹出界消除
        for(int i = 0;i < allAmmos.size();i++) {
            Ammo ammo = allAmmos.get(i);
            if(ammo.isDead())
                allAmmos.remove(i);
        }
        count++;
    }

    private void produceCircle(Enemy enemy)
    {
        //产生环形弹幕
        for(int i=0;i<36;i++)
        {
            addAmmo(enemy,1 ,10*i,0,0);
        }
    }

    private void addBullet(int type){
        int x = hero.getX() + hero.getBitmap().getWidth() / 2 - 50;
        int y = hero.getY();

        Bullet bullet1 = new Bullet(getContext(),type,x-50,y,hero.getPower());
        Bullet bullet2 = new Bullet(getContext(),type,x+50,y,hero.getPower());
        allBullets.add(bullet1);
        allBullets.add(bullet2);
    }

    private void addEnemy(int count){
        int x = 400;
        int y = 0;//修改过
        Enemy enemy = new Enemy(getContext(),2,x,y);
        allEnemys.add(enemy);
    }

    private void addAmmo(Enemy enemy,int type,int angle,int movX,int movY){
        int x = enemy.getX()+movX;
        int y = enemy.getY()+movY;

        Ammo ammo = new Ammo(getContext(),type,20,x,y,hero.getX(),hero.getY(),enemy.getBitmap(),hero.getBitmap(),angle);
        allAmmos.add(ammo);


    }

    public void onTouchEvent(MotionEvent event) {
        //if(hero.life<=0){
        //    hero.againButtonClick(getContext(),
        //            event);
        //}
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(event.getX()>hero.getX()&&event.getX()<hero.getX()+hero.getBitmap().getWidth()&&event.getY()>hero.getY()&&event.getY()<hero.getY()+hero.getBitmap().getHeight())
            {
                hero.logic((int) event.getX(), (int) event.getY());
                isPressed=true;
            }
        }
        else if(event.getAction()==MotionEvent.ACTION_MOVE&&isPressed)
        {
            hero.logic((int) event.getX(), (int) event.getY());
        }
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            isPressed=false;
        }

    }
}
