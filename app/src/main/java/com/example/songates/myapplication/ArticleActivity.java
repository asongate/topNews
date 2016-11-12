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

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.articles_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.home);

        Intent article = getIntent();
        String source = article.getStringExtra("source");
        populateArticles(source);
    }

    private  void populateArticles(String source) {

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept","application/json"));
        RequestParams params = new RequestParams();
        params.add("language","en");
        params.add("source", source);
        params.add("sortBy","");
        params.add("apiKey","69d6752c22d24cd9bb2e4538b43e12c1");

        String url ="https://newsapi.org/v1/articles";
        NewsOrgClient.get(getApplicationContext(),url,headers.toArray(new Header[headers.size()]),
                params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {

                        final ArrayList<Article> articleArrayList = new ArrayList<Article>();
                        ArticleAdapter articleAdapter = new ArticleAdapter(ArticleActivity.this,articleArrayList);

                        try {
                            JSONArray articles = jsonObject.getJSONArray("articles");

                            for (int i=0; i<articles.length(); i++) {
                                articleAdapter.add(new Article(articles.getJSONObject(i)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        ListView listView = (ListView) findViewById(R.id.articles);
                        listView.setAdapter(articleAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                Article article = articleArrayList.get(pos);
                                Intent detail = new Intent(getApplicationContext(),ArticleDetailActivity.class);
                                detail.putExtra("url",article.url);
                                startActivity(detail);
                            }
                        });

                    }
                });

    }
}
