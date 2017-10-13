package com.example.lapersfacg.airplane;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private ImageButton startGame;
    private ImageButton rankList;
    private ImageButton exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataBaseHelper dbHelper=new DataBaseHelper(HomeActivity.this,"game");
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        startGame=(ImageButton)findViewById(R.id.startGame);
        rankList=(ImageButton)findViewById(R.id.ranklist);
        exit=(ImageButton)findViewById(R.id.exit);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent();
                //intent.setClass(HomeActivity.this,MainActivity.class);
               // startActivity(intent);
                showAddDialog();

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

    protected void showAddDialog() {

        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.input_name, null);
        final EditText editTextName = (EditText) textEntryView.findViewById(R.id.editTextName);
        editTextName.setTypeface(Utils.getFonts());
        AlertDialog.Builder ad1 = new AlertDialog.Builder(HomeActivity.this,R.style.AlertDialog);
        ad1.setTitle("玩家昵称:");
        ad1.setIcon(R.drawable.game_icon);
        ad1.setView(textEntryView);
        ad1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String name=editTextName.getText().toString();
                if(name.equals(""))
                {
                    Toast.makeText(HomeActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                }
                else
                {
                Intent intent=new Intent();
                intent.putExtra("user_name",name);
                intent.setClass(HomeActivity.this,MainActivity.class);
                HomeActivity.this.startActivity(intent);
                }

            }
        });
        ad1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        ad1.show();// 显示对话框


    }

}
