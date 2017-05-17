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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import Connection.Base;

/**
 * Created by prosper on 4/5/2017.
 */

public class Tabchercherretour extends Fragment {

    EditText date_depart, date_ofarrival;
    String airone = "", airtwo = "";
    Calendar myCalendar;

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
        View view = inflater.inflate(R.layout.aller_retour, container, false);
        date_depart = (EditText) view.findViewById(R.id.editTextdate_goiing);
        date_ofarrival = (EditText) view.findViewById(R.id.editTextreturning);

        new HttpRequestTask().execute();
        Spinner spinnerone = (Spinner) view.findViewById(R.id.spinneraller_one);
        Spinner spinnertwo = (Spinner) view.findViewById(R.id.spinneraller_two);
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
        final DatePickerDialog.OnDateSetListener date_other = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLaBel();
            }

        };

        date_depart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        date_ofarrival.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getActivity(), date_other, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });


        Button btn = (Button) view.findViewById(R.id.button4);
        btn.setOnClickListener(

                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Onsend(v);
                    }
                }
        );


        return view;

    }


    public void Onsend(View v) {
        String dateone = date_depart.getText().toString().trim();
        String datetwo = date_ofarrival.getText().toString().trim();

        if(TextUtils.isEmpty(dateone))
        {
            date_depart.setError("Veuillez choisir une date si vous plait");

            return;
        }

        if(TextUtils.isEmpty(datetwo))
        {
            date_ofarrival.setError("Veuillez choisir une date si vous plait");

            return;
        }

        if(TextUtils.isEmpty(airone))
        {
            TextView textView= (TextView)getView().findViewById(R.id.textViewalller_retour);
            textView.setError("Vous n'avez pas remplis ce champ");

            return;
        }

        if(TextUtils.isEmpty(airtwo))
        {
          TextView textView= (TextView)getView().findViewById(R.id.textView17);


            textView.setError("Vous n'avez pas remplis ce champ");

            return;
        }



        if (!date_ofarrival.getText().toString().isEmpty() && !date_depart.getText().toString().isEmpty() && !airone.isEmpty() && !airtwo.isEmpty()) {

            SendData sendDatand = new SendData();
            sendDatand.setAirportone(airone);
            sendDatand.setAirporttwo(airtwo);
            sendDatand.setDate_of_retour(date_ofarrival.getText().toString().trim());
            sendDatand.setDateof_departure(date_depart.getText().toString().trim());
            sendDatand.execute();

        }

    }

    private void updateLabel() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_depart.setText(sdf.format(myCalendar.getTime()));

    }

    private void updateLaBel() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date_ofarrival.setText(sdf.format(myCalendar.getTime()));
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            return Base.getVol();
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            Spinner spinnerone = (Spinner) getView().findViewById(R.id.spinneraller_one);
            Spinner spinnertwo = (Spinner) getView().findViewById(R.id.spinneraller_two);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, strings);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerone.setAdapter(adapter);
            spinnertwo.setAdapter(adapter);

        }
    }

    private class SendData extends AsyncTask<Void, Void, ArrayList<Vol>> {
        String airportone, airporttwo, dateof_departure, date_of_retour;

        public String getDateof_departure() {
            return dateof_departure;
        }

        public void setDateof_departure(String dateof_departure) {
            this.dateof_departure = dateof_departure;
        }

        public String getDate_of_retour() {

            return date_of_retour;
        }

        public void setDate_of_retour(String date_of_retour) {
            this.date_of_retour = date_of_retour;
        }

        public String getAirporttwo() {

            return airporttwo;
        }

        public void setAirporttwo(String airporttwo) {
            this.airporttwo = airporttwo;
        }

        public String getAirportone() {

            return airportone;
        }

        public void setAirportone(String airportone) {
            this.airportone = airportone;
        }

        @Override
        protected ArrayList<Vol> doInBackground(Void... params) {
            return Base.insertChercher_retour(airportone, airporttwo, dateof_departure, date_of_retour);
        }

        @Override
        protected void onPostExecute(ArrayList<Vol> vols) {

            if (vols.size() > 0) {

                Intent intent = new Intent(getActivity(), Resultschercher.class);
                intent.putExtra("myarray", vols);
                startActivity(intent);


            } else {

                Toast.makeText(getActivity(), "Vol n'exist pas", Toast.LENGTH_LONG).show();
            }
        }
    }
}

