package com.example.songates.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

/**
 * Created by songates on 11/7/16.
 */

public class TopicsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        Toolbar topics = (Toolbar) findViewById(R.id.topics_toolbar);
        setSupportActionBar(topics);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("category");
        populateSources(topic);

    }

    private  void populateSources(String topic) {

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept","application/json"));
        RequestParams params = new RequestParams();
        params.add("language","en");
        if (topic.equalsIgnoreCase("science")) {
            topic="science-and-nature";
            params.add("category",topic);
        } else {
            params.add("category", topic);
        }
        String url ="https://newsapi.org/v1/sources";
        NewsOrgClient.get(getApplicationContext(),url,headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {

                        final ArrayList<Source> topicList = new ArrayList<Source>();
                        SourceAdapter adapter = new SourceAdapter(TopicsActivity.this,topicList);

                        try {
                            JSONArray sources = jsonObject.getJSONArray("sources");

                            for (int i=0; i<sources.length(); i++) {
                                adapter.add(new Source(sources.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        ListView listView = (ListView) findViewById(R.id.topicsList);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                Source source = topicList.get(position);
                                Intent article = new Intent(getApplicationContext(),ArticleActivity.class);
                                article.putExtra("source",source.id);
                                startActivity(article);
                            }
                        });

                    }
                });

    }
}
