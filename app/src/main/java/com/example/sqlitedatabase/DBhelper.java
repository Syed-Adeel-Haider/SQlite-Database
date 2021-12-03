package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "login.db";

    public DBhelper(Context context ) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
myDB.execSQL("create Table users(email TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
myDB.execSQL("drop Table if exists users");
    }
    public boolean insertData(String email,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = myDB.insert("users", null,contentValues);
        return result != -1;
    }
    public boolean checkemail(String email){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where email = ?",new String[] {email});
        if (cursor.getCount()>0)
            return true;
            else
                return false;
    }
    public boolean checkemailpassword(String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from users where email = ? and password = ?", new String[]{email,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
