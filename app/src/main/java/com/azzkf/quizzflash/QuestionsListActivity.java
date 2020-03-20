package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListActivity extends AppCompatActivity {

    private static final String TAG = "QuestionsListActivity";
    private ArrayList<Question> questions;
    private QuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        Intent srcIntent = getIntent();
        questions = srcIntent.getParcelableArrayListExtra("questions");
        adapter = new QuestionsAdapter(questions);

        RecyclerView recyclerView = findViewById(R.id.listrecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}

