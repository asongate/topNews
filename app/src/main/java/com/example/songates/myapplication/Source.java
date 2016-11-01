package com.example.songates.myapplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by songates on 10/28/16.
 */

public class Source {

    public String name;
    public String url;

    public Source(JSONObject object) {
        try {
            this.name = object.getString("name");
            this.url = object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
