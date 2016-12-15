package com.example.charlotte.readmemore.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.charlotte.readmemore.Notifications.MyAlarmService;
import com.example.charlotte.readmemore.R;
import com.example.charlotte.readmemore.Notifications.MyReceiver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlotte on 22/11/2016.
 */

public class NotificationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private List<String> heures = new ArrayList<String>();
    private List<String> min = new ArrayList<String>();
    private List<String> morningList = new ArrayList<String>();
    private Spinner spinnerMin;
    private Spinner spinnerHeure;
    private Spinner spinnerMorning;
    private String HeureAlarme = "00";
    private String minutes = "00";
    private String morning = "AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        Button lancer = (Button) findViewById(R.id.lancer);
        final Button stop = (Button) findViewById(R.id.annuler);

        spinnerHeure = (Spinner) findViewById(R.id.spinnerHeure);
        spinnerMin = (Spinner) findViewById(R.id.spinnerMinutes);

        spinnerHeure.setOnItemSelectedListener(this);
        spinnerMin.setOnItemSelectedListener(this);


        InitSpinner();

        lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNotification();
            }

        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopNotification();
            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        String test = String.valueOf(parent.getLastVisiblePosition());

        Spinner spin = (Spinner) parent;

        if (spin.getId() == R.id.spinnerHeure) {
            HeureAlarme = item;
        }
        if (spin.getId() == R.id.spinnerMinutes) {
            minutes = item;
        }


    }


    public void startNotification() {
        Intent service1 = new Intent(this, MyAlarmService.class);
        this.startService(service1);

        MyAlarmService.setHeureAlarme(HeureAlarme + ":" + minutes + ":00");
        MyAlarmService.setMorning(morning);

    }

    public void stopNotification() {

        stopService(new Intent(this,MyAlarmService.class));

    }

    public void InitSpinner() {
        for (int i = 0; i < 24; i++)
            if (i < 10)
                heures.add("0" + String.valueOf(i));
            else
                heures.add(String.valueOf(i));

        for (int j = 0; j < 60; j++)
            if (j < 10)
                min.add("0" + String.valueOf(j));
            else
                min.add(String.valueOf(j));

        morningList.add("AM");
        morningList.add("PM");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, min);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMin.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapterHeure = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, heures);
        dataAdapterHeure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHeure.setAdapter(dataAdapterHeure);

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


}
