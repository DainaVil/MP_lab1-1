package com.example.guessnumber;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView infoTxt;
    Button subButton, newButton;
    EditText inputTxt;

    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoTxt = (TextView) findViewById(R.id.infoTxt);
        inputTxt = (EditText) findViewById(R.id.editText);
        subButton = (Button) findViewById(R.id.button);
        newButton = (Button) findViewById(R.id.newbutton);

        Random random = new Random();
        number = random.nextInt(5);
    }

    public void onClick(View v) {
            if (v.getId() == R.id.button) {
                if ((inputTxt.getText().toString().matches("[-+]?\\d+")) && (Integer.parseInt(inputTxt.getText().toString()) <= 5)
                        && (0 <= Integer.parseInt(inputTxt.getText().toString()))){
                    if (Integer.parseInt(inputTxt.getText().toString()) == number) infoTxt.setText(getResources().getString(R.string.hit));
                    if (Integer.parseInt(inputTxt.getText().toString()) < number) infoTxt.setText(getResources().getString(R.string.less));
                    if (Integer.parseInt(inputTxt.getText().toString()) > number) infoTxt.setText(getResources().getString(R.string.more));
                }
                else infoTxt.setText(getResources().getString(R.string.info));

            }
            if (v.getId() == R.id.newbutton) {
                Random random = new Random();
                number = random.nextInt(5);
                infoTxt.setText(getResources().getString(R.string.info));
            }

        }
}