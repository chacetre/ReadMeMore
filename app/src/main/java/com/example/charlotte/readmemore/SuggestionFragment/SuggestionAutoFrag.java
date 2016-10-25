package com.example.charlotte.readmemore.SuggestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlotte.readmemore.R;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class SuggestionAutoFrag extends Fragment {


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
}
