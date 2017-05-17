package com.example.prosper.safeairlines;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Connection.Base;

/**
 * Created by prosper on 30/4/2017.
 */

public class TabAnnuler extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabannuller, container, false);
        Button btn = (Button) view.findViewById(R.id.buttonalluner);
        btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                OnAllumer();
            }
        });

        return view;
    }


    public void OnAllumer() {
        SharedPreferences shrd = this.getActivity().getSharedPreferences("userinformation", Context.MODE_PRIVATE);
        final int id_reservation = shrd.getInt("id_reservation", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);

        builder.setTitle("Annuller Reservation");
        builder.setMessage("Etes-Vous sure que Vous voulez annuler la reservation");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Annuller vry = new Annuller();

                        vry.setId_reservation(id_reservation);
                        vry.execute();
                        dialog.dismiss();

                    }
                }


        );
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.create();


        dialog.show();

    }

    private class Annuller extends AsyncTask<Void, Void, Boolean> {
        int id_reservation;

        public int getId_reservation() {
            return id_reservation;
        }

        public void setId_reservation(int id_reservation) {
            this.id_reservation = id_reservation;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            Intent intent=new Intent(getActivity(),Operations.class);
            startActivity(intent);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return Base.annuller(id_reservation);

        }
    }
}
