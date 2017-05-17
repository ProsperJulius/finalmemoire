package com.example.prosper.safeairlines;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.zip.Inflater;

import Connection.Base;


public class Operations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txt = (TextView) findViewById(R.id.textViewoperations);
        SharedPreferences sharedPreference = getSharedPreferences("userinformation", MODE_PRIVATE);
        String nom = sharedPreference.getString("nom", "");
        String prenom = sharedPreference.getString("prenom", "");
        txt.setText("Welcome " + nom + " " + prenom);


        String operations[] = {"reserver vol", "gestion vol", "payer vol", "consulter compte", "consulter vol"};
        ListAdapter listAdapter = new Mycustom_list(this, operations);

        ListView listView = (ListView) findViewById(R.id.listViewoperations);
        //listView.setBackground(getDrawable(R.drawable.custom_shape));
        listView.setAdapter(listAdapter);
        listView.setDividerHeight(70);


        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 3:
                                new compte().execute();
                                break;
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), Reserver.class);
                                startActivity(intent);
                                break;
                            case 1:
                                DialogReservation(position);
                                break;
                            case 2:
                                DialogReservation(position);
                                break;
                            case 4:
                                DialogReservation(position);
                                break;

                        }
                    }
                }
        );


    }


    public void DialogReservation(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Operations.this, R.style.MyAlertDialogStyle);
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View view = inflater.inflate(R.layout.reservationid, null);
        final EditText id_res = (EditText) view.findViewById(R.id.editTextreservation_verify);
        builder.setTitle("Enter your Numero de Reservation");
        builder.setView(view);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        VerifyReservation vry = new VerifyReservation();
                        vry.setClass_name(position);
                        int id_reservation = Integer.parseInt(id_res.getText().toString().trim());
                        if (!id_res.getText().toString().trim().isEmpty()) {
                            vry.setId_reservation(id_reservation);
                            vry.execute();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "veillez entrer votre numero de reservation", Toast.LENGTH_LONG).show();

                        }

                    }
                }


        );
        builder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();


        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.operations_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.id_home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.id_signout) {

            new SignOut().execute();
        }

        return super.onOptionsItemSelected(item);
    }

    private class compte extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.compte, null);
            SharedPreferences sharedPreferences = getSharedPreferences("userinformation", MODE_PRIVATE);
            double solde = Double.parseDouble(sharedPreferences.getString("solde", ""));
            int points = sharedPreferences.getInt("points", 0);
            String emaill = sharedPreferences.getString("email", "");
            TextView email = (TextView) view.findViewById(R.id.textView_email_compte);
            TextView sollde = (TextView) view.findViewById(R.id.textView_solde);
            TextView ppoints = (TextView) view.findViewById(R.id.textView_points);
            email.setText("" + emaill);
            AlertDialog.Builder builder = new AlertDialog.Builder(Operations.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Votre compte");
            builder.setMessage("email:  " + emaill + "\n" + "Solde:  " + solde + "\n" + "Points:  " + points + " \n");

            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

    private class SignOut extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Operations.this, R.style.MyAlertDialogStyle);
            builder.setTitle("Sign Out");
            builder.setMessage("Are you sure You want to SignOut");


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    SharedPreferences shrd = getSharedPreferences("userinformation", MODE_PRIVATE);
                    SharedPreferences.Editor edit = shrd.edit();
                    edit.clear();
                    edit.commit();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        }


    }


    private class VerifyReservation extends AsyncTask<Void, Void, reserv_vol> {
        int class_name;

        SharedPreferences sh = getSharedPreferences("userinformation", MODE_PRIVATE);
        int id_client = sh.getInt("id", 0);

        public int getId_reservation() {
            return id_reservation;
        }

        public void setId_reservation(int id_reservation) {
            this.id_reservation = id_reservation;
        }

        int id_reservation;

        public int getClass_name() {
            return class_name;
        }

        public void setClass_name(int class_name) {
            this.class_name = class_name;
        }

        @Override
        protected reserv_vol doInBackground(Void... params) {
            return Base.verifyReservation(id_client, id_reservation);
        }

        @Override
        protected void onPostExecute(reserv_vol aBoolean) {

            SharedPreferences sharedPreferences = getSharedPreferences("userinformation", MODE_PRIVATE);
            String error = "";
            int points = sharedPreferences.getInt("points", 0);
            Log.i("points", "po" + points);
            Log.i("position", "sition" + class_name);
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("id_reservation", id_reservation);
            editor.putString("airone", aBoolean.getAeroport_da());
            editor.putString("airtwo", aBoolean.getAeroport_de());
            editor.putString("dateone", aBoolean.getDate_da());
            editor.putString("datetwo", aBoolean.getDate_de());
            editor.putString("timeone", aBoolean.getHeure_da());
            editor.putString("timetwo", aBoolean.getHeure_de());
            String clientcout = Double.toString(aBoolean.getCout());
            editor.putString("costs", clientcout);
            editor.putString("reservation", aBoolean.getType());
            editor.commit();


            if (aBoolean.getId_vol() > 0) {
                if (points > 2000) {
                    if (class_name == 1) {

                        Intent intent = new Intent(getApplicationContext(), Gestion.class);
                        // editor.commit();
                        startActivity(intent);

                    }
                    if (class_name == 2) {

                        Intent intent = new Intent(getApplicationContext(), Payer.class);
                        // editor.commit();
                        startActivity(intent);

                    }
                    if (class_name == 4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Operations.this, R.style.MyAlertDialogStyle);
                        builder.setTitle("Apropos de Votre Vol");

                        builder.setMessage("Aeroport de depart: " + aBoolean.getAeroport_da() + "\n" + "Aeroport d'arrvee: " + aBoolean.getAeroport_de() + "\n" + "date depart: " + aBoolean.getDate_de() + "\n" + "date d'arriver: " +aBoolean.getDate_da()+"\n" + "Time de depart:" + aBoolean.getHeure_de() + "\n" + "Time d'arrivee: " + aBoolean.getHeure_da() + "\n");
                        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }


                } else {
                    error += "Points insuffisants";


                }
            } else {

                error += "Vous n'avez pas reserve";
            }
            if (!error.equals("")) {

                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
            }
        }
    }
}