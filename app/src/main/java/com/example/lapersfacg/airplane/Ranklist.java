package com.example.lapersfacg.airplane;

import android.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Ranklist extends android.app.ListActivity {
    //private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranklist);

        ArrayList<HashMap<String,String>> mylist=new ArrayList<>();
        HashMap<String,String> map1=new HashMap<>();
        HashMap<String,String> map2=new HashMap<>();
        HashMap<String,String> map3=new HashMap<>();
        HashMap<String,String> map4=new HashMap<>();
        HashMap<String,String> map5=new HashMap<>();
        HashMap<String,String> map6=new HashMap<>();
        map1.put("user_name","尼古拉斯.赵四");
        map1.put("user_score","1200");
        map2.put("user_name","瑟曦.兰尼斯特");
        map2.put("user_score","1589");
        map3.put("user_name","洛拉斯.提利尔");
        map3.put("user_score","1972");
        map4.put("user_name","丹妮莉丝.坦格利安");
        map4.put("user_score","1200");
        map5.put("user_name","莱安娜.莫尔蒙");
        map5.put("user_score","1589");
        map6.put("user_name","");
        map6.put("user_score","1972");
        mylist.add(map1);
        mylist.add(map2);
        mylist.add(map3);

        SimpleAdapter adapter=new SimpleAdapter(Ranklist.this,mylist,R.layout.rank,new String[]{"user_name","user_score"},new int[]{R.id.user_name,R.id.user_score});

       // listView=(ListView)findViewById(R.id.list);
        setListAdapter(adapter);

    }
}
