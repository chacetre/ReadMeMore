package com.example.charlotte.readmemore.Notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.charlotte.readmemore.R;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by Charlotte on 22/11/2016.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String[] tab = currentDateTimeString.split(" ");
        String[] HeureAlarme = MyAlarmService.getHeureAlarme().split(":");
        String[] tabHeure = tab[3].split(":");

        if (tabHeure[0].equals(HeureAlarme[0]) && tabHeure[1].equals(HeureAlarme[1])) {

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext())
                    .setWhen(System.currentTimeMillis())
                    .setContentText("Je suis la dame notif")
                    .setContentTitle("Dis le a chacha")
                    .setSmallIcon(R.drawable.ic_arrow_back_white_24dp)
                    .setAutoCancel(true);


            Notification notification = notificationBuilder.build();

            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            mNotifyMgr.notify(001, notification);


        }


    }
}
