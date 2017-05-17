package com.example.prosper.safeairlines;

/**
 * Created by prosper on 12/5/2017.
 */

public class reserv_vol {




    private int id_vol;
    private String aeroport_de, aeroport_da;
    private String date_da, date_de, date_retour;
    private String heure_da, heure_de;
    private double tarrif;
    private String type_vol;
    private double cout;
    private String type;

    public reserv_vol() {
        // TODO Auto-generated constructor stub
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public reserv_vol(int id_vol, double cout, String airone, String airtwo,
                      String depart, String arrive, String  heurede, String heurearr, String type) {


        this.id_vol = id_vol;
        this.type=type;


        this.aeroport_de = airone;
        this.aeroport_da = airtwo;
        this.date_da = depart;
        this.date_de = arrive;

        this.heure_da = heurearr;
        this.heure_de = heurede;
        this.cout = cout;


    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public void setType_vol(String type_vol) {
        this.type_vol = type_vol;
    }

    public String getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(String date_retour) {
        this.date_retour = date_retour;
    }

    public String getType_vol() {
        return type_vol;
    }

    public double getTarrif() {
        return tarrif;
    }

    public void setTarrif(int tarrif) {
        this.tarrif = tarrif;
    }

    public void setId_vol(int id_vol) {
        this.id_vol = id_vol;
    }

    public void setHeure_de(String heure_de) {

        this.heure_de = heure_de;
    }

    public void setHeure_da(String heure_da) {
        this.heure_da = heure_da;
    }

    public void setDate_de(String date_de) {
        this.date_de = date_de;
    }

    public void setDate_da(String date_da) {
        this.date_da = date_da;
    }

    public void setAeroport_de(String aeroport_de) {
        this.aeroport_de = aeroport_de;
    }

    public void setAeroport_da(String aeroport_da) {
        this.aeroport_da = aeroport_da;
    }

    public int getId_vol() {
        return id_vol;
    }

    public String getHeure_de() {
        return heure_de;
    }

    public String getHeure_da() {
        return heure_da;
    }

    public String getDate_de() {
        return date_de;
    }

    public String getDate_da() {
        return date_da;
    }

    public String getAeroport_de() {
        return aeroport_de;
    }

    public String getAeroport_da() {
        return aeroport_da;
    }

}

