package com.example.prosper.safeairlines;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by prosper on 28/04/2017.
 */

public class Vol implements Serializable {



        private int id_vol;
        private String aeroport_de, aeroport_da;
        private Date date_da, date_de, date_retour;
        private String heure_da, heure_de;
        private double tarrif;
        private String type_vol;

        public Vol() {
            // TODO Auto-generated constructor stub
        }

        public Vol(int id_vol, double tarrif, String airone, String airtwo, String type_vol, Date depart, Date arrive,
                   Date retour, String heurede, String heurearr) {

            this.id_vol = id_vol;
            this.tarrif = tarrif;
            this.aeroport_de = airone;
            this.aeroport_da = airtwo;
            this.date_da = depart;
            this.date_de = arrive;
            this.date_retour = retour;
            this.heure_da = heurearr;
            this.heure_de = heurede;


            this.type_vol = type_vol;
        }

        public void setType_vol(String type_vol) {
            this.type_vol = type_vol;
        }

        public Date getDate_retour() {
            return date_retour;
        }

        public void setDate_retour(Date date_retour) {
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

        public void setDate_de(Date date_de) {
            this.date_de = date_de;
        }

        public void setDate_da(Date date_da) {
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

        public Date getDate_de() {
            return date_de;
        }

        public Date getDate_da() {
            return date_da;
        }

        public String getAeroport_de() {
            return aeroport_de;
        }

        public String getAeroport_da() {
            return aeroport_da;
        }


}
