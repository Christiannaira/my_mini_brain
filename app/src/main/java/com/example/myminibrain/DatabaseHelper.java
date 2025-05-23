package com.example.myminibrain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String database_name = "mini_brain.db";
    private static final String usertable_name = "user_table";
    private static final String coursetable_name = "course_table";
    private static final String inventorytable_name = "inventory_table";
    private static final String transactiontable_name = "transaction_table";


    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + usertable_name + "(user_id INTEGER PRIMARY KEY AUTOINCREMENT, fullname VARCHAR(255), username VARCHAR(255), email VARCHAR(255), password VARCHAR(255), type VARCHAR(255))");
        db.execSQL("CREATE TABLE " + coursetable_name + "(course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name VARCHAR(255), course_category VARCHAR(255), courseSeller_id INTEGER, course_selected_id INTEGER)");
        db.execSQL("CREATE TABLE " + inventorytable_name + "(inventory_id INTEGER PRIMARY KEY AUTOINCREMENT, courseSelected_id INTEGER, courseSeller_id INTEGER, course_category VARCHAR(255))");
        db.execSQL("CREATE TABLE " + transactiontable_name + "(transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, transaction_reciept VARCHAR(255), transaction_buyerId INTEGER, transaction_sellerId INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + usertable_name);
        db.execSQL("DROP TABLE IF EXISTS " + coursetable_name);
        db.execSQL("DROP TABLE IF EXISTS " + inventorytable_name);
        db.execSQL("DROP TABLE IF EXISTS " + transactiontable_name);
        onCreate(db);
    }

    public boolean insertUser(String username, String email, String password, String type, String fullname) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT email FROM " + usertable_name + " WHERE email = ?", new String[] {email});

        if (cursor.getCount() == 0) {
            cv.put("fullname", fullname);
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
        } else {

            return false;
        }

    }

    public boolean insertCourse(String courseName, String courseCategory, int courseSellerId, int courseSelectedId){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM " + coursetable_name + " WHERE course_selected_id = ?", new String[] {String.valueOf(courseSelectedId)});

        if (cursor.getCount() == 0) {
            cv.put("course_name", courseName);
            cv.put("course_category", courseCategory);
            cv.put("courseSeller_id", courseSellerId);
            cv.put("course_selected_id", courseSelectedId);
            long result = db.insert(coursetable_name, null, cv);

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {

            return false;
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

    // display the user
    public ArrayList<String[]> getActiveUser(String activeEmail){

        ArrayList<String[]> userData = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + usertable_name + " WHERE email = ?", new String[] {activeEmail});

        if (cursor.moveToFirst()){
            String fullname = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            String[] row = {username, email, fullname};

            userData.add(row);

        } while (cursor.moveToNext());

        cursor.close();
        return userData;
    }


    public List<String[]> getAllUserData() {

        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "  + usertable_name, null);

        if (cursor.moveToFirst()) {
            do {
                int user_id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

                String[] row = {String.valueOf(user_id), username, email};

                dataList.add(row);
            } while (cursor.moveToNext());

        }

        cursor.close();
        return dataList;

    }

    public List<String[]> getAllCourse(){

        List<String[]> getAllCourseData = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "  + coursetable_name, null);

        if (cursor.moveToFirst()) {
            do {
                String courseName = cursor.getString(cursor.getColumnIndexOrThrow("course_name"));
                int courseSellerId = cursor.getInt(cursor.getColumnIndexOrThrow("courseSeller_id"));
                int course_selected_id = cursor.getInt(cursor.getColumnIndexOrThrow("course_selected_id"));

                    String[] row = {courseName, String.valueOf(course_selected_id), String.valueOf(courseSellerId)};

                getAllCourseData.add(row);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return getAllCourseData;

    }








}
