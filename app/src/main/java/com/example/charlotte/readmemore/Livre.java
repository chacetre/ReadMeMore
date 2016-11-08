package com.example.charlotte.readmemore;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class Livre {
    private String title;
    private String auteur;
    private String date;
    //private  String Resume; // avoir si on met le résumé ou non
    //Besoin pour firebase
    public Livre() {

    }
    public Livre(String title, String auteur, String date) {
        this.title = title;
        this.auteur = auteur;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
