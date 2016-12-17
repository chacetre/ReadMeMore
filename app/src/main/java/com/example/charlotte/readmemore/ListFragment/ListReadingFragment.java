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
public class ListReadingFragment extends RecyclerViewFragment {

    @Override
    protected List<Livre> filterBook(List<Livre> input) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            input.removeIf(livre -> livre.getReadingStatus() != Livre.Status.OnGoing);
        }
        else{
            for (Livre livre: input
                    ) {
                if(livre.getReadingStatus()!= Livre.Status.OnGoing) {
                    input.remove(livre);
                }
            }
        }
        return super.filterBook(input);
    }
}
