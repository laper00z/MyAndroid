package com.example.lapersfacg.airplane;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.media.MediaPlayer;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    public static int life = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this,1,2));
        //setContentView(R.layout.activity_main);

        /**findViewById(R.id.start_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.list_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.exit_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //setContentView(new GameView(this));
        //播放音乐
        //player = MediaPlayer
        //        .create(MainActivity.this,
        //                R.raw.bgm);
        player.start();
        player.setLooping(true);
         **/
    }

    @Override
    protected void onStop() {
        super.onStop();
        //player.pause();
    }
    @Override
    protected void onStart() {
        super.onStart();
        //player.start();
    }

    //程序退出之前的调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //player.stop();
        //player.release();
    }
}
