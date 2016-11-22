package com.example.charlotte.readmemore;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class Livre {
    private String title;
    private String auteur;
    private String date;
    private int nbPages;
    private String status;  // 1 = déja lu , 2 = en cours , 3 = à lire
    private int pageEnCours;
    //private  String Resume; // avoir si on met le résumé ou non
    //Besoin pour firebase

    public Livre(String title, String auteur, String date, int nbPages, String status, int pageEnCours) {
        this.title = title;
        this.auteur = auteur;
        this.date = date;
        this.nbPages = nbPages;
        this.status = status;
        this.pageEnCours = pageEnCours;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPageEnCours() {
        return pageEnCours;
    }

    public void setPageEnCours(int pageEnCours) {
        this.pageEnCours = pageEnCours;
    }

    public Livre() {
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
