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
import android.widget.Toast;

import Connection.Base;

/**
 * Created by prosper on 30/4/2017.
 */

public class TabModifier extends Fragment {
    EditText date;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.modifier_date, container, false);
        date = (EditText) view.findViewById(R.id.idDates);
        Button btn = (Button) view.findViewById(R.id.idbuttonDates);
        btn.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDate(v);
                    }
                }
        );


        return view;
    }

    public void sendDate(View v) {
        if (date.getText().toString().isEmpty()) {
            date.setError("Veuillez choisir une date S'il vous plait");
        }
        if (!date.getText().toString().isEmpty()) {
            onSend onsend = new onSend();
            onsend.setDate(date.getText().toString());
            onsend.execute();

        }
    }

    private class onSend extends AsyncTask<Void, Void, Boolean> {

        SharedPreferences preferences = getActivity().getSharedPreferences("userinformation", Context.MODE_PRIVATE);
        int id_vol = preferences.getInt("id_vol", 0);
        int id_reservation = preferences.getInt("id_reservation", 0);
        String date;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return Base.Datess(date, id_vol, id_reservation);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {


                AlertDialog.Builder jusa = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                jusa.setTitle("Resultat");
                jusa.setMessage("l'operation a termine avec succes,utilez le meme reservation pour d'autres operations");
                jusa.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(getContext(), Operations.class);
                        startActivity(intent);

                    }
                });
                AlertDialog dialog = jusa.create();
                dialog.show();


            } else {
                AlertDialog.Builder jusa = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
                jusa.setTitle("Error");
                jusa.setMessage("Vol n'exist pas choisir un autre date");
                jusa.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = jusa.create();
                dialog.show();

            }

        }
    }

}
