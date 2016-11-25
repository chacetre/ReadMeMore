package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.PageView.SuggestionViewPagerAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionAutoFrag;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionByThemeFrag;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class SuggestionActivity extends AppCompatActivity {

    private ImageView backHome;
    private List<Livre> userLivres;
    private List<Livre> globalLivres;
    private static ValueEventListener valueEventListener;
    private static ValueEventListener valueEventListener2;
    private static DatabaseReference userRef;
    private static DatabaseReference globalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity);
        backHome = (ImageView) findViewById(R.id.backHome) ;

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuggestionActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        setUserDBListener();
        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this,SuggestionAutoFrag.class.getName()));
        fragments.add(Fragment.instantiate(this,SuggestionByThemeFrag.class.getName()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pagerSuggestion);
        viewPager.setAdapter(new SuggestionViewPagerAdapter(getSupportFragmentManager(),fragments));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsSuggestion);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        setUserDBListener();
    }

    private void setUserDBListener() {
        userLivres = new ArrayList<>();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    if(userRef == null) {
                        if(valueEventListener!=null) {
                            userRef.removeEventListener(valueEventListener);
                        }
                        userRef = null;
                    }
                    if(globalRef == null) {
                        if(valueEventListener2!=null) {
                            globalRef.removeEventListener(valueEventListener2);
                        }
                        globalRef = null;
                    }
                    userLivres.clear();
                    //Clear pas globalLib pour economiser
                }
                else {
                    userRef = Utils.getDatabase().getReference(firebaseAuth.getCurrentUser().getUid());
                    globalRef = Utils.getDatabase().getReference("globalLibrary");
                    valueEventListener = userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                            userLivres=dataSnapshot.getValue(genericTypeIndicator);
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            Log.w("Firebase", "Failed to read value.", error.toException());
                        }
                    });
                    valueEventListener2 = globalRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                            globalLivres=dataSnapshot.getValue(genericTypeIndicator);
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
}
