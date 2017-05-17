package com.example.prosper.safeairlines;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import Connection.Base;

/**
 * Created by prosper on 30/4/2017.
 */

public class TabSiege extends Fragment {
    ArrayList<String> siege;

    public ArrayList<String> getSiege() {
        return siege;
    }

    public void setSiege(ArrayList<String> siege) {
        this.siege = siege;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        siege = new ArrayList<String>();


        SharedPreferences shd = getActivity().getSharedPreferences("userinformation", Context.MODE_PRIVATE);
        String reservation = shd.getString("reservation", "").trim();
        Seats sit = new Seats();
        sit.execute();


        if (reservation.contains("econ")) {
            ArrayList<seat> st = new ArrayList<seat>();

            String[][] buttons = new String[30][8];
            for (int i = 0; i < 30; i++) {

                int q = i + 1;
                for (int k = 0; k < 8; k++) {

                    if (k == 0)
                        buttons[i][k] = "a" + q;

                    if (k == 1)
                        buttons[i][k] = "c" + q;
                    if (k == 2)
                        buttons[i][k] = "d" + q;
                    if (k == 3)
                        buttons[i][k] = "e" + q;
                    if (k == 4)
                        buttons[i][k] = "f" + q;
                    if (k == 5)
                        buttons[i][k] = "g" + q;
                    if (k == 6)
                        buttons[i][k] = "h" + q;
                    if (k == 7) {
                        buttons[i][k] = "k" + q;

                    }

                }
            }

            for (int i = 0; i < siege.size(); i++) {
                for (int k = 0; k < 30; k++) {

                    for (int j = 0; j < 8; j++) {

                        if (buttons[k][j].equalsIgnoreCase(siege.toString())) {

                            buttons[k][j] = "";
                        }

                    }


                }
            }
            String a = "", c = "", d = "", e = "", f = "", g = "", h = "", p = "";
            for (int b = 0; b < 30; b++) {


                for (int x = 0; x < 8; x++) {
                    if (x == 0)
                        a = buttons[b][x];

                    if (x == 1)
                        c = buttons[b][x];
                    if (x == 2)
                        d = buttons[b][x];
                    if (x == 3)
                        e = buttons[b][x];
                    if (x == 4)
                        f = buttons[b][x];
                    if (x == 5)
                        g = buttons[b][x];
                    if (x == 6)
                        h = buttons[b][x];
                    if (x == 7) {
                        p = buttons[b][x];
                        st.add(new seat(a, c, d, e, f, g, h, p));


                    }


                }


            }

            view = inflater.inflate(R.layout.custom_economy, container, false);
            ArrayAdapter<seat> adapter = new Custom_list_economy(getContext(), st);
            ListView listview = (ListView) view.findViewById(R.id.listview_economy);
            Log.i("working", "here_mrj");
            listview.setAdapter(adapter);
            listview.setDividerHeight(10);
            return view;

        }


        if (reservation.contains("affa")) {
            ArrayList<seatbusiness> sb = new ArrayList<seatbusiness>();


            String[][] button = new String[5][6];
            for (int i = 0; i < 5; i++) {

                int q = i + 1;
                for (int k = 0; k < 6; k++) {

                    if (k == 0)
                        button[i][k] = "a" + q;

                    if (k == 1)
                        button[i][k] = "c" + q;
                    if (k == 2)
                        button[i][k] = "d" + q;
                    if (k == 3)
                        button[i][k] = "g" + q;
                    if (k == 4)
                        button[i][k] = "h" + q;
                    if (k == 5)
                        button[i][k] = "k" + q;


                }
            }

            for (int i = 0; i < siege.size(); i++) {
                for (int k = 0; k < 5; k++) {

                    for (int j = 0; j < 6; j++) {

                        if (button[k][j].equalsIgnoreCase(siege.toString())) {

                            button[k][j] = "";
                        }

                    }


                }
            }

            String a = "", c = "", d = "", g = "", h = "", p = "";
            for (int b = 0; b < 5; b++) {


                for (int x = 0; x < 6; x++) {
                    if (x == 0)
                        a = button[b][x];

                    if (x == 1)
                        c = button[b][x];
                    if (x == 2)
                        d = button[b][x];
                    if (x == 3)
                        g = button[b][x];
                    if (x == 4)
                        h = button[b][x];
                    if (x == 5) {
                        p = button[b][x];

                        sb.add(new seatbusiness(a, c, d, g, h, p));


                    }


                }


            }

            view = inflater.inflate(R.layout.custom_business, container, false);
            ArrayAdapter<seatbusiness> adapter = new Myseatbusiness(getContext(), sb);
            ListView listview = (ListView) view.findViewById(R.id.list_view_business);

            listview.setAdapter(adapter);
            listview.setDividerHeight(20);
            return view;


        }


        if (reservation.contains("premie")) {

            ArrayList<seatfirst> sf = new ArrayList<seatfirst>();


            String[][] buttonf = new String[2][4];
            for (int i = 0; i < 2; i++) {

                int q = i + 1;
                for (int k = 0; k < 4; k++) {

                    if (k == 0)
                        buttonf[i][k] = "a" + q;

                    if (k == 1)
                        buttonf[i][k] = "d" + q;
                    if (k == 2)
                        buttonf[i][k] = "g" + q;
                    if (k == 3)
                        buttonf[i][k] = "k" + q;


                }
            }

            for (int i = 0; i < siege.size(); i++) {
                for (int k = 0; k < 2; k++) {

                    for (int j = 0; j < 4; j++) {

                        if (buttonf[k][j].equalsIgnoreCase(siege.toString())) {

                            buttonf[k][j] = "";
                        }

                    }


                }
            }

            String a = "", c = "", d = "", g = "", h = "", p = "";
            for (int b = 0; b < 2; b++) {


                for (int x = 0; x < 4; x++) {
                    if (x == 0)
                        a = buttonf[b][x];

                    if (x == 1)
                        d = buttonf[b][x];
                    if (x == 2)
                        g = buttonf[b][x];
                    if (x == 3) {
                        p = buttonf[b][x];


                        sf.add(new seatfirst(a, d, g, p));
                        Log.i("mrj",a+d+g+p);

                    }
                }


            }


            view = inflater.inflate(R.layout.custom_first, container, false);
            ArrayAdapter<seatfirst> adapter_first = new MyseatFirst(getContext(), sf);
            ListView listview_first = (ListView) view.findViewById(R.id.list_view_first);

            listview_first.setAdapter(adapter_first);
            listview_first.setDividerHeight(40);
            return view;


        }


        return view;
    }

    private class Seats extends AsyncTask<Void, Void, ArrayList<String>> {
        String reservation;

        public String getReservation() {
            return reservation;
        }

        public void setReservation(String reservation) {
            this.reservation = reservation;
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            return Base.getreservation(reservation);
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            siege.addAll(strings);
        }
    }
}


