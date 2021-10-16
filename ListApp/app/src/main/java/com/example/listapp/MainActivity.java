package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = findViewById(R.id.main_textview);
        mainTextView.setText("Simple list");

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
        updButton = findViewById(R.id.upd_button);
        updButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.main_edittext);

        mainListView = findViewById(R.id.main_listview);
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);
        mainListView.setAdapter(mArrayAdapter);

        mainListView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == addButton) {
            if (!mainEditText.getText().toString().isEmpty()) {
                mNameList.add(mainEditText.getText().toString());
            }
        } else {
            Collections.sort(mNameList);
            HashSet hs = new HashSet();
            hs.addAll(mNameList);
            mNameList.clear();
            mNameList.addAll(hs);
        }
        mArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mNameList.remove(position);
        mArrayAdapter.notifyDataSetChanged();
    }

    TextView mainTextView;
    Button addButton;
    Button updButton;
    EditText mainEditText;

    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();

}