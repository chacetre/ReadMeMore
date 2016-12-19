package com.example.charlotte.readmemore.SuggestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class SuggestionAutoFrag extends Fragment {

    private static ValueEventListener userListener;
    private ValueEventListener globalListener;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.auto_suggestion, container, false);


        super.onCreate(savedInstanceState);
        return rootView;
    }



    private ValueEventListener setUserListener(ValueEventListener valueEventListener) {
        if (userListener != null) {
            Utils.removeUserListener(userListener);
            userListener = null;
        }
        userListener = valueEventListener;
        return userListener;
    }

    private ValueEventListener setGlobalListener(ValueEventListener valueEventListener) {
        if (globalListener != null) {
            Utils.removeUserListener(globalListener);
            globalListener = null;
        }
        globalListener = valueEventListener;
        return globalListener;
    }
}
