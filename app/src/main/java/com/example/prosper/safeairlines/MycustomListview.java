package com.example.prosper.safeairlines;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SimpleFormatter;


public class MycustomListview extends ArrayAdapter<Vol> {


    Context context;

    public MycustomListview(Context context, ArrayList<Vol> objects) {
        super(context, R.layout.custom_layout, objects);

    }

    private class ViewHolder {

        TextView airportone;
        TextView airporttwo;
        TextView date_de;
        TextView date_da;
        TextView costfirst;
        TextView costbusiness;
        TextView costeconomie;
        TextView date_retour;
        TextView date_return;
        TextView heure_de;
        TextView heure_da;


    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater jusainflator = LayoutInflater.from(getContext());
        convertView = jusainflator.inflate(R.layout.custom_layout, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.airportone = (TextView) convertView.findViewById(R.id.air1);
        viewHolder.airporttwo = (TextView) convertView.findViewById(R.id.air2);
        viewHolder.date_de = (TextView) convertView.findViewById(R.id.date_depart_custom);
        viewHolder.date_da = (TextView) convertView.findViewById(R.id.date_arrive_custom);
        viewHolder.costfirst = (TextView) convertView.findViewById(R.id.textViewcostsfirstclasse);
        viewHolder.costbusiness = (TextView) convertView.findViewById(R.id.textViewbusinnesclass);
        viewHolder.costeconomie = (TextView) convertView.findViewById(R.id.textViewclasseconomy);
        viewHolder.date_retour = (TextView) convertView.findViewById(R.id.textViewdate_retour);
        viewHolder.date_return = (TextView) convertView.findViewById(R.id.textViewdate_return);
        viewHolder.heure_de=(TextView)convertView.findViewById(R.id.custom_heure);
        viewHolder.heure_da=(TextView)convertView.findViewById(R.id.custom_heure_da);

        Vol vol = getItem(position);
        boolean retour = false;
        java.util.Date dateee = vol.getDate_retour();


        if (vol.getType_vol().equals("simple")) {
            viewHolder.airportone.setText(vol.getAeroport_de());
            viewHolder.airporttwo.setText(vol.getAeroport_da());
            viewHolder.date_retour.setText("");
            viewHolder.date_return.setText("");
            viewHolder.heure_de.setText(vol.getHeure_de());
            viewHolder.heure_da.setText(vol.getHeure_da());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy");
            String stringone = simpleDateFormat.format(vol.getDate_da());
            String stringtwo = simpleDateFormat.format(vol.getDate_de());
            double business = vol.getTarrif() + 0.5 * vol.getTarrif();
            double firstclass = vol.getTarrif() + 0.75 * vol.getTarrif();
            viewHolder.costfirst.setText("US " + firstclass);
            viewHolder.costbusiness.setText("US  " + business);
            viewHolder.costeconomie.setText("US " + vol.getTarrif());
            viewHolder.date_de.setText(stringtwo);
            viewHolder.date_da.setText(stringone);
            convertView.setTag(new Integer(vol.getId_vol()));


            return convertView;
        } else {


            viewHolder.airportone.setText(vol.getAeroport_de());
            viewHolder.airporttwo.setText(vol.getAeroport_da());


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd,yyyy");
            String stringone = simpleDateFormat.format(vol.getDate_da());
            String stringtwo = simpleDateFormat.format(vol.getDate_de());
            String stringthree = simpleDateFormat.format(vol.getDate_retour());
            viewHolder.heure_de.setText(vol.getHeure_de());
            viewHolder.heure_da.setText(vol.getHeure_da());
            double business = vol.getTarrif() + 1.5 * vol.getTarrif();
            double firstclass = vol.getTarrif() + 1.75 * vol.getTarrif();
            viewHolder.costfirst.setText("US" + firstclass);
            viewHolder.costbusiness.setText("US " + business);
            viewHolder.costeconomie.setText("US " + vol.getTarrif());
            viewHolder.date_return.setText(stringthree);
            viewHolder.date_de.setText(stringtwo);
            viewHolder.date_da.setText(stringone);
            convertView.setTag(new Integer(vol.getId_vol()));


            return convertView;
        }
    }
}