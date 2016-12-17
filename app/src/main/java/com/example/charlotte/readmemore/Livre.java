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
    public enum  Status {
        OnGoing,
        ToDo,
        Done
    }
    private static Comparator<Livre> livreComparator;
    private String title;
    private String auteur;
    private String date;
    private String nbPages;
    private String nbPagesLues;
    private Date finDeLecture;
    private Status readingStatus;

/*    public Livre(String title, String auteur) {
        this.title = title;
        this.auteur = auteur;
    }*/

    public Livre() {
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

    public Status getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(Status readingStatus) {
        this.readingStatus = readingStatus;
    }

    public Date getFinDeLecture() {
        return finDeLecture;
    }

    public void setFinDeLecture(Date finDeLecture) {
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
