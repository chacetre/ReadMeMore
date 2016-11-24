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

import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadingFragment;
import com.example.charlotte.readmemore.ListFragment.ListToReadFragment;

import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.ListViewPagerAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Charlotte on 21/10/2016.
 */
public class ListGeneralActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private FirebaseDatabase database;
    private static DatabaseReference reference;
    private List<Livre> bookList;
    private List<RecyclerViewFragment> fragments;
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
                /* ouvrir Pop-up */
            }

        });

        bookList = new ArrayList<>();

        });

        fragments = new Vector<>();
        // Ajout des Fragments dans la liste
        fragments.add((ListReadFragment) Fragment.instantiate(this,ListReadFragment.class.getName()));
        fragments.add((ListReadingFragment) Fragment.instantiate(this,ListReadingFragment.class.getName()));
        fragments.add((ListToReadFragment) Fragment.instantiate(this,ListToReadFragment.class.getName()));

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerListAdapter(getSupportFragmentManager(),fragments));

                final EditText titre = (EditText) dialog.findViewById(R.id.titre);
                final EditText auteur = (EditText) dialog.findViewById(R.id.auteur);
                final EditText nbPages = (EditText) dialog.findViewById(R.id.ET_pages);
                final EditText date = (EditText) dialog.findViewById(R.id.ET_date);
                final TextView text = (TextView) dialog.findViewById(R.id.ET_text_information);

    private void initListBook () {


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                bookList=dataSnapshot.getValue(genericTypeIndicator);
                for (RecyclerViewFragment fragment:
                        fragments) {
                    fragment.updateBookList(bookList);
                }
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("Firebase", "Value is: " + value);
            }

        });
//        reference.setValue(bookList);

    }


    public List<Livre> getBookList() {
        return bookList;
    }
}
