package com.example.prosper.safeairlines;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Connection.Base;


public class Chercher extends AppCompatActivity{
    EditText air1,air2,date_gng;
    private  String airportone,airporttwo,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher);

        // air1=(EditText)findViewById(R.id.editTextairone);
        // air2=(EditText)findViewById(R.id.editTextairtwo);
       // date_gng=(EditText)findViewById(R.id.editTextdatedepart);

    }

    public void OnChercher(View view) throws ParseException {



        airportone=air1.getText().toString().trim();
        airporttwo=air2.getText().toString().trim();

        date=date_gng.getText().toString().trim();
        httpChercher httpChercher=new httpChercher();
         httpChercher.setAirone(airportone);
        httpChercher.setAirtwo(airporttwo);
        httpChercher.setDdate(date);
        httpChercher.execute();



    }


    private class httpChercher extends AsyncTask<Void,Void,ArrayList<Vol>>{

       String airone,airtwo,ddate;

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


            return Base.insertChercher(airone,airtwo,ddate);
        }

        @Override
        protected void onPostExecute(ArrayList<Vol> vol) {
            if(vol.size()>0){

                Intent intent=new Intent(getApplicationContext(),Resultschercher.class);
                intent.putExtra("myarray",vol);
                startActivity(intent);



            }else{

                Toast.makeText(getApplicationContext(),"Vol n'exist pas",Toast.LENGTH_LONG).show();
            }

        }
    }


}
