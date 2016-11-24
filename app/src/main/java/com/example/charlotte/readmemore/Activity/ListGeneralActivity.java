package com.example.charlotte.readmemore.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadingFragment;
import com.example.charlotte.readmemore.ListFragment.ListToReadFragment;

import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.ViewPagerListAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Charlotte on 21/10/2016.
 */
public class ListGeneralActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private FirebaseDatabase database;
    private static DatabaseReference userRef;
    private List<Livre> bookList;
    private List<RecyclerViewFragment> fragments;
    private ImageView backHome;
    private ImageView addBook;
    public static ViewPager viewPager;
    private static ValueEventListener valueEventListener;

    public static ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_list);
        database= Utils.getDatabase();
        //Connect and sync db on user change;
        setUserDBListener();
        backHome = (ImageView) findViewById(R.id.backHome) ;
        addBook = (ImageView) findViewById(R.id.addBook) ;

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListGeneralActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final boolean find = false;
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_add_book);
                dialog.setTitle("Ajouter un livre");

                final EditText titre = (EditText) dialog.findViewById(R.id.titre);
                final EditText auteur = (EditText) dialog.findViewById(R.id.auteur);
                final EditText nbPages = (EditText) dialog.findViewById(R.id.ET_pages);
                final EditText date = (EditText) dialog.findViewById(R.id.ET_date);
                final TextView text = (TextView) dialog.findViewById(R.id.ET_text_information);

                final Button dialogButtonAdd = (Button) dialog.findViewById(R.id.dialogButtonAdd);



                /* On recupere data des EDIT TEXT */

                String titreS = titre.getText().toString();
                String auteurS = auteur.getText().toString();
                String nbPagesS = nbPages.getText().toString();
                String dateS = date.getText().toString();


                final Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonRecherche);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String titreS = titre.getText().toString();
                        String auteurS = auteur.getText().toString();
                        // TODO : Faire une recherche dans la dataBase avec auteurS et titre S
                        // si on trouve alors on ferme et on ajoute dans la liste de lecture

                        //Sinon on demande d'ajouter dans la DB

                        text.setText("Nous n'avons pas trouver de livre dans notre bibilotèque veuillez renseigner les champs suivants :");
                        text.setTextColor(getResources().getColor(R.color.red));
                        date.setVisibility(View.VISIBLE);
                        nbPages.setVisibility(View.VISIBLE);
                        dialogButton.setVisibility(View.GONE);
                        dialogButtonAdd.setVisibility(View.VISIBLE);

                    }
                });

                dialogButtonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String dateS = date.getText().toString();
                        String nbPageS = nbPages.getText().toString();
                        // envoie de la donnée a la DB et ajout à la liste
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });

        bookList = new ArrayList<>();

        // Création de la liste de Fragments que fera défiler le PagerAdapter

        fragments = new Vector<>();
        // Ajout des Fragments dans la liste
        fragments.add((ListReadFragment) Fragment.instantiate(this,ListReadFragment.class.getName()));
        fragments.add((ListReadingFragment) Fragment.instantiate(this,ListReadingFragment.class.getName()));
        fragments.add((ListToReadFragment) Fragment.instantiate(this,ListToReadFragment.class.getName()));

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerListAdapter(getSupportFragmentManager(),fragments));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        setUserDBListener();
    }

    private void setUserDBListener() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    if(userRef!=null) {
                        userRef.removeEventListener(valueEventListener);
                        userRef = null;
                    }
                    bookList.clear();
                }
                else {
                    userRef =database.getReference(firebaseAuth.getCurrentUser().getUid());
                    valueEventListener = userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                            bookList=dataSnapshot.getValue(genericTypeIndicator);
                            for (RecyclerViewFragment fragment:
                                    fragments) {
                                fragment.updateBookList(bookList);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Log.w("Firebase", "Failed to read value.", error.toException());
                        }
                    });
                }
            }
        });
    }

    public List<Livre> getBookList() {
        return bookList;
    }
}
