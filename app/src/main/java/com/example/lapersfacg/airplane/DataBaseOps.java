package com.example.lapersfacg.airplane;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/12.
 */

public class DataBaseOps {

    public static void insert(String name, int score, Context context)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);//键值对，键是列名，值是希望插入到该列的值，值必须对应键的数据了类型
        contentValues.put("score",score);
        DataBaseHelper dbHelper=new DataBaseHelper(context,"game");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.insert("users",null,contentValues);
    }
    public static Cursor query(Context context)
    {
        DataBaseHelper dbHelper = new DataBaseHelper(context,"game");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users order by score DESC", null);
        return cursor;
    }
}
