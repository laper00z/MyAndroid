package com.example.lapersfacg.airplane;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Created by laper sfacg on 2017/9/24.
 */



public class GameActivity extends AppCompatActivity {
    int life=1;
    int type=1;
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this,life,type));
        //播放音乐
        /**player= MediaPlayer
                .create(GameActivity.this,
                        R.raw.bg);
        player.start();
        player.setLooping(true);
         **/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
            return true;
        else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.pause();
    }
    @Override
    protected void onStart() {
        super.onStart();
        player.start();
    }

    //程序退出之前的调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }
}