package com.example.prosper.safeairlines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import Connection.Base;

/**
 * Created by prosper on 15/5/2017.
 */

public class MyseatFirst extends ArrayAdapter<seatfirst> {




    String std="";
    public MyseatFirst(Context context, ArrayList<seatfirst> objects) {
        super(context, R.layout.list_first, objects);


    }
    private class Viewholder {

        Button a,d,g,k;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        viewholder=new Viewholder();
        LayoutInflater jusainflator = LayoutInflater.from(getContext());
        convertView = jusainflator.inflate(R.layout.list_first, parent, false);
        viewholder.a=(Button)convertView.findViewById(R.id.button_first_a);
        viewholder.d=(Button)convertView.findViewById(R.id.button_first_d);


        viewholder.g=(Button)convertView.findViewById(R.id.button_first_g);
        viewholder.k=(Button)convertView.findViewById(R.id.button_first_k);


        seatfirst st=getItem(position);

        viewholder.a.setText(st.getA());
        viewholder.a.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.a.getText().toString());
                    }
                }

        );

        viewholder.d.setText(st.getD());
        viewholder.d.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.d.getText().toString());
                    }
                }

        );


        viewholder.g.setText(st.getG());
        viewholder.g.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.g.getText().toString());
                    }
                }

        );

        viewholder.k.setText(st.getK());
        viewholder.k.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.k.getText().toString());
                    }
                }

        );






        return convertView;
    }
    public void dialoG(String string){

        std=string;
        if(!std.isEmpty()){

            AlertDialog.Builder builder=new AlertDialog.Builder(getContext(),R.style.MyAlertDialogStyle);
            builder.setTitle("Alert");
            builder.setMessage("Vous voulez Choisir ce siege"+std);
            builder.setNegativeButton("Terminer",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Continuer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sendData snd=new sendData();
                    snd.setSeat(std);
                    snd.execute();
                }
            });

            AlertDialog dialog=builder.create();
            dialog.show();



        }
    }

    private class sendData extends AsyncTask<Void,Void,Boolean>{
        String seat;
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("userinformation",Context.MODE_PRIVATE);
        int id_reservation=sharedPreferences.getInt("id_reservation",0);

        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return Base.updatesiege(id_reservation,seat);



        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){

                Intent intent=new Intent(getContext(),Operations.class);
                getContext().startActivity(intent);
            }else{

                Toast.makeText(getContext(),"il y a une erreur",Toast.LENGTH_LONG).show();

            }
        }
    }

}
