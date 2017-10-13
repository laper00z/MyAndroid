package com.example.lapersfacg.airplane;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadActivity extends AppCompatActivity {
    private TextView tv;
    private TextView line;
    private ImageView down;
    private ImageView up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        tv=(TextView)findViewById(R.id.danmuText);
        line=(TextView)findViewById(R.id.underline);
        down=(ImageView) findViewById(R.id.downjet);
        up=(ImageView) findViewById(R.id.upjet);

        Utils utils=new Utils(this);
        tv.setTypeface(Utils.getFonts());
        Animation alpha_bg = AnimationUtils.loadAnimation(this,R.anim.alpha_bg);
        Animation alpha_logo = AnimationUtils.loadAnimation(this,R.anim.alpha_logo);
        Animation upjet = AnimationUtils.loadAnimation(this,R.anim.upjet);
        Animation downjet = AnimationUtils.loadAnimation(this,R.anim.downjet);

        line.setAnimation(alpha_logo);
        tv.setAnimation(alpha_bg);
        up.setAnimation(upjet);
        down.setAnimation(downjet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadActivity.this,HomeActivity.class);
                startActivity(intent);
                LoadActivity.this.finish();
            }
        },3900);

    }
}
