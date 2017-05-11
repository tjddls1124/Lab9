package com.example.sungin.lab9;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SungIn on 2017-05-10.
 */

public class urlAdapter extends BaseAdapter {
    ArrayList<webSite> data;
    Context context;

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
        if(convertView == null)
            convertView = inflater.inflate(R.layout.listviewlayout, null);
        TextView textView = (TextView)convertView.findViewById(R.id.textView);
        Button button = (Button)convertView.findViewById(R.id.button);
        textView.setText("<"+data.get(position).getSiteName()+">"+data.get(position).getUrl());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity.class);
                intent.putExtra("url",data.get(position).getUrl());
                context.startActivity(intent);
            }
        });



        return convertView;
    }
}
