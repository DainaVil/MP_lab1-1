package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView infoTxt, trysTxt;
    Button subButton, newButton;
    EditText inputTxt;

    Random random = new Random();

    private int number, trys, level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTxt = (TextView) findViewById(R.id.infoTxt);
        trysTxt = (TextView) findViewById(R.id.popitTxt);
        inputTxt = (EditText) findViewById(R.id.editText);
        subButton = (Button) findViewById(R.id.button);
        newButton = (Button) findViewById(R.id.newbutton);

        number = random.nextInt(5);
        level = 1;
        trys = 5;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                newGame(level);
                break;
            case R.id.menu_low:
                newGame(1);
                break;
            case R.id.menu_avr:
                newGame(2);
                break;
            case R.id.menu_high:
                newGame(3);
                break;
            case R.id.menu_exit: finishAndRemoveTask();
            default: newGame(1);

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
            if (v.getId() == R.id.button) {
                if ((inputTxt.getText().toString().matches("[-+]?\\d+")) && (Integer.parseInt(inputTxt.getText().toString()) <= 5)
                        && (0 <= Integer.parseInt(inputTxt.getText().toString()))) {

                    if (trys > 0) {
                        if (Integer.parseInt(inputTxt.getText().toString()) == number) infoTxt.setText(getResources().getString(R.string.hit));
                        if (Integer.parseInt(inputTxt.getText().toString()) < number) infoTxt.setText(getResources().getString(R.string.less));
                        if (Integer.parseInt(inputTxt.getText().toString()) > number) infoTxt.setText(getResources().getString(R.string.more));
                        trys--;
                        trysTxt.setText("Попыток: " + trys);
                    }

                    if (trys == 0) infoTxt.setText(getResources().getString(R.string.lost));
                }
                else infoTxt.setText(getResources().getString(R.string.info));
            }
            if (v.getId() == R.id.newbutton) newGame(level);
    }

    public void newGame(Integer lvl) {

        level = lvl;
        int tr = 5;

        switch (level) {
            case 1:
                tr = 5;
                break;
            case 2:
                tr = 3;
                break;
            case 3:
                tr = 1;
                break;
        }

        trys = tr;

        infoTxt.setText(getResources().getString(R.string.info));
        trysTxt.setText("Попыток: " + trys);

        number = random.nextInt(5);

    }
}