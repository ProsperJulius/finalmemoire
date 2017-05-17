package com.example.prosper.safeairlines;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import Connection.Base;

/**
 * Created by prosper on 30/4/2017.
 */

public class Tabrepas extends Fragment {

    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radbutton1;
    RadioButton radbutton2;


    String food1;
    String food2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repas, container, false);

        radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) view.findViewById(R.id.radioGroup2);
        Button btn=(Button)view.findViewById(R.id.idbutton);
        btn.setOnClickListener(
                new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        sendFood(v);
                    }
                }
        );


        return view;
    }


    public void sendFood(View view) {

        int f1 = radioGroup1.getCheckedRadioButtonId();
        int f2 = radioGroup2.getCheckedRadioButtonId();
        if (f1 != -1 && f2 != -1) {
            radbutton1 = (RadioButton) view.findViewById(f1);
            radbutton2 = (RadioButton) view.findViewById(f2);
            food1 = radbutton1.getText().toString();
            food2 = radbutton2.getText().toString();

            setUserFoods s = new setUserFoods();
            s.setMeal(food1 + food2);
            s.execute();
        } else {
            Toast.makeText(getActivity(), "Veuillez choisir une choix s'il vous plait", Toast.LENGTH_SHORT).show();
        }

    }

    private class setUserFoods extends AsyncTask<Void, Void, Boolean> {

        SharedPreferences shrd = getActivity().getSharedPreferences("userinformation", Context.MODE_PRIVATE);

        int id_reservation = shrd.getInt("id_reservation", 0);
        String meal;

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return Base.setUserFoods(id_reservation, meal);

        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result){
                Toast.makeText(getActivity(),"Menu choisi",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(),Operations.class);
                startActivity(intent);

            }else{

                Toast.makeText(getActivity(),"il y a une erreur quelque part",Toast.LENGTH_LONG).show();
            }

        }
    }
}
