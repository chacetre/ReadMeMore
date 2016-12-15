package com.example.charlotte.readmemore.Notifications;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by Charlotte on 22/11/2016.
 */

public class MyAlarmService extends Service {

    private NotificationManager mManager;
    private static String heureAlarmeL = "0:00:00"; //dix heures
    private static String morning = "AM";

    public static String getMorning() {
        return morning;
    }

    public static void setMorning(String morning) {
        MyAlarmService.morning = morning;
    }

    public static String getHeureAlarme() {
        return heureAlarmeL;
    }

    public static void setHeureAlarme(String heureAlarme) {
        heureAlarmeL = heureAlarme;
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        super.onCreate();

    }


    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        int repeatTime = 45;  //Repeat alarm time in seconds
        AlarmManager processTimer = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent intent2 = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,  intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        processTimer.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),repeatTime*1000, pendingIntent);


    }



    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
