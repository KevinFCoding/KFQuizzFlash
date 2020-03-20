package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizzGameActivity extends AppCompatActivity implements Serializable {

    private static final String TAG = "QuizzGameActivity";
    private ArrayList<Question> questionDifficulty;
    private Question questionStandAlone;
    private ArrayList<String> answers;
    private RadioButton radioButton;
    private MediaPlayer sound;
    private int score;
    private int questionNumber;
    private int questionLeft;
    private int clickCount;
    private String trueanswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_game);

        Intent srcIntent = getIntent();

        final ArrayList<Question> question = srcIntent.getParcelableArrayListExtra("questions");
        Log.i(TAG, "onCreate: " + question);
        final String difficulty = srcIntent.getStringExtra("difficulty");

        questionNumber = srcIntent.getIntExtra("questionnumber", 1);
        score = srcIntent.getIntExtra("score", 0);
        clickCount = srcIntent.getIntExtra("clickcount", 0);
        /*We catch only the objects from the difficulty needed */

        /* We process the difficulty only the first time */

        if (questionNumber == 1) {
            if (difficulty.equals("Special")) {
                questionStandAlone = srcIntent.getParcelableExtra("questions");
            } else {
                questionDifficulty = createArrayDifficulty(question, difficulty);
                Collections.shuffle(questionDifficulty);
            }
        } else {
            questionDifficulty = srcIntent.getParcelableArrayListExtra("questions");
            Collections.shuffle(questionDifficulty);

        }

        /* Get the answers from the first shuffled object */

        if (difficulty.equals("Special")) {

            answers = new ArrayList<String>();
            answers.add(questionStandAlone.getAnswerA());
            answers.add(questionStandAlone.getAnswerB());
            answers.add(questionStandAlone.getAnswerC());
            answers.add(questionStandAlone.getGoodAnswers());
        } else {
            answers = createAnswersArray(questionDifficulty);

        }
        /*Shuffle them */

        Collections.shuffle(answers);


        /* Preset all items to change in view */

        if (difficulty.equals("Special")) {
            trueanswer = questionStandAlone.getGoodAnswers();
        } else {
            trueanswer = questionDifficulty.get(0).getGoodAnswers();
        }

        TextView index = findViewById(R.id.indexQuestionTextView);
        index.setText("#" + questionNumber);
        ImageView backgroundImage = findViewById(R.id.gameBackgroundImageView);
        Question game = srcIntent.getParcelableExtra("hub");

        final int background = srcIntent.getIntExtra("back", 0);

        backgroundImage.setImageResource(background);

        /* Create a radio button for each question, set up the answer */

        RadioButton radioA = findViewById(R.id.aRadioButton);
        radioA.setText(answers.get(0));
        RadioButton radioB = findViewById(R.id.bRadioButton);
        radioB.setText(answers.get(1));
        RadioButton radioC = findViewById(R.id.cRadioButton);
        radioC.setText(answers.get(2));
        RadioButton radioD = findViewById(R.id.dRadioButton);
        radioD.setText(answers.get(3));

        final RadioGroup answersGroup = findViewById(R.id.gameRadioGroup);
        final RadioButton selectedButton;
        final TextView success = findViewById(R.id.successGameTextView);

        if (difficulty.equals("Special")) {
            success.setText(questionStandAlone.getQuestion() + " ?");
        } else {
            success.setText(questionDifficulty.get(0).getQuestion() + " ?");
        }
        success.setVisibility(View.VISIBLE);

        /* Checking if the answer is the good one */

        final Button confirm = findViewById(R.id.confirmToggleButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAnswers = answersGroup.getCheckedRadioButtonId();
                RadioButton selectedButton = findViewById(selectedAnswers);
                if (clickCount == 0) {
                    if (difficulty.equals("Special")) {
                        confirm.setText("Voir résultats");
                    } else {
                        questionDifficulty.remove(0);
                        if (questionDifficulty.isEmpty()) {
                            confirm.setText("Voir résultats");
                        } else {
                            confirm.setText("Prochaine question");
                        }
                        clickCount += 1;
                    }

                    if (selectedButton.getText().equals(trueanswer)) {
                        success.setText("Bonne réponse !");
                        success.setVisibility(View.VISIBLE);
                        score += 1;
                    } else {
                        success.setText("Mauvaise réponse !");
                        success.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (difficulty.equals("Special") || questionDifficulty.isEmpty()) {
                        Intent end = new Intent(QuizzGameActivity.this, ResultQuizzActivity.class);
                        end.putExtra("score", score);
                        end.putExtra("questionnumber", questionNumber);
                        Log.i(TAG, "onClick: score : " + score + " & number " + questionNumber);
                        startActivity(end);
                    } else {
                        questionNumber += 1;
                        Intent intent = new Intent(QuizzGameActivity.this, QuizzGameActivity.class);
                        intent.putExtra("questions", questionDifficulty);
                        intent.putExtra("score", score);
                        intent.putExtra("questionnumber", questionNumber);
                        intent.putExtra("clickcount", 0);
                        intent.putExtra("back", background);
                        startActivity(intent);
                    }
                }
            }
        });

        /* Add a little clue pop-up */

        Button clue = findViewById(R.id.clueButton);
        clue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (difficulty.equals("Special")) {
                    Toast.makeText(QuizzGameActivity.this,
                            questionStandAlone.getClue(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizzGameActivity.this,
                            questionDifficulty.get(0).getClue(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button playSound = findViewById(R.id.gameSoundButton);

        if (difficulty.equals("Special")) {
            if (questionStandAlone.getSound() != 0) {
                final MediaPlayer sound = MediaPlayer.create(this, questionStandAlone.getSound());
                playSound.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sound.start();
                    }
                });
            }
        } else if (questionDifficulty.get(0).getSound() != 0) {
            final MediaPlayer sound = MediaPlayer.create(this, questionDifficulty.get(0).getSound());
            playSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sound.start();
                }
            });
        } else {
            playSound.setVisibility(View.INVISIBLE);
        }

        ImageView mainImage = findViewById(R.id.mainGameImageView);
        if (difficulty.equals("Special")) {
            mainImage.setImageResource(questionStandAlone.getImgID());
        } else {
            mainImage.setImageResource(questionDifficulty.get(0).getImgID());
        }
    }

    private ArrayList<Question> createArrayDifficulty(ArrayList<Question> question, String difficulty) {
        questionDifficulty = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            if (question.get(i).getDifficulty().equals(difficulty)) {
                questionDifficulty.add(question.get(i));
                Log.i(TAG, "onCreate: questionDifficu" + questionDifficulty);
            }
        }
        return questionDifficulty;
    }

    private ArrayList<String> createAnswersArray(ArrayList<Question> questionDifficulty) {
        answers = new ArrayList<>();
        answers.add(questionDifficulty.get(0).getAnswerA());
        answers.add(questionDifficulty.get(0).getAnswerB());
        answers.add(questionDifficulty.get(0).getAnswerC());
        answers.add(questionDifficulty.get(0).getGoodAnswers());
        return answers;
    }
}
