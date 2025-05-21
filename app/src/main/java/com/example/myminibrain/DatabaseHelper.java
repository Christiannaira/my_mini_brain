package com.example.myminibrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "mini_brain.db";
    private static final String usertable_name = "user_table";


    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + usertable_name + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), type VARCHAR(255))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + usertable_name);
    }

    public boolean insertUser(String username, String email, String password, String type) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("type", type);
        long result = db.insert(usertable_name, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }



}
