package com.example.charlotte.readmemore.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.ListFragment.ListReadFragment;
import com.example.charlotte.readmemore.ListFragment.ListReadingFragment;
import com.example.charlotte.readmemore.ListFragment.ListToReadFragment;
import com.example.charlotte.readmemore.PageView.SuggestionViewPagerAdapter;
import com.example.charlotte.readmemore.PageView.ViewPagerListAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionAutoFrag;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionByThemeFrag;

import java.util.List;
import java.util.Vector;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class SuggestionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity);

        List fragments = new Vector();

        // Ajout des Fragments dans la liste
        fragments.add(Fragment.instantiate(this,SuggestionAutoFrag.class.getName()));
        fragments.add(Fragment.instantiate(this,SuggestionByThemeFrag.class.getName()));

        ViewPager viewPager = (ViewPager) findViewById(R.id.pagerSuggestion);
        viewPager.setAdapter(new SuggestionViewPagerAdapter(getSupportFragmentManager(),fragments));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabsSuggestion);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

    }
}
