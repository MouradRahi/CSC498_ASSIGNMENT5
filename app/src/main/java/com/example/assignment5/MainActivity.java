package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("finaldb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS finals (courses VARCHAR, web_url VARCHAR)");
            sql.execSQL("INSERT INTO finals(courses, web_url) VALUES ('mobile computing', 'https://developer.android.com/docs')");

            Cursor c = sql.rawQuery("Select * from finals", null);
            int coursesIndex = c.getColumnIndex("courses");
            int wurlIndex = c.getColumnIndex("web_url");
            c.moveToFirst();
            while(c!= null){
                String name = c.getString(coursesIndex) + " " + c.getString(wurlIndex);
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
                c.moveToNext();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}