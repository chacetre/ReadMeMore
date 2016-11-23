package com.example.charlotte.readmemore.Activity;

import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.ListLivres;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.ListViewPagerAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Charlotte on 21/10/2016.
 */
public class ListGeneralActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private FirebaseDatabase database;
    private static DatabaseReference reference;
    private ListLivres bookList;
    // private List<RecyclerViewFragment> fragments;
    private ImageView backHome;
    private ImageView addBook;
    public static ViewPager viewPager;

    public static ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_list);

        Bundle b = getIntent().getExtras();
        bookList = b.getParcelable("identifiantListe");

        database = Utils.getDatabase();
        reference = database.getReference("globalLibrary");
        backHome = (ImageView) findViewById(R.id.backHome);
        addBook = (ImageView) findViewById(R.id.addBook);

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


        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ListViewPagerAdapter(getSupportFragmentManager(), bookList));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        //initListBook();
    }


    public List<Livre> getBookList() {
        return bookList;
    }
}
