package com.example.sungin.lab9;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SungIn on 2017-05-10.
 */

public class urlAdapter extends BaseAdapter {
    ArrayList<webSite> data;
    Context context;
    public static webSite a;

    public urlAdapter(ArrayList<webSite> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.listviewlayout, null);
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText("<" + data.get(position).getSiteName() + ">");
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);
        textView2.setText(data.get(position).getUrl());


        return convertView;
    }
}
