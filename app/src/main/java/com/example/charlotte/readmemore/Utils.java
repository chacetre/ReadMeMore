package com.example.charlotte.readmemore;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louis on 08/11/2016.
 */

public class Utils {
    private static List<Livre> userLivres;
    private static List<Livre> globalLivres;

    private static FirebaseDatabase mDatabase;

    private static ValueEventListener userLivresListener;
    private static DatabaseReference userRef;

    private static ValueEventListener globalLivresListener;
    private static DatabaseReference globalRef;

    public static List<Livre> getUserLivres() {
        if (userLivres == null) {
            userLivres = new ArrayList<>();
        }
        return userLivres;
    }

    public static List<Livre> getGlobalLivres() {
        if (globalLivres == null) {
            globalLivres = new ArrayList<>();
        }
        return globalLivres;
    }

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

    private static DatabaseReference getUserRef() {
        if (userRef == null) {
            userRef=getDatabase().getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
        return userRef;
    }

    private static DatabaseReference getGlobalRef() {
        if (userRef == null) {
            userRef=getDatabase().getReference("globalLibrary");
        }
        return userRef;
    }

    public static void AddUserValueListener(ValueEventListener vel) {
        getUserRef().addValueEventListener(vel);
    }

    public static void AddGlobalValueListener(ValueEventListener vel) {
        getGlobalRef().addValueEventListener(vel);
    }

    private void setUserDBListener() {
        userLivres = new ArrayList<>();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null) {
                    if(userRef!=null) {
                        if(userLivresListener !=null) {
                            userRef.removeEventListener(userLivresListener);
                        }
                        userRef = null;
                    }
                    if(globalRef == null) {
                        if(globalLivresListener !=null) {
                            globalRef.removeEventListener(globalLivresListener);
                        }
                        globalRef = null;
                    }
                    userLivres.clear();
                    //Clear pas globalLib pour economiser
                }
                else {
                    userRef = Utils.getDatabase().getReference(firebaseAuth.getCurrentUser().getUid());
                    globalRef = Utils.getDatabase().getReference("globalLibrary");
                    userLivresListener = userRef.addValueEventListener(new ValueEventListener() {
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
                    globalLivresListener = globalRef.addValueEventListener(new ValueEventListener() {
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

    public static void removeUserListener(ValueEventListener input) {
        getUserRef().removeEventListener(input);
    }
}
