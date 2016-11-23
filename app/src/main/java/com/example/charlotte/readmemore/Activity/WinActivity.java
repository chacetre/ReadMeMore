package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charlotte.readmemore.R;

/**
 * Created by Charlotte on 23/11/2016.
 */

public class WinActivity extends AppCompatActivity {

    private ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_activity);

        Bundle b = getIntent().getExtras();
        int nb = b.getInt("nbPageLu");

        backHome = (ImageView) findViewById(R.id.backHome) ;
        TextView nbPage = (TextView) findViewById(R.id.totalPoint);

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        nbPage.setText(String.valueOf(nb) + " points");

        // TODO boucle if
    }
}
