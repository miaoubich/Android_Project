package com.example.crudtable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private String name;
    private String age;
    private String phone;
    private byte[] image;

    public SQLiteHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insert data
    public void insertData(String name, String age, String phone, byte[] image){
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.image = image;
        SQLiteDatabase database = getWritableDatabase();
        //query to insert data in database
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?)"; //where 'RECORD' is the table name, it will be created in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindString(2,age);
        statement.bindString(3,phone);
        statement.bindBlob(4, image);
        statement.bindDouble(5, (double)id);

        statement.executeInsert();
    }

    //update data
    Override
    public void updateData(String name, String age, String phone, byte[] image, int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update records
        String sql = "UPDATE RECORD SET name=?, age=?, photo=?, image=? WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
