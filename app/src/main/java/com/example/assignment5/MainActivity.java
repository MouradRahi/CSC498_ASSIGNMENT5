package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mylist;
    ArrayList<String> thelist;
    ArrayList<String> thelist2;
    ArrayAdapter<String> adapter;
    String[] arr;
    String[] opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView) findViewById(R.id.myList);
        try{

            SQLiteDatabase sql = this.openOrCreateDatabase("finaldb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS finals (courses VARCHAR, web_url VARCHAR)");
            sql.execSQL("INSERT INTO finals(courses, web_url) VALUES ('mobile computing', 'https://developer.android.com/docs')");

            Cursor c = sql.rawQuery("Select * from finals", null);
            int coursesIndex = c.getColumnIndex("courses");
            int wurlIndex = c.getColumnIndex("web_url");
            c.moveToFirst();
            while(c!= null){
                String course_name = c.getString(coursesIndex) + " " + c.getString(wurlIndex);
                arr= new String[c.getCount()];
                Toast.makeText(getApplicationContext(), course_name, Toast.LENGTH_LONG).show();
                c.moveToNext();
            }
            thelist = new ArrayList<String>(Arrays.asList(arr));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thelist);
            mylist.setAdapter(adapter);
            thelist = new ArrayList<String>(Arrays.asList(arr));
            thelist2 = new ArrayList<String>(Arrays.asList(opt));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,thelist2);
            mylist.setAdapter(adapter);

            mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent obj = new Intent(getApplicationContext(), MainActivity2.class);
                    obj.putExtra("courses_name", ""+thelist.get(i));
                    startActivity(obj);

                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }



    }
}