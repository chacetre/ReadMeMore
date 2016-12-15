package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlotte on 23/11/2016.
 */

public class WinActivity extends AppCompatActivity {

    private ImageView backHome;
    private int nb;
    private List<Livre> userLivres;
    private TextView nbPage ;
    private static ValueEventListener userListener;


    private ValueEventListener setUserListener(ValueEventListener valueEventListener) {
        if(userListener!=null) {
            Utils.removeUserListener(userListener);
            userListener=null;
        }
        userListener=valueEventListener;
        return userListener;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_activity);

        backHome = (ImageView) findViewById(R.id.backHome) ;
        nbPage = (TextView) findViewById(R.id.totalPoint);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        nb=0;
        nbPage.setText(String.valueOf(nb) + " points");

        // TODO boucle if
        Utils.AddUserValueListener(setUserListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLivres=Utils.getUserLivres();
                /*for ( Livre l : userLivres ) {
                    if(l.getSatus().equal("finish"))
                        
                        nb += l.getNombrePages();

                }*/
                nbPage.setText(String.valueOf(nb) + " points");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        }));


    }
}
