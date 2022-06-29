package com.example.threadednotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;


import androidx.core.app.NotificationCompat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationService extends Service {

        private static final String CHANNEL_ID = "com.example.notifications";
        private static int notification_id = 0;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);


        @Override
        public IBinder onBind(Intent intent) {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public int onStartCommand(Intent intent, int flags, final int startId) {
            scheduler.scheduleAtFixedRate(new MyRunnable(1), 0, 2, TimeUnit.SECONDS);
            scheduler.scheduleAtFixedRate(new MyRunnable(2), 0, 3, TimeUnit.SECONDS);
            return START_NOT_STICKY;
        }

        @Override
        public void onDestroy(){
            scheduler.shutdown();
            super.onDestroy();
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

        public class MyRunnable implements Runnable {
            int type;
            String message_title;

            public MyRunnable(Integer type) {
                this.type=type;
                message_title = "Сообщение из " + type + " потока";
            }
            @Override
            public void run() {
                sendNotification(message_title);
            }
        }

    public void sendNotification(String message_title) {
        createNotificationChannel();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(message_title)
                .setContentText("Текст уведомления")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
        notificationManager.notify(notification_id++,notification);
    }

    }