package com.example.charlotte.readmemore.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.example.charlotte.readmemore.Livre;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Charlotte on 23/10/2016.
 */
public class StatistiqueActivity extends AppCompatActivity {

    private ImageView backHome;
    private XYPlot plot;

    private List<Livre> userLivres;
    private List<Livre> globalLivres;
    private static ValueEventListener userListener;
    private static ValueEventListener globalListener;


    private ValueEventListener setUserListener(ValueEventListener valueEventListener) {
        if(userListener!=null) {
            Utils.removeUserListener(userListener);
            userListener=null;
        }
        userListener=valueEventListener;
        return userListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistique_activity);

        plot = (XYPlot) findViewById(R.id.plot);
        backHome = (ImageView) findViewById(R.id.backHome) ;


        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatistiqueActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

        Utils.AddUserValueListener(setUserListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLivres=Utils.getUserLivres();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        }));

        initPlot();

    }

    /*Initialise le graphique */

    public void initPlot(){

        final String[] domainLabels = {"janvier", "fevrier", "mars", "avril", "mai", "juin","juillet","aout","septembre","octobre","novembre","decembre"};
        Number[] series1Numbers = {1, 10 , 50 ,30 ,15, 12,12,12,12,12,12,12 };


        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series1");


        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format =
                new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);


        // just for fun, add some smoothing to the lines:
        // see: http://androidplot.com/smooth-curves-and-androidplot/
        series1Format.setInterpolationParams(
                new CatmullRomInterpolator.Params(12, CatmullRomInterpolator.Type.Centripetal));


        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);


        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                int i = Math.round(((Number) obj).floatValue());
                return toAppendTo.append(domainLabels[i]);
            }
            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;
            }
        });
    }
}
