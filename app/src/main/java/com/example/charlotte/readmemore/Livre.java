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
    private String genre ;

    public Livre() {
    }

    public Livre(String title, String auteur, String date, String nbPages, String nbPagesLues, String finDeLecture, String readingStatus , String genre) {
        this.title = title;
        this.auteur = auteur;
        this.date = date;
        this.nbPages = nbPages;
        this.nbPagesLues = nbPagesLues;
        this.finDeLecture = finDeLecture;
        this.readingStatus = readingStatus;
        this.genre = genre;
    }

    public static Comparator<Livre> getLivreComparator() {
        return livreComparator;
    }

    public static void setLivreComparator(Comparator<Livre> livreComparator) {
        Livre.livreComparator = livreComparator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNbPages() {
        return nbPages;
    }

    public void setNbPages(String nbPages) {
        this.nbPages = nbPages;
    }

    public String getNbPagesLues() {
        return nbPagesLues;
    }

    public void setNbPagesLues(String nbPagesLues) {
        this.nbPagesLues = nbPagesLues;
    }

    public String getFinDeLecture() {
        return finDeLecture;
    }

    public void setFinDeLecture(String finDeLecture) {
        this.finDeLecture = finDeLecture;
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        return auteur.hashCode()+title.hashCode();
    }


}
