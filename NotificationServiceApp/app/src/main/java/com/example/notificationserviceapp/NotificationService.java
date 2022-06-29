package com.example.notificationserviceapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notificationserviceapp.ui.login.LoginActivity;

public class NotificationService extends Service {

        private static final int ONGOING_NOTIFICATION_ID = 1;
        private static final String CHANNEL_ID = "com.example.notifications";
        private static final String EXTRA_NOTIFICATION_ID = "clickActionIntent";

        @Override
        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, final int startId) {

            Intent notIntent = new Intent(getApplicationContext(), NotificationActivity.class);
            notIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent notPending = PendingIntent.getActivity(getApplicationContext(), 0, notIntent, 0);

            Intent firstActionIntent = new Intent(getApplicationContext(), MainActivity2.class);
            firstActionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            firstActionIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
            PendingIntent firstPending = PendingIntent.getActivity(getApplicationContext(), 0, firstActionIntent, 0);

            Intent secondActionIntent = new Intent(getApplicationContext(), LoginActivity.class);
            secondActionIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            secondActionIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
            PendingIntent secondPending = PendingIntent.getActivity(getApplicationContext(), 0, secondActionIntent, 0);

            createNotificationChannel();
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_picture)
                    .setContentTitle(getString(R.string.notification_title))
                    .setContentText(getString(R.string.notification_message))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(notPending)
                    .addAction(R.mipmap.ic_launcher, getString(R.string.first_activity), firstPending)
                    .addAction(R.mipmap.ic_launcher, getString(R.string.second_activity), secondPending)
                    .build();
//            startForeground(ONGOING_NOTIFICATION_ID, notification);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            notificationManager.notify(ONGOING_NOTIFICATION_ID, notification);
            return START_STICKY;
        }

        private void createNotificationChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_desc);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
        }

    }