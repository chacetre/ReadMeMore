package com.example.charlotte.readmemore.PageView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.charlotte.readmemore.ListFragment.ListTabFragment;
import com.example.charlotte.readmemore.ListLivres;


/**
 * Created by Charlotte on 24/10/2016.
 */
public class ListViewPagerAdapter extends FragmentStatePagerAdapter {

    ListLivres listLivres = new ListLivres();
    private String tabTitles[] = new String[] {"A lire", "En lecture", "Déja lu" };

    //On fournit à l'adapter la liste des fragments à afficher
    public ListViewPagerAdapter(FragmentManager fm, ListLivres listLivres) {
        super(fm);
        this.listLivres = listLivres ;

        // TODO trié liste ici ou dans liste activity
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListTabFragment tab1 = new ListTabFragment();
                Bundle args = new Bundle();
                args.putParcelable("list", listLivres);
                tab1.setArguments(args);
                // passer liste dans arguments
                return tab1;
            case 1:
                ListTabFragment tab2 = new ListTabFragment();
                Bundle args1 = new Bundle();
                args1.putParcelable("list",listLivres);
                tab2.setArguments(args1);
                return tab2;
            case 2:
                ListTabFragment tab3 = new ListTabFragment();
                Bundle args2 = new Bundle();
                args2.putParcelable("list",listLivres);
                tab3.setArguments(args2);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
