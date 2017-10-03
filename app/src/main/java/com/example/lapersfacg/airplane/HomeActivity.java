package com.example.lapersfacg.airplane;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Button startGame;
    private Button rankList;
    private Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startGame=(Button)findViewById(R.id.startGame);
        rankList=(Button)findViewById(R.id.ranklist);
        exit=(Button)findViewById(R.id.exit);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomeActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        rankList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(HomeActivity.this,Ranklist.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "退出游戏", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
