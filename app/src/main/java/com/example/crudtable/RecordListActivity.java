package com.example.crudtable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class RecordListActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);

        mListView = findViewById(R.id.listView);
    }
}

/*
* Design row for the listview
* Create class model
* Create custom adapter for listView
* */