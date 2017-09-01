package com.example.ayushi.listicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

/**
 * Created by ayushi on 23/8/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "todo.db";
    public static final String TABLE_NAME = "task_table";
    public static final String COL_2 = "taskDesc";
    SQLiteDatabase db;


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String task){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, task);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String taskId, String taskDesc){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, taskDesc);
        long result = db.update(TABLE_NAME, contentValues, "id="+taskId, null);

        if(result == -1){ return false; }
        else { return true; }
    }

    public boolean deleteData(int taskId){
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id="+taskId, null);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);
        return result;
    }

    public int getId(String taskDesc){
        db = this.getWritableDatabase();
        Cursor id = db.rawQuery("select id from " + TABLE_NAME + " where taskDesc=" + "'"+ taskDesc+ "'", null);
        id.moveToNext();
        return id.getInt(0);
    }
}
