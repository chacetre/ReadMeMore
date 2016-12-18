package com.example.charlotte.readmemore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

/**
 * Created by Charlotte on 23/10/2016.
 */

//public class Livre implements Parcelable {
public class Livre {

    private static Comparator<Livre> livreComparator;
    private String title;
    private String auteur;
    private String date;
    private String nbPages;
    private String nbPagesLues;
    private String finDeLecture;
    private String readingStatus; // OnGoing, ToDo, Done

/*    public Livre(String title, String auteur) {
        this.title = title;
        this.auteur = auteur;
    }*/

    public Livre() {
    }

    public Livre(String title, String auteur, String date, String nbPages, String nbPagesLues, String finDeLecture, String readingStatus) {
        this.title = title;
        this.auteur = auteur;
        this.date = date;
        this.nbPages = nbPages;
        this.nbPagesLues = nbPagesLues;
        this.finDeLecture = finDeLecture;
        this.readingStatus = readingStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    @SuppressWarnings("WeakerAccess")
    public String getAuteur() {
        return auteur;
    }
    @SuppressWarnings("WeakerAccess")
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    @SuppressWarnings("WeakerAccess")
    public void setDate(String date) {
        this.date = date;
    }
    @SuppressWarnings("WeakerAccess")
    public String getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return auteur.hashCode()+title.hashCode();
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getFinDeLecture() {
        return finDeLecture;
    }

    public void setFinDeLecture(String finDeLecture) {
        this.finDeLecture = finDeLecture;
    }

    public String getNbPagesLues() {
        return nbPagesLues;
    }

    public void setNbPagesLues(String nbPagesLues) {
        this.nbPagesLues = nbPagesLues;
    }

    public String getNbPages() {
        return nbPages;
    }

    public void setNbPages(String nbPages) {
        this.nbPages = nbPages;
    }

}
