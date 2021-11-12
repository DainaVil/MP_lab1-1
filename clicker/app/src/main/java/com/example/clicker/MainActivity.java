package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mainText;
    Button plusBtn, minusBtn, resetBtn;
    ImageButton imageBtn;

    long score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.mainTxt);
        plusBtn = (Button) findViewById(R.id.btnPlus);
        minusBtn = (Button) findViewById(R.id.btnMinus);
        resetBtn = (Button) findViewById(R.id.btnReset);
        imageBtn = (ImageButton) findViewById(R.id.imgButton);

        plusBtn.setOnClickListener(clickListener);
        minusBtn.setOnClickListener(clickListener);
        resetBtn.setOnClickListener(clickListener);
        imageBtn.setOnClickListener(clickListener);

        score = 0;
    }

    final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ((v.getId() == R.id.btnPlus) || (v.getId() == R.id.imgButton)) score++;
            if (v.getId() == R.id.btnMinus && score > 0) score--;
            if (v.getId() == resetBtn.getId()) score = 0;

            if ((score == 2) ||(score == 3) || (score == 4))
            mainText.setText("Кнопка нажата " + score + " раза");
            else mainText.setText("Кнопка нажата " + score + " раз");
        }
    };
}



