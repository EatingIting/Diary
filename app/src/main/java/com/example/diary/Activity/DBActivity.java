package com.example.diary.Activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static class MyDBHelper extends SQLiteOpenHelper {

        private static volatile MyDBHelper dbHelper;

        public static MyDBHelper getInstance(Context context) {
            if (dbHelper == null) {
                synchronized (MyDBHelper.class) {
                    if (dbHelper == null) {
                        dbHelper = new MyDBHelper(context);
                    }
                }
            }
            return dbHelper;
        }

        public MyDBHelper(Context context) {
            super(context, "DiaryDB", null, 1);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Diary (title VARCHAR(20), contents text, weather VARCHAR(10));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS Diary;");
            onCreate(db);
        }
    }
}