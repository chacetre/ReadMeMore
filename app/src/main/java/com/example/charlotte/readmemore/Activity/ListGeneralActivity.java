package com.example.charlotte.readmemore.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.ListFragment.ListReadFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadingFragment;
import com.example.charlotte.readmemore.ListFragment.ListToReadFragment;
import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.ViewPagerListAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
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
    private static DatabaseReference reference;
    private List<Livre> bookList;
    private List<RecyclerViewFragment> fragments;
    private ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_list);
        database= Utils.getDatabase();
        reference = database.getReference("globalLibrary");
        backHome = (ImageView) findViewById(R.id.backHome) ;

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListGeneralActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        bookList = new ArrayList<>();

        // Création de la liste de Fragments que fera défiler le PagerAdapter

        fragments = new Vector<>();
        // Ajout des Fragments dans la liste
        fragments.add((RecyclerViewFragment) Fragment.instantiate(this,RecyclerViewFragment.class.getName()));
        fragments.add((RecyclerViewFragment) Fragment.instantiate(this,RecyclerViewFragment.class.getName()));
        fragments.add((RecyclerViewFragment) Fragment.instantiate(this,RecyclerViewFragment.class.getName()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerListAdapter(getSupportFragmentManager(),fragments));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        initListBook();
    }

    private void initListBook () { // pour le moment on les met en dur ensuite on ira les chercher dans la BD

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

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });

//        reference.setValue(bookList);
    }

    public List<Livre> getBookList() {
        return bookList;
    }
}
