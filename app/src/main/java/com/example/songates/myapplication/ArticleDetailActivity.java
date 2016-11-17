package com.example.songates.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by songates on 11/7/16.
 */

public class ArticleDetailActivity extends AppCompatActivity {

    ProgressBar progressBar;
    WebView webView;
    private String url;
    private String source;
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.articledetails_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent article = getIntent();
        url = article.getStringExtra("url");
        source = article.getStringExtra("source");
        category = article.getStringExtra("category");
        webView = (WebView) findViewById(R.id.detail);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebClient());
        webView.loadUrl(url);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent backToArticles = new Intent(getApplicationContext(),ArticleActivity.class);
                backToArticles.putExtra("source",source);
                backToArticles.putExtra("category",category);
                NavUtils.navigateUpTo(this,backToArticles);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class myWebClient extends WebViewClient {


        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view,url);
            progressBar.setVisibility(View.GONE);

        }
    }
}
