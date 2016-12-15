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
import java.util.Stack;

/**
 * Created by louis on 08/11/2016.
 */

public class Utils {
    private static List<Livre> userLivres;
    private static List<Livre> globalLivres;

    private static FirebaseDatabase mDatabase;

    private static Stack<ValueEventListener> userLivresListeners;
    private static DatabaseReference userRef;

    private static Stack<ValueEventListener> globalLivresListeners;
    private static DatabaseReference globalRef;

    private static Stack<ValueEventListener> getUserLivresListeners() {
        if (userLivresListeners == null) {
            userLivresListeners = new Stack<>();
        }
        return userLivresListeners;
    }

    private static Stack<ValueEventListener> getGlobalLivresListeners() {
        if (globalLivresListeners == null) {
            globalLivresListeners = new Stack<>();
        }
        return globalLivresListeners;
    }

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
        if (globalRef == null) {
            globalRef=getDatabase().getReference("globalLibrary");
        }
        return globalRef;
    }

    private static void AddBookForUser(Livre input) {
        getUserRef().child(String.valueOf(input.hashCode())).setValue(input);
    }

    private static void RemoveBookForUser (Livre input) {
        getUserRef().child(String.valueOf(input.hashCode())).removeValue();
    }


    public static void AddUserValueListener(ValueEventListener vel) {
        getUserLivresListeners().push(getUserRef().addValueEventListener(vel));
    }

    public static void AddGlobalValueListener(ValueEventListener vel) {
        getGlobalLivresListeners().push(getGlobalRef().addValueEventListener(vel));
    }

    public static void setUserDBListener() {
        userLivres = new ArrayList<>();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if(firebaseAuth.getCurrentUser() == null) {
                if(userRef!=null) {
                    while (!getUserLivresListeners().isEmpty()) {
                        userRef.removeEventListener(getUserLivresListeners().pop());
                    }
                    userRef = null;
                }
                if(globalRef == null) {
                    while (!getGlobalLivresListeners().isEmpty()) {
                        globalRef.removeEventListener(getGlobalLivresListeners().pop());
                    }
                    globalRef = null;
                }
                userLivres.clear();
                //Clear pas globalLib pour economiser
            }
            else {
                userRef = Utils.getDatabase().getReference(firebaseAuth.getCurrentUser().getUid());
                globalRef = Utils.getDatabase().getReference("globalLibrary");
                getUserLivresListeners().push(userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                        userLivres=dataSnapshot.getValue(genericTypeIndicator);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("Firebase", "Failed to read value.", error.toException());
                    }
                }));
                getGlobalLivresListeners().push(globalRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<Livre>> genericTypeIndicator = new GenericTypeIndicator<List<Livre>>() {};
                        globalLivres=dataSnapshot.getValue(genericTypeIndicator);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w("Firebase", "Failed to read value.", error.toException());
                    }
                }));
            }
            }
        });

    }

    public static void removeUserListener(ValueEventListener input) {
        getUserRef().removeEventListener(input);
    }
}
