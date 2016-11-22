package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.charlotte.readmemore.R;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class StatistiqueActivity extends AppCompatActivity {

    private ImageView backHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistique_activity);

        backHome = (ImageView) findViewById(R.id.backHome) ;

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatistiqueActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
}
