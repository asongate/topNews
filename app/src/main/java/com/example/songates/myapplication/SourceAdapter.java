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
 * Created by songates on 10/28/16.
 */

public class SourceAdapter extends ArrayAdapter<Source> {

    public SourceAdapter(Context context, ArrayList<Source> sources) {
        super(context,0,sources);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Source source = getItem(position);

        if ( convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.topics,parent,false);

        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView url = (ImageView) convertView.findViewById(R.id.url);

        name.setText(source.name);
        Picasso.with(getContext()).load(source.url).into(url);

        return convertView;
    }
}
