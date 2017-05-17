package com.example.prosper.safeairlines;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Connection.Base;

public class Payer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payer);

    }



    public  void onPayer_solde(View v){

        Log.i("started","mr j");
        SharedPreferences sharedPreferences=getSharedPreferences("userinformation",MODE_PRIVATE);
      final  double cost=Double.parseDouble(sharedPreferences.getString("costs",""));
        final double solde=Double.parseDouble(sharedPreferences.getString("solde",""));

        if(solde>cost){
            Log.i("started","the mission");


        AlertDialog.Builder builder = new AlertDialog.Builder(Payer.this, R.style.MyAlertDialogStyle);
        builder.setTitle("Payer Par Solde");
        builder.setMessage("Vous voulez payer vraiement?");

        builder.setPositiveButton("yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
               onpayerSolde onsolde=new onpayerSolde();
                onsolde.setDiffence_sold(solde-cost);
                onsolde.execute();


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        AlertDialog  dialog=builder.create();
            dialog.show();

        }else{



            AlertDialog.Builder builder = new AlertDialog.Builder(Payer.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Error");
            builder.setMessage("Votre solde est insuffisant");


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();



        }

    }
    public void onPayer_points(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("userinformation",MODE_PRIVATE);
        double cost=Double.parseDouble(sharedPreferences.getString("costs",""));
       final int points=sharedPreferences.getInt("points",0);

        final int points_required=(int)Math.round(cost*12);
        if(points>points_required){

        AlertDialog.Builder builder = new AlertDialog.Builder(Payer.this, R.style.MyAlertDialogStyle);
        builder.setTitle("Payer Par Points");
        builder.setMessage("are you sure you want to pay");

        builder.setPositiveButton("yes",new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                onpayerPoints onpayerpoints =new onpayerPoints();
                onpayerpoints.setPoints_tobepaid(points-points_required);
                onpayerpoints.execute();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog=builder.create();
            dialog.show();


        }else{


            AlertDialog.Builder builder = new AlertDialog.Builder(Payer.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Erreur");
            builder.setMessage("Vous avez des points insuffisants");


            builder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog=builder.create();
            dialog.show();



        }
    }
    private class onpayerSolde extends AsyncTask<Void,Void,Boolean>{
       private double diffence_sold;

        SharedPreferences sharedPreferences=getSharedPreferences("userinformation",MODE_PRIVATE);
        int id_client=sharedPreferences.getInt("id_client",0);
        int id_reservation=sharedPreferences.getInt("id_reservation",0);

        public double getDiffence_sold() {
            return diffence_sold;
        }

        public void setDiffence_sold(double diffence_sold) {
            this.diffence_sold = diffence_sold;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            return  Base.onPaySolde(id_client,id_reservation,diffence_sold);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            if(aBoolean){

                Intent intent=new Intent(getApplicationContext(),Operations.class);
                SharedPreferences.Editor edit=sharedPreferences.edit();
                edit.remove("solde");
                edit.putString("solde",Double.toString(diffence_sold));
                edit.commit();
                startActivity(intent);
            }else{

                Toast.makeText(getApplicationContext(),"il y a une erreur quelque part",Toast.LENGTH_LONG).show();

            }
        }
    }
    private class onpayerPoints extends AsyncTask<Void,Void,Boolean>{

        SharedPreferences sharedPreferences=getSharedPreferences("userinformation",MODE_PRIVATE);
        int id_client=sharedPreferences.getInt("id_client",0);
        int id_reservation=sharedPreferences.getInt("id_reservation",0);
        int points_tobepaid;

        public int getPoints_tobepaid() {
            return points_tobepaid;
        }

        public void setPoints_tobepaid(int points_tobepaid) {
            this.points_tobepaid = points_tobepaid;
        }

        @Override
        protected void onPostExecute(Boolean boolen) {
            if(boolen){

                Intent intent=new Intent(getApplicationContext(),Operations.class);
                SharedPreferences.Editor edit=sharedPreferences.edit();
                edit.remove("points");
                edit.putString("points",Double.toString(points_tobepaid));
                edit.commit();
                startActivity(intent);
            }else{

                Toast.makeText(getApplicationContext(),"il y a une erreur quelque part",Toast.LENGTH_LONG).show();

            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {
         return Base.onPayPoints(id_client,id_reservation,points_tobepaid+2000);
        }
    }
}
