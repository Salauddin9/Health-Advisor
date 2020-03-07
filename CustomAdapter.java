package com.example.salauddinrubel.healthadviser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {

    String[] dietnames;
    Context context;
    private LayoutInflater inflater;

    CustomAdapter(Context context, String[] dietname){
        this.context = context;
        this.dietnames=dietname;
    }



    @Override
    public int getCount() {
        return dietnames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
           inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.showlist,parent,false);
        }

        TextView textView = convertView.findViewById(R.id.caloriesid);
        textView.setText(dietnames[position]);


        return convertView;
    }
}
