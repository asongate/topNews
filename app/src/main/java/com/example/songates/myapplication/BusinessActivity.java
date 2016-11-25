package com.example.songates.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.example.songates.myapplication.clients.NewsOrgClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class BusinessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);

        Intent i = getIntent();
        populateBusinessSources();

    }

    private  void populateBusinessSources() {

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept","application/json"));
        RequestParams params = new RequestParams();
        params.add("language","en");
        params.add("category","business");
        String url ="https://newsapi.org/v1/sources";
        NewsOrgClient.get(getApplicationContext(),url,headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {

                        ArrayList<Source> businessList = new ArrayList<Source>();
                        SourceAdapter adapter = new SourceAdapter(BusinessActivity.this,businessList);

                        try {
                            JSONArray sources = jsonObject.getJSONArray("sources");

                            for (int i=0; i<sources.length(); i++) {
                                adapter.add(new Source(sources.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        ListView listView = (ListView) findViewById(R.id.businessList);
                        listView.setAdapter(adapter);

                    }
                });

    }



}
