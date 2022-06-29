package com.example.lifecycleservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

//    private Button startButton, bindButton, stopButton;
    private static final String TAG = "TAG";
    boolean bound;
    ServiceConnection serviceConnection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Activity onCreate");

//        startButton = findViewById(R.id.startButton);
//        stopButton = findViewById(R.id.stopButton);
//        bindButton = findViewById(R.id.bindButton);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i(TAG, "onServiceConnected");
                // MyBinder binder = (MyBinder) iBinder;
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i(TAG, "onServiceConnected");
                bound = false;
            }
        };
    }

//    public static ServiceConnection getServiceConnection() {
//        return serviceConnection;
//    }

    public void startService(View v){
        startService(new Intent(this, LCservice.class));
    }

    public void stopService(View v){
        stopService(new Intent(this, LCservice.class));
    }

    public void onClickBind(View v){
        bindService(new Intent(this, LCservice.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void onClickUnbind(View v){
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    // Логирование активити

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "Activity onPostResume");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Activity onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Activity onStop");
    }
}