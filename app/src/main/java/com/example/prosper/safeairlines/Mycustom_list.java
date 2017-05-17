package com.example.prosper.safeairlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by prosper on 4/5/2017.
 */

public class Mycustom_list extends ArrayAdapter<String> {
    public Mycustom_list(Context context, String[] objects) {
        super(context,R.layout.custom_list, objects);
    }
private class Holder{
    TextView textView;

}
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflator = LayoutInflater.from(getContext());
        convertView=inflator.inflate(R.layout.custom_list,parent,false);
        Holder holder=new Holder();
        holder.textView=(TextView)convertView.findViewById(R.id.textViewcustom_list);
        String item=getItem(position);
        holder.textView.setText(item);
        return convertView;

    }
}
