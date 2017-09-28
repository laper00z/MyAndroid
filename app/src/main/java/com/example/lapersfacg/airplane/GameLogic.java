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

        hero.logic(0,0);

        //自机子弹生成
        if(count%5==0)
            addBullet();

        //自机子弹
        for (int i = 0; i < allBullets.size(); i++) {
            Bullet bullet = allBullets.get(i);

            //自机子弹移动
            bullet.logic(0,0);

            //自机子弹碰撞测试（与敌机）
            for(int j = 0; j< allEnemys.size(); j++){
                Enemy enemy = allEnemys.get(j);
                if(enemy.isConnectionWithItem(bullet.getBitmap(),bullet.getX(),bullet.getY())==true){

                    //自机子弹消除（碰撞敌机消除）
                    allBullets.remove(i);

                    //敌机消除，计分
                    if(enemy.minusLife(hero.getPower()))
                        allEnemys.remove(j);

                }
            }

            // 自机子弹出界消除
            if(bullet.isDead())
                allBullets.remove(i);
        }

        // 敌机生成
        if(count%400==0){
            addEnemy(count/40);
        }

        // 敌机
        for(int i = 0;i < allEnemys.size();i++){
            // 敌机移动
            Enemy enemy = allEnemys.get(i);
            enemy.logic(0,0);

            // 敌机碰撞测试（与自机，不是与自机子弹）
            if(hero.isConnectionWithItem(enemy.getBitmap(),enemy.getX(),enemy.getY())){
                // 自机消除，生命减少，游戏结束判断
                hero.minusLife();
            }
            // 敌机出界消除
            if(enemy.isDead()){
                allEnemys.remove(i);
            }
        }



        // 敌机子弹生成
        // 敌机子弹移动
        // 敌机子弹碰撞测试（与自机）
        // 自机消除，生命减少，游戏结束判断
        // 敌机子弹出界消除
        count++;
    }

    private void addBullet(){
        int x = hero.getX() + hero.getBitmap().getWidth() / 2;
        int y = hero.getY();

        Bullet bullet = new Bullet(getContext(),1,x,y,hero.getPower());

        allBullets.add(bullet);
    }

    private void addEnemy(int count){
        int x = 400;
        int y = 0;
        Enemy enemy = new Enemy(getContext(),1,x,y);
        allEnemys.add(enemy);
    }

    private void addAmmo(Enemy enemy){
        int x = enemy.getX();
        int y = enemy.getY();

        Ammo ammo = new Ammo(getContext(),1,40,x,y,hero.getX(),hero.getY());

        allAmmos.add(ammo);
    }

    public void onTouchEvent(MotionEvent event1, MotionEvent event2) {

    }
}
