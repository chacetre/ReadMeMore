package com.example.charlotte.readmemore.PageView;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.charlotte.readmemore.ListFragment.RecyclerViewFragment;


/**
 * Created by Charlotte on 24/10/2016.
 */
public class ViewPagerListAdapter extends FragmentPagerAdapter {

    private final List<RecyclerViewFragment> fragments;
    private String tabTitles[] = new String[] {"A lire", "En lecture", "Déja lu" };

    //On fournit à l'adapter la liste des fragments à afficher
    public ViewPagerListAdapter(FragmentManager fm, List fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
