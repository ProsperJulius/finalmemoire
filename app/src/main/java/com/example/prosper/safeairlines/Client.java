package com.example.prosper.safeairlines;



public class Client {


    private int id;
    private int points;
    private String nom,prenom,email,password;
    private double solde;
    public Client() {

    }
    public Client(int id,String nom,String prenom,String email,String password,int points,double solde){

        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.password=password;
        this.id=id;
        this.points=points;
        this.solde=solde;

    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getEmail() {
        return email;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }public void setPoints(int points) {
        this.points = points;
    }public void setPassword(String password) {
        this.password = password;
    }public void setNom(String nom) {
        this.nom = nom;
    }public void setId(int id) {
        this.id = id;
    } public void setEmail(String email) {
        this.email = email;
    }public String getPrenom() {
        return prenom;
    }public int getPoints() {
        return points;
    }public String getPassword() {
        return password;
    }public String getNom() {
        return nom;
    }public int getId() {
        return id;
    }}






















