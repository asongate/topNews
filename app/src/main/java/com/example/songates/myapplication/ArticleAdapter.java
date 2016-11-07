package com.example.songates.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by songates on 11/7/16.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, ArrayList<Article> articles) {

        super(context,0,articles);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Article article = getItem(position);

        if(convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.articles,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        ImageView imgUrl = (ImageView) convertView.findViewById(R.id.imgUrl);

        title.setText(article.title);
        Log.i("url", article.url);

        Picasso.with(getContext()).load(article.imgUrl).into(imgUrl);

        return convertView;
    }
}
