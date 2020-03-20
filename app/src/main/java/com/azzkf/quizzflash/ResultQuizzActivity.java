package com.azzkf.quizzflash;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultQuizzActivity extends AppCompatActivity {

    private int score;
    private int numberOfQuestion;
    private String congrats;
    // private int percent;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultQuizzActivity.this, ChooseActivity.class));
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_quizz);

        Intent srcIntent = getIntent();

        /***
         * Setupping all the Extras
         */
        score = srcIntent.getIntExtra("score", 0);
        numberOfQuestion = srcIntent.getIntExtra("questionnumber", 1);
        /***
         * Problem with the math apparently, using an integer seems to make percent = 0 need help
         */

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText((score*100)/numberOfQuestion + "%");
        TextView congrats = findViewById(R.id.congratsTextView);
        TextView display = findViewById(R.id.displayScoreTextView);
        display.setText(score + " / " + numberOfQuestion);

        /***
         * Some addition to the congrats for the player "NEVER GIVE UP"
         */

        congrats.setText("Congratulation !");

        /*if (percent == 100) {
            congrats.setText("PERFECT SCORE");
        } else if (percent < 100 && percent >= 75) {
            congrats.setText("Congratulation !");
        } else if (percent < 75 && percent >= 50) {
            congrats.setText("Not bad");
        } else if (percent < 50 && percent >= 25) {
            congrats.setText("Peu mieux faire");
        } else {
            congrats.setText("Wtf mate");
        }*/

        /***
         * If you wanna play again
         */
        Button back = findViewById(R.id.backMenuButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultQuizzActivity.this, ChooseActivity.class);
                startActivity(intent);
            }
        });

    }
}
