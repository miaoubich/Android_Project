package com.example.crudtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context,
                        @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //insert data
    public void insertData(String name, String age, String phone, byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        //query to insert data in database
        String sql = "INSERT INTO RECORD VALUES(NULL, ?, ?, ?, ?)"; //where 'RECORD' is the table name, it will be created in mainActivity

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,name);
        statement.bindString(2,age);
        statement.bindString(3,phone);
        statement.bindBlob(4, image);

        statement.executeInsert();
    }

    //update data
    public void updateData(String name, String age, String phone, byte[] image, int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to update records
        String sql = "UPDATE RECORD SET name=?, age=?, phone=?, image=? WHERE id=?";

        SQLiteStatement updateStatement = database.compileStatement(sql);

        updateStatement.bindString(1,name);
        updateStatement.bindString(2,age);
        updateStatement.bindString(3,phone);
        updateStatement.bindBlob(4, image);
        updateStatement.bindDouble(5, (double)id);

        updateStatement.executeUpdateDelete();
        database.close();
    }

    //delete Data
    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM RECORD WHERE id=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
