package com.example.charlotte.readmemore.ListFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by louis on 08/11/2016.
 */

public class RecyclerViewFragment extends Fragment {

    private AdapterList mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Livre> bookList;
    private List<Livre> hold;
    private List<Livre> listFilter;
    private List<Livre> localChacha;
    private boolean toUpdate = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bookList = new ArrayList<>();
        listFilter = new ArrayList<>();
        mAdapter = new AdapterList(bookList);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (toUpdate) {
            updateBookList(hold);
            toUpdate = false;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.list_book, container, false);

        super.onCreate(savedInstanceState);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter = new AdapterList(bookList);
        mAdapter.notifyDataSetChanged();
        updateBookList(listManually()); // Juste pour Charlotte car pas connection a la dataBase
        return rootView;
    }

    protected List<Livre> filterBook(List<Livre> input) {

        listFilter.clear();
        int currentItem = ListGeneralActivity.getViewPager().getCurrentItem() ;
        String currentItemS = "";
        if(currentItem == 0)
            currentItemS = "1";
        if(currentItem == 1)
            currentItemS = "2";
        if(currentItem == 2)
            currentItemS = "3";

        for (Livre l : input)
        {
            if(l.getStatus().equals(currentItemS))
                listFilter.add(l);
        }
        return listFilter;
    }

    public void updateBookList(List<Livre> livres) {
        if (this.isVisible()) {
            bookList.clear();
            bookList.addAll(filterBook(livres));
            Log.i("BookList", " has been updated");
//            mAdapter.UpdateList(bookList);
            mAdapter.notifyDataSetChanged();
        } else {
            hold = livres;
            toUpdate = true;
        }
    }

    public List<Livre> listManually(){
        localChacha = new ArrayList<>();
        localChacha.clear();
        Livre l = new Livre("Anna et Elsa","Georges","2016",202,"1",152);
        localChacha.add(l);

        l = new Livre("Bob l'éponge","Marie","2016",202,"2",152);
        localChacha.add(l);

        l = new Livre("Gasper le fantome","Lise","2016",202,"3",152);
        localChacha.add(l);

        l = new Livre("mamie le chien","marion","2016",202,"2",152);
        localChacha.add(l);

        l = new Livre("Leo le veau","lea","2016",202,"1",152);
        localChacha.add(l);

        return localChacha;
    }
}
