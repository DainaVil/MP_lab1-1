package com.example.broadcastnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyBroadcastReciever extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Получено сообщение: " + intent.getStringExtra("com.example.broadcast.action.BROADCAST_MESSAGE"),
                Toast.LENGTH_SHORT);
        String data = intent.getStringExtra("com.example.broadcast.action.BROADCAST_MESSAGE");
//        Log.d(TAG, data);
//        Intent sendIntent = new Intent();
//        sendIntent.putExtra("message", intent.getStringExtra("com.example.broadcast.action.BROADCAST_MESSAGE"));
//        startBroadcast();
    }

}