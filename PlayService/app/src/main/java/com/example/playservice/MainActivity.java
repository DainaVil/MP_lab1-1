package com.example.playservice;

import static android.app.Service.START_FLAG_RETRY;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStart = findViewById(R.id.button_start);
        final Button btnStop = findViewById(R.id.button_stop);
        final boolean[] isPlaying = {false};
        final boolean[] serviceStarted = {false};

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying[0]) {
                    if (!serviceStarted[0]) {
                        startService(new Intent(MainActivity.this, MyPlayService.class));
                        serviceStarted[0] = true;
                    }
                    else {
                        MyPlayService.musicContinue();
                    }
                    isPlaying[0] = true;
                    btnStart.setText("pause");
                    }
                else {
                    MyPlayService.musicPause();
                    isPlaying[0] = false;
                    btnStart.setText("play");
                }
            }

        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, MyPlayService.class));
                isPlaying[0] = false;
                serviceStarted[0] = false;
                btnStart.setText("play");
            }
        });
    }
}
