package com.example.myminibrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "mini_brain.db";
    private static final String usertable_name = "user_table";
    private static final String coursetable_name = "course_table";
    private static final String inventorytable_name = "inventory_table";
    private static final String transactiontable_name = "transaction_table";


    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + usertable_name + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), type VARCHAR(255))");
        db.execSQL("CREATE TABLE " + coursetable_name + "(course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name VARCHAR(255), course_category VARCHAR(255), courseSeller_id INTEGER)");
        db.execSQL("CREATE TABLE " + inventorytable_name + "(inventory_id INTEGER PRIMARY KEY AUTOINCREMENT, courseSelected_id INTEGER, courseSeller_id INTEGER, course_category VARCHAR(255))");
        db.execSQL("CREATE TABLE " + transactiontable_name + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, transaction_reciept VARCHAR(255), transaction_buyerId INTEGER, transaction_sellerId INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + usertable_name);
        db.execSQL("DROP TABLE IF EXISTS " + coursetable_name);
        db.execSQL("DROP TABLE IF EXISTS " + inventorytable_name);
        db.execSQL("DROP TABLE IF EXISTS " + transactiontable_name);
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

    public boolean checkAccount(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + usertable_name + " WHERE email = ? AND password = ?", new String[] {email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }



}
