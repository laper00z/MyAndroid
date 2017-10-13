package com.example.lapersfacg.airplane;

/**
 * Created by Administrator on 2017/10/12.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/1/31.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    public static final int VERSION=1;

    public DataBaseHelper(Context context,String name,SQLiteDatabase.CursorFactory cursorFactory,int version)
    {
        super(context,name,cursorFactory,version);
    }
    public DataBaseHelper(Context context,String name)
    {
        this(context,name,VERSION);
    }
    public DataBaseHelper(Context context,String name,int version)
    {
        this(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//第一次得到database对象时调用
        db.execSQL("create table users(id integer primary key autoincrement,name varchar(50),score int)");//执行sql语句
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("upgrade a database");
    }
}
