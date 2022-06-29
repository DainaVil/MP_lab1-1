package com.example.diary4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, CalendarView.OnDateChangeListener
{
    private String inText = "";
    ListView ListView;
    ArrayList NameList = new ArrayList();
    ArrayAdapter arrayAdapter;
    EditText inputText;
    Button addButton;
    CalendarView calendar;
    String curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.edittext);

        addButton = findViewById(R.id.buttonAdd);
        ListView = findViewById(R.id.ListView);
        calendar = findViewById(R.id.calendarView);
        inputText = findViewById(R.id.edittext);

        arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                NameList);
        ListView.setAdapter(arrayAdapter);
        ListView.setOnItemClickListener(this);
        ListView.setOnItemLongClickListener(this);

        addButton.setOnClickListener(this);
        calendar.setOnDateChangeListener(this::onSelectedDayChange);


//        adapterUpdate();
//        arrayAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                break;
            case R.id.menu_exit: finishAndRemoveTask();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == addButton) {
            if (!inputText.getText().toString().isEmpty()) {
                NameList.add(inputText.getText().toString());
            }
        } else {
            Collections.sort(NameList);
            HashSet hs = new HashSet();
            hs.addAll(NameList);
            NameList.clear();
            NameList.addAll(hs);
        }
        arrayAdapter.notifyDataSetChanged();
//        writeFile();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        view.setBackgroundColor(Color.parseColor("#EBB8AC"));
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        int index = (int) ListView.getItemIdAtPosition(position);
        NameList.remove(index);
        ListView.removeViewInLayout(view);
//        writeFile();
        arrayAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
        curDate = String.valueOf(i) + String.valueOf(i1) + String.valueOf(i2);
        NameList.clear();
        arrayAdapter.notifyDataSetChanged();
//        adapterUpdate();
    }
}