package com.example.prosper.safeairlines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import Connection.Base;

import static android.R.attr.resource;

/**
 * Created by prosper on 13/5/2017.
 */

public class Custom_list_economy extends ArrayAdapter<seat>{

String std="";
    public Custom_list_economy(Context context, ArrayList<seat> objects) {
        super(context, R.layout.list_eco, objects);


    }
    private class Viewholder {

        Button a,c,d,e,f,g,h,k;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Viewholder viewholder;
        viewholder=new Viewholder();
        LayoutInflater jusainflator = LayoutInflater.from(getContext());
        convertView = jusainflator.inflate(R.layout.list_eco, parent, false);
        viewholder.a=(Button)convertView.findViewById(R.id.button_a);
        viewholder.d=(Button)convertView.findViewById(R.id.button_d);
        viewholder.c=(Button)convertView.findViewById(R.id.button_c);
        viewholder.e=(Button)convertView.findViewById(R.id.button_e);
        viewholder.f=(Button)convertView.findViewById(R.id.button_f);
        viewholder.g=(Button)convertView.findViewById(R.id.button_g);
        viewholder.h=(Button)convertView.findViewById(R.id.button__h);
        viewholder.k=(Button)convertView.findViewById(R.id.button_k);
        seat st=getItem(position);

        viewholder.a.setText(st.getA());
        viewholder.a.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                   dialoG(viewholder.a.getText().toString());
                    }
                }

        );
        viewholder.c.setText(st.getC());

        viewholder.c.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.c.getText().toString());
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
        viewholder.e.setText(st.getE());
        viewholder.e.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.e.getText().toString());
                    }
                }

        );
        viewholder.f.setText(st.getF());
        viewholder.f.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.f.getText().toString());
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
        viewholder.h.setText(st.getH());
        viewholder.h.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialoG(viewholder.h.getText().toString());
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
