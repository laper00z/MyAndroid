package com.example.lapersfacg.airplane;

import android.app.*;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Ranklist extends android.app.ListActivity {
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranklist);
        ArrayList<HashMap<String,String>> mylist=new ArrayList<>();

        Cursor cursor=DataBaseOps.query(Ranklist.this);
        while (cursor.moveToNext())
        {
            HashMap<String,String> map=new HashMap<>();
            String name=cursor.getString(cursor.getColumnIndex("name"));
            int score=cursor.getInt(cursor.getColumnIndex("score"));
            map.put("user_name",name);
            map.put("user_score",score+"");
            mylist.add(map);
        }


        SimpleAdapter adapter=new SimpleAdapter(Ranklist.this,mylist,R.layout.rank,new String[]{"user_name","user_score"},new int[]{R.id.user_name,R.id.user_score});
        setListAdapter(adapter);
        btn=(ImageButton)findViewById(R.id.back_menu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
