package com.example.broadcastnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button sendMessage;
    public static final String BROADCAST_MESSAGE_ACTION = "com.example.broadcast.action.BROADCAST_MESSAGE";
    public static final String MESSAGE_SENT = "Сообщение отправлено";
    public static final String ALARM_MESSAGE = "Сообщение на экзамене";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        BroadcastReceiver br = new MyBroadcastReciever();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_MESSAGE_ACTION);
        this.registerReceiver(br, filter);

        sendMessage = findViewById(R.id.sendButton);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, MESSAGE_SENT, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setAction(BROADCAST_MESSAGE_ACTION);
                intent.putExtra(BROADCAST_MESSAGE_ACTION, ALARM_MESSAGE);
                sendBroadcast(intent);
            }
        });

    }
}