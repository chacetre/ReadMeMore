package com.example.charlotte.readmemore.SuggestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class SuggestionByThemeFrag extends Fragment {

    private Map<String, Livre> booklist;
    private List<Livre> userLivres;
    private List<Livre> globalLivres;
    private List<Livre> policierBook;
    private List<Livre> romanceBook;
    private static ValueEventListener globalListener;
    private CheckBox romanceBox;
    private CheckBox policierBox;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.theme_suggestion, container, false);
        super.onCreate(savedInstanceState);
        bundleItem(rootView);

        policierBook = new ArrayList<>();
        romanceBook = new ArrayList<>();
        return rootView;
    }

    private ValueEventListener setGlobalListener(ValueEventListener valueEventListener) {
        if (globalListener != null) {
            Utils.removeUserListener(globalListener);
            globalListener = null;
        }
        globalListener = valueEventListener;
        return globalListener;
    }

    private void bundleItem(View v) {
        Button rechercheThemeButton = (Button) v.findViewById(R.id.rechercheThemeButton);
        final RelativeLayout resultatRecherche = (RelativeLayout) v.findViewById(R.id.resultatRecherche);

        romanceBox = (CheckBox) v.findViewById(R.id.RomanceCheckBox);
        policierBox = (CheckBox) v.findViewById(R.id.PoliciercheckBox);
        TextView title = (TextView) v.findViewById(R.id.title);
        TextView auteur = (TextView) v.findViewById(R.id.auteur);

        String genreChoose = "";

        Utils.AddGlobalValueListener(setGlobalListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                globalLivres = Utils.getGlobalLivres();
                if (globalLivres.size() != 0) {
                    for (Livre l : globalLivres) {
                        if (l.getGenre().equals("policier"))
                            policierBook.add(l);

                        if (l.getGenre().equals("romance"))
                            romanceBook.add(l);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        }));


        rechercheThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (policierBox.isChecked()) {
                    for (Livre livre : policierBook) {

                        title.setText(livre.getTitle());
                        auteur.setText(livre.getAuteur());

                    }

                }

                if (romanceBox.isChecked()) {

                    if (romanceBook.size() != 0) {
                        title.setText(romanceBook.get(0).getTitle());
                        auteur.setText(romanceBook.get(0).getAuteur());
                    }


                }

                resultatRecherche.setVisibility(View.VISIBLE);
            }
        });
    }
}
