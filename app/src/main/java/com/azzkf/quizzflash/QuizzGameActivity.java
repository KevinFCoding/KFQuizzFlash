package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.List;

public class QuizzGameActivity extends AppCompatActivity {

    private static final String TAG = "QuizzGameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_game);

        Intent srcIntent = getIntent();

        ImageView backgroundImage = findViewById(R.id.gameBackgroundImageView);
        Question game = srcIntent.getParcelableExtra("hub");
        Log.i(TAG, "onCreate: " + game);

        int background = srcIntent.getIntExtra("back", 0);
        Log.i(TAG, "onCreate: " + background);
        backgroundImage.setImageResource(background);

        RadioButton radioA = findViewById(R.id.aRadioButton);
        RadioButton radioB = findViewById(R.id.bRadioButton);
        RadioButton radioC = findViewById(R.id.cRadioButton);
        RadioButton radioD = findViewById(R.id.dRadioButton);

        Button confirm = findViewById(R.id.confirmToggleButton);
        Button clue = findViewById(R.id.clueButton);
        Button playSound = findViewById(R.id.gameSoundButton);
        ImageView mainImage = findViewById(R.id.mainGameImageView);

        List<String> possibleAnswers;


        /* Preset all items to change in view */

    }
}
