package com.example.prosper.safeairlines;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import Connection.Base;


/**
 * Created by prosper on 4/5/2017.
 */

public class Tabchercheraller extends Fragment {
    Spinner airportone, airporttwo;
    EditText datetext;
    Calendar myCalendar;
    String airone, airtwo;

    public String getAirtwo() {
        return airtwo;
    }

    public void setAirtwo(String airtwo) {
        this.airtwo = airtwo;
    }

    public String getAirone() {
        return airone;
    }

    public void setAirone(String airone) {
        this.airone = airone;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chercher, container, false);
        datetext = (EditText) view.findViewById(R.id.editTextdate);
        Button btn = (Button) view.findViewById(R.id.Chercher);
        btn.setOnClickListener(

                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SendData();
                    }
                }
        );

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datetext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        new HttpRequestTask().execute();
        Spinner spinnerone = (Spinner) view.findViewById(R.id.spinnerone);
        spinnerone.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        setAirone(String.valueOf(parent.getItemAtPosition(position)));
                       // Toast.makeText(getActivity(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        Spinner spinnertwo = (Spinner) view.findViewById(R.id.spinnertwo);
        spinnertwo.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {


                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        setAirtwo(String.valueOf(parent.getItemAtPosition(position)));
                       // Toast.makeText(getActivity(), String.valueOf(parent.getItemAtPosition(position)), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


        return view;


    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        datetext.setText(sdf.format(myCalendar.getTime()));
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            return Base.getVol();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            Spinner spinnerone = (Spinner) getView().findViewById(R.id.spinnerone);
            Spinner spinnertwo = (Spinner) getView().findViewById(R.id.spinnertwo);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerone.setAdapter(adapter);
            spinnertwo.setAdapter(adapter);

        }
    }

    public void SendData() {






        String date = datetext.getText().toString().trim();


        if(TextUtils.isEmpty(date))
        {
            datetext.setError("Veuillez choisir une date si vous plait");

            return;
        }

        if(TextUtils.isEmpty(airone))
        {
            TextView textView=(TextView)getView().findViewById(R.id.textView);
            textView.setError("Vous n'avez pas remplis ce champ");

            return;
        }

        if(TextUtils.isEmpty(airtwo))
        {
            TextView textView=(TextView)getView().findViewById(R.id.textView3);
            textView.setError("Vous n'avez pas remplis ce champ");

            return;
        }
        if(!date.isEmpty() && !airone.isEmpty()&&!airtwo.isEmpty()) {
            httpChercher httpChercher = new httpChercher();
            httpChercher.setAirone(airone);
            httpChercher.setAirtwo(airtwo);
            httpChercher.setDdate(date);
            httpChercher.execute();
        }else{

            TextView aeroport =(TextView)getView().findViewById(R.id.textView);
            aeroport.setError("Veuillez remplier tous les champs");
        }

    }


    private class httpChercher extends AsyncTask<Void, Void, ArrayList<Vol>> {

        String airone, airtwo, ddate;

        public String getDdate() {
            return ddate;
        }

        public void setDdate(String ddate) {
            this.ddate = ddate;
        }

        public String getAirtwo() {
            return airtwo;
        }

        public void setAirtwo(String airtwo) {
            this.airtwo = airtwo;
        }

        public String getAirone() {
            return airone;
        }

        public void setAirone(String airone) {
            this.airone = airone;
        }

        @Override
        protected ArrayList<Vol> doInBackground(Void... params) {


            return Base.insertChercher(airone, airtwo, ddate);
        }

        @Override
        protected void onPostExecute(ArrayList<Vol> vol) {
            if (vol.size() > 0) {

                Intent intent = new Intent(getActivity(), Resultschercher.class);
                intent.putExtra("myarray", vol);
                startActivity(intent);


            } else {

                Toast.makeText(getActivity(), "Vol n'exist pas", Toast.LENGTH_LONG).show();
            }

        }


    }
}