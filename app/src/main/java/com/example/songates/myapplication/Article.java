package com.example.songates.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by songates on 11/7/16.
 */

public class Article {

    public String title;
    public String url;
    public String imgUrl;

    public Article(JSONObject object) {

        try {
            this.title = object.getString("title");
            this.url = object.getString("url");
            this.imgUrl = object.getString("urlToImage");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
