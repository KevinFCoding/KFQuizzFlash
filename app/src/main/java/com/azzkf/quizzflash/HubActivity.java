package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HubActivity extends AppCompatActivity {

    private HubTag hub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        Intent srcIntent = getIntent();

        hub = srcIntent.getParcelableExtra("tagChoosed");


        Button quizzButton = findViewById(R.id.hubQuizzButton);
        Button listButton = findViewById(R.id.hubListButton);

        ImageView backgroundHub = findViewById(R.id.hubBackgroundImageView);

        backgroundHub.setImageResource(hub.getBackgroundHub());
        quizzButton.setText(hub.getWhichQuizz());
        quizzButton.setText(hub.getWhichList());
        quizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HubActivity.this, QuizzGameActivity.class);
                startActivity(intent);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HubActivity.this, QuestionsListActivity.class);
                startActivity(intent);
            }
        });
    }
}
