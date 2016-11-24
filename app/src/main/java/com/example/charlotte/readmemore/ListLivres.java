package com.example.charlotte.readmemore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Charlotte on 23/11/2016.
 */

public class ListLivres extends ArrayList<Livre> implements Parcelable {

    public ListLivres()
    {

    }

    public ListLivres(Parcel in)
    {
        this.getFromParcel(in);
    }

    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public ListLivres createFromParcel(Parcel in)
        {
            return new ListLivres(in);
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

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        //Taille de la liste
        int size = this.size();
        dest.writeInt(size);
        for(int i=0; i < size; i++)
        {
            Livre pers = this.get(i); //On vient lire chaque objet personne
            dest.writeString(pers.getTitle());
            dest.writeString(pers.getAuteur());
            dest.writeString(pers.getDate());
            //dest.writeString(pers.getNbPages());
            //dest.writeString(pers.getStatus());
            //dest.writeString(pers.getPageEnCours());
        }
    }

    public void getFromParcel(Parcel in)
    {
        // On vide la liste avant tout remplissage
        this.clear();

        //Récupération du nombre d'objet
        int size = in.readInt();

        //On repeuple la liste avec de nouveau objet
        for(int i = 0; i < size; i++)
        {
            Livre livre = new Livre();
            livre.setTitle(in.readString());
            livre.setAuteur(in.readString());
            livre.setDate(in.readString());
            //livre.setNbPages(in.readString());
            //livre.setStatus(in.readString());
            //livre.setPageEnCours(in.readString());
            this.add(livre);
        }

    }
}
