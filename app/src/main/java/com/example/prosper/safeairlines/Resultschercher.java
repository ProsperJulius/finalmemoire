package com.example.prosper.safeairlines;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.Serializable;
import java.util.ArrayList;

import Connection.Base;

import static android.support.v7.appcompat.R.styleable.AlertDialog;

public class Resultschercher extends AppCompatActivity implements Serializable {

    double paiement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultschercher);
        ArrayList<Vol> results = (ArrayList<Vol>) getIntent().getSerializableExtra("myarray");

        Vol vol = results.get(0);
        paiement = vol.getTarrif();


        ListView listView = (ListView) findViewById(R.id.list_view_resutls);
        ArrayAdapter arrayAdapter;
        arrayAdapter = new MycustomListview(this, results);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int id_vol = (Integer) view.getTag();
                        ReservationDialog reservatonDialog = new ReservationDialog();
                        reservatonDialog.setId_vol(id_vol);
                        reservatonDialog.setPaiement(paiement);

                        reservatonDialog.execute();
                        //Toast.makeText(getApplicationContext()," jusa "+id_vol,Toast.LENGTH_LONG).show();
                    }
                }
        );


    }


    private class SendData extends AsyncTask<Void, Void, Integer> {
        private int id_client, id_vol;
        private String classereserv;
        private double paiement;

        public double getPaiement() {
            return paiement;
        }

        public void setPaiement(double paiement) {
            this.paiement = paiement;
        }

        public int getId_vol() {
            return id_vol;
        }

        public void setId_vol(int id_vol) {
            this.id_vol = id_vol;
        }

        public int getId_client() {

            return id_client;
        }

        public void setId_client(int id_client) {
            this.id_client = id_client;
        }

        public String getClassereserv() {
            return classereserv;
        }

        public void setClassereserv(String classereserv) {
            this.classereserv = classereserv;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            return Base.insertReservation(id_client, id_vol, paiement, classereserv);

        }


        @Override
        protected void onPostExecute(Integer aBoolean) {


            if (aBoolean != 0) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Resultschercher.this, R.style.MyAlertDialogStyle);
                builder.setTitle("Numero de Reservation");
                builder.setMessage("Votre numero de Reservation est  " + aBoolean + "\n" + "on a envoye toutes les informations concernant votre reservation dans votre email");


                builder.setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getApplicationContext(), Operations.class);
                        startActivity(intent);

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            } else {

                Toast.makeText(getApplicationContext(), "Vous pouvez pas reserver deux Fois la meme vol", Toast.LENGTH_LONG).show();
            }


        }
    }


    private class ReservationDialog extends AsyncTask<Void, Void, Boolean> {

        private String classereserv;
        private double paiement;

        public double getPaiement() {
            return paiement;
        }

        public void setPaiement(double paiement) {
            this.paiement = paiement;
        }

        public int getId_vol() {
            return id_vol;
        }

        public void setId_vol(int id_vol) {
            this.id_vol = id_vol;
        }

        public String getClassereserv() {
            return classereserv;
        }

        public void setClassereserv(String classereserv) {
            this.classereserv = classereserv;
        }

        private int id_vol;
        SharedPreferences sharedPreferences = getSharedPreferences("userinformation", MODE_PRIVATE);
        int id_client = sharedPreferences.getInt("id", 0);

        @Override
        protected Boolean doInBackground(Void... params) {
            return true;
        }

        @Override
        protected void onPostExecute(Boolean b) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Resultschercher.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Reservation du vol");

            final String classe_reservation[] = {"Premiere classe", "classe affaires", " classe economique"};

            builder.setSingleChoiceItems(classe_reservation, -1, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {

                        classereserv = "classepremiere";


                    } else if (which == 1) {

                        classereserv = "classeaffaire";

                    } else if (which == 2) {
                        classereserv = "classeeconomique";
                    }


                }
            });


            builder.setPositiveButton("Reserver", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SendData sendData = new SendData();
                    sendData.setId_vol(id_vol);
                    sendData.setId_client(id_client);
                    sendData.setClassereserv(classereserv);
                    sendData.setPaiement(paiement);
                    sendData.execute();

                }
            });
            builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ///
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }


}
