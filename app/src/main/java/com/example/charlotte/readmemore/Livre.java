package com.example.charlotte.readmemore;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Charlotte on 23/10/2016.
 */
//public class Livre implements Parcelable {
public class Livre {
    private String title;
    private String auteur;
    private String date;
    //private String nbPages;
    //private String status;  // 1 = déja lu , 2 = en cours , 3 = à lire
    //private String pageEnCours;
    //private  String Resume; // avoir si on met le résumé ou non
    //Besoin pour firebase

    public Livre(String title, String auteur, String date, String nbPages, String status, String pageEnCours) {
        this.title = title;
        this.auteur = auteur;
        this.date = date;
/*        this.nbPages = nbPages;
        this.status = status;
        this.pageEnCours = pageEnCours;*/
    }
/*

    public String getNbPages() {
        return nbPages;
    }

    public void setNbPages(String nbPages) {
        this.nbPages = nbPages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPageEnCours() {
        return pageEnCours;
    }

    public void setPageEnCours(String pageEnCours) {
        this.pageEnCours = pageEnCours;
    }
*/

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
/*

    public Livre(Parcel in)
    {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public Livre createFromParcel(Parcel in)
        {
            return new Livre(in);
        }

        @Override
        public Object[] newArray(int size) {
            return null;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    //On ecrit dans le parcel les données de notre objet
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.title);
        dest.writeString(this.auteur);
        dest.writeString(this.date);
        dest.writeString(this.nbPages);
        dest.writeString(this.status);
        dest.writeString(this.pageEnCours);
    }

    //On va ici hydrater notre objet à partir du Parcel
    public void getFromParcel(Parcel in)
    {
        this.setTitle(in.readString());
        this.setAuteur(in.readString());
        this.setDate(in.readString());
        this.setNbPages(in.readString());
        this.setStatus(in.readString());
        this.setPageEnCours(in.readString());

    }
*/
}
