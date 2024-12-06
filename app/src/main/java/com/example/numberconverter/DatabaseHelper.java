package com.example.numberconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Base1.db";
    public static final String TABLE_NAME = "Base1_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "INPUT";
    public static final String COL_3 = "OUTPUT";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,INPUT TEXT,OUTPUT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Base1_table");
        onCreate(db);

    }
    public boolean insertData(String INPUT,String OUTPUT){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,INPUT);
        contentValues.put(COL_3,OUTPUT);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Base1_table",null);
        return res;
    }
    public Cursor deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("delete from Base1_table",null);
    }

}
