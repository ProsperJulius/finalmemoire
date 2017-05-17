package com.example.prosper.safeairlines;

/**
 * Created by prosper on 27/04/2017.
 */

public class Reservation {




    private int id_reservation, id_client, id_vol;

    public Reservation() {

    }

    public Reservation(int id_reservation, int id_vol, int int_client) {

        this.id_reservation = id_reservation;
        this.id_client = id_client;
        this.id_vol = id_vol;

    }

    public int getId_client() {
        return id_client;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_vol() {
        return id_vol;
    }












}
