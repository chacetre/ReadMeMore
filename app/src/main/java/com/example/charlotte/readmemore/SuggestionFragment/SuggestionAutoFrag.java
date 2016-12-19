package com.example.charlotte.readmemore.SuggestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class SuggestionAutoFrag extends Fragment {

    private List<Livre> userLivres;
    private List<Livre> globalLivres;
    private List<Livre> autosuggestedBooks;

    private HashMap<String, Integer> userTastes = new HashMap<String, Integer>();

    private static ValueEventListener userListener;
    private static ValueEventListener globalListener;

    private ValueEventListener setUserListener(ValueEventListener valueEventListener) {
        if(userListener!=null) {
            Utils.removeUserListener(userListener);
            userListener=null;
        }
        userListener=valueEventListener;
        return userListener;
    }

    private ValueEventListener setGlobalListener(ValueEventListener valueEventListener) {
        if(globalListener!=null) {
            Utils.removeUserListener(globalListener);
            globalListener=null;
        }
        globalListener=valueEventListener;
        return globalListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.auto_suggestion, container, false);

        Utils.AddUserValueListener(setUserListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLivres=Utils.getUserLivres();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        }));

        Utils.AddGlobalValueListener(setGlobalListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                globalLivres=Utils.getGlobalLivres();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        }));

        if(autosuggestedBooks != null)
            autosuggestedBooks = suggestionAuto();

        super.onCreate(savedInstanceState);
        return rootView;
    }

    private List<Livre> suggestionAuto()
    {
        List<Livre> tmpList = new ArrayList<Livre>();

        //find most frequent themes/authors in users's list
        for (Livre book: userLivres)
        {
            //add themes when implemented
            String curAuteur = book.getAuteur();
            if(userTastes.containsKey(curAuteur))
            {
                userTastes.put(curAuteur, userTastes.get(curAuteur)+1);
            }
            else
            {
                userTastes.put(curAuteur, 1);
            }
        }

        for (Livre book: globalLivres)
        {
            if(userTastes.containsKey(book.getAuteur()) && tmpList.size() <= 9) //or theme when implemented
            {
                tmpList.add(book);
            }
            else if (tmpList.size() >= 10)
            {
                break;
            }
        }

        return tmpList;
    }
}
