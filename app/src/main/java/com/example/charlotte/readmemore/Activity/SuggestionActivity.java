package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.charlotte.readmemore.PageView.SuggestionViewPagerAdapter;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionAutoFrag;
import com.example.charlotte.readmemore.SuggestionFragment.SuggestionByThemeFrag;

import java.util.List;
import java.util.Vector;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class SuggestionActivity extends AppCompatActivity {

    private ImageView backHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_activity);
        backHome = (ImageView) findViewById(R.id.backHome) ;

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuggestionActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

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
