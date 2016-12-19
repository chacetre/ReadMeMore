package com.example.charlotte.readmemore.ListFragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlotte.readmemore.Activity.ListGeneralActivity;
import com.example.charlotte.readmemore.AdapterList;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class ListToReadFragment extends RecyclerViewFragment {
    List<Livre> output = new ArrayList<>();

    @Override
    protected List<Livre> filterBook(List<Livre> input) {
        output.clear();
        for (Livre livre: input) {
            if(livre.getReadingStatus().equals("Done")) {
                output.add(livre);
            }
        }

        return output;

    }
    
}
