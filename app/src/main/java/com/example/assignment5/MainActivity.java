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

    ListView my_list;
    ArrayList<String> the_list;
    ArrayList<String> the_list2;
    ArrayAdapter<String> adapter;
    String[] arr;
    String[] opt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_list = (ListView) findViewById(R.id.myList);
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
            the_list = new ArrayList<String>(Arrays.asList(arr));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_list);
            my_list.setAdapter(adapter);
            the_list = new ArrayList<String>(Arrays.asList(arr));
            the_list2 = new ArrayList<String>(Arrays.asList(opt));

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,the_list2);
            my_list.setAdapter(adapter);

            my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent obj = new Intent(getApplicationContext(), MainActivity2.class);
                    obj.putExtra("coursee_name", ""+the_list.get(i));
                    startActivity(obj);

                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }



    }
}