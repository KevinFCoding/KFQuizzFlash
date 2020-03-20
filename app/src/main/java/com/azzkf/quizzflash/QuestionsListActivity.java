package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

public class QuestionsListActivity extends AppCompatActivity {

    /***
     * List activity mainly using the Recycler View to display each Questions from the Quizz
     * Use the QuestionsAdapter to be able to setup the view and the OnClick on each items
     */
    private static final String TAG = "QuestionsListActivity";
    private ArrayList<Question> questions;
    private QuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        Intent srcIntent = getIntent();

        questions = srcIntent.getParcelableArrayListExtra("questions");
        /***
         * Had some problem with getting the QuestionsAdapter to work after changing for
         * an ArrayList instead of a full on Object.
         * QuestionAdapter still send an object to the QuizzGameActivity, had to do a lot of change
         */
        adapter = new QuestionsAdapter(questions);

        /***
         * Getting information from the adapter to the recycler
         */
        RecyclerView recyclerView = findViewById(R.id.listrecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}

