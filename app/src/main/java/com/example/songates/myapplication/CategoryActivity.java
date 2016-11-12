package com.example.songates.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.category_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        ListView categories = (ListView) findViewById(R.id.categoryList);

        final ArrayList<String> list = new ArrayList<String>();

        list.add("Business");
        list.add("Entertainment");
        list.add("Gaming");
        list.add("General");
        list.add("Music");
        list.add("Science");
        list.add("Sport");
        list.add("Technology");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.categories, R.id.category,list);

        categories.setAdapter(arrayAdapter);

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Log.i("logged::", list.get(position));

                Intent i = new Intent(getApplicationContext(),TopicsActivity.class);
                i.putExtra("category", list.get(position));
                startActivity(i);
            }
        });
    }


}
