package com.example.charlotte.readmemore.SuggestionFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.charlotte.readmemore.R;

/**
 * Created by Charlotte on 24/10/2016.
 */
public class SuggestionByThemeFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.theme_suggestion, container, false);

        super.onCreate(savedInstanceState);
        bundleItem(rootView);
        return rootView;
    }

    private void bundleItem(View v)
    {
        Button rechercheThemeButton = (Button) v.findViewById(R.id.rechercheThemeButton);
        final RelativeLayout resultatRecherche = (RelativeLayout) v.findViewById(R.id.resultatRecherche) ;

        rechercheThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultatRecherche.setVisibility(View.VISIBLE);
            }
        });
    }
}
