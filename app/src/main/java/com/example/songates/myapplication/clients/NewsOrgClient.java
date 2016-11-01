package com.example.songates.myapplication.clients;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by songates on 10/31/16.
 */

public class NewsOrgClient {

    private  static AsyncHttpClient client = new AsyncHttpClient();

    public static void get (Context context, String url, Header[] headers,
                            RequestParams requestParams, AsyncHttpResponseHandler httpResponseHandler) {
        client.get(context,url, headers,requestParams,httpResponseHandler);
    }

}
