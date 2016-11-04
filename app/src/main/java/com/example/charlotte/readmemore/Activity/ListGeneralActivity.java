package com.example.charlotte.readmemore.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.ListFragment.ListReadFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadingFragment;
import com.example.charlotte.readmemore.ListFragment.ListToReadFragment;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.ViewPagerListAdapter;
import com.example.charlotte.readmemore.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Charlotte on 21/10/2016.
 */
public class ListGeneralActivity extends FragmentActivity {

    private PagerAdapter mPagerAdapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<Livre> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.content_list);
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        reference = database.getReference("globalLibrary");

        bookList = new ArrayList<>();
        initListBook();

        // Création de la liste de Fragments que fera défiler le PagerAdapter
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this,ListToReadFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,ListReadingFragment.class.getName()));
        fragments.add(Fragment.instantiate(this,ListReadFragment.class.getName()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerListAdapter(getSupportFragmentManager(),fragments));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    private void initListBook () { // pour le moment on les met en dur ensuite on ira les chercher dans la BD

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("Firebase", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });

        Livre book = new Livre("AAAAAAAAAAAAAA", "J.K. Rowling", "2016");
        bookList.add(book);

        book = new Livre("BBBBBBBBBBBB", "Fanny Pantigny", "2016");
        bookList.add(book);

        book = new Livre("CCCCCCCCCCCCC", "Gérard Davet", "2016");
        bookList.add(book);

        book = new Livre("DDDDDDDDDDDDDd", "François Mitterrand", "2016");
        bookList.add(book);

        book = new Livre("Harry Potter et l'Enfant Maudit", "J.K. Rowling", "2016");
        bookList.add(book);

        book = new Livre("Ki et Hi", "Fanny Pantigny", "2016");
        bookList.add(book);

        book = new Livre("Un président ne devrait pas dire ça… Les secrets d'un quinquennat", "Gérard Davet", "2016");
        bookList.add(book);

        book = new Livre("Lettres à Anne 1962-1995", "François Mitterrand", "2016");
        bookList.add(book);

        reference.setValue(bookList);
    }

    public List<Livre> getBookList() {
        return bookList;
    }
}
