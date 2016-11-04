package com.example.charlotte.readmemore.ListFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charlotte.readmemore.AdapterList;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class ListToReadFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Livre> bookList = new ArrayList<>();

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

        // specify an adapter (see also next example)
        mAdapter = new AdapterList(bookList);
        mRecyclerView.setAdapter(mAdapter);

        bookList.clear();
        initListBook();

        return rootView;
    }

    private void initListBook () { // pour le moment on les met en dur ensuite on ira les chercher dans la BD

        Livre book = new Livre("Harry Potter et l'Enfant Maudit", "J.K. Rowling", "2016");
        bookList.add(book);

        book = new Livre("Ki et Hi", "Fanny Pantigny", "2016");
        bookList.add(book);

        book = new Livre("Un président ne devrait pas dire ça… Les secrets d'un quinquennat", "Gérard Davet", "2016");
        bookList.add(book);

        book = new Livre("Lettres à Anne 1962-1995", "François Mitterrand", "2016");
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }
}
