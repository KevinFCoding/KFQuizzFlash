package com.azzkf.quizzflash;

import androidx.activity.OnBackPressedCallback;
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
import java.util.ArrayList;
import java.util.Collections;

public class QuizzGameActivity extends AppCompatActivity implements Serializable {

    /***
     * Main class of the application, get information straight from the Hub, from the background
     * to all the ArrayList<Question> containing all objects for the Quizz.
     */

    private static final String TAG = "QuizzGameActivity";
    private ArrayList<Question> questionDifficulty;
    private Question questionStandAlone;
    private ArrayList<String> answers;
    private int score;
    private int questionNumber;
    private int clickCount;
    private String trueanswer;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(QuizzGameActivity.this, ChooseActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz_game);


        Intent srcIntent = getIntent();

        /***
         * Presetting paramater gotten via getIntent
         *
         */
        final ArrayList<Question> question = srcIntent.getParcelableArrayListExtra("questions");
        Log.i(TAG, "onCreate: " + question);
        final String difficulty = srcIntent.getStringExtra("difficulty");

        questionNumber = srcIntent.getIntExtra("questionnumber", 1);
        score = srcIntent.getIntExtra("score", 0);
        clickCount = srcIntent.getIntExtra("clickcount", 0);
        /*We catch only the objects from the difficulty needed */

        /* We process the difficulty only the first time */

        /***
         * Processing the difficulty only once, but still need to send it up to avoid an NPE
         * (from the Standalone coming from the ListActivity)
         */
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

        /***
         * Two different way to set the answers array, first one for the list, 2nd one for the usual
         */
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


        /***
         * Both the Objets and their answers are shuffle for the randomization.
         */
        Collections.shuffle(answers);


        /* Preset all items to change in view */

        /***
         * Sadly had to take measure to validate changes (coming from an Object instead of and List
         */
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

        /***
         * Hardcoded RadioButton directly in the XML, manage to set up all answer randomly in there
         */
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

        /***
         * Room to improve
         */
        if (difficulty.equals("Special")) {
            success.setText(questionStandAlone.getQuestion() + " ?");
        } else {
            success.setText(questionDifficulty.get(0).getQuestion() + " ?");
        }
        success.setVisibility(View.VISIBLE);

        /* Checking if the answer is the good one */

        /***
         * Lots of thing happening for confirmation :
         * Text Button change, 2 way possible
         * When the Quizz is generated from the List => directly on the Result
         * Send to result or restart the same class with new parameter
         * We remove the index(0) at each shuffle, so the Array get Smaller, until it's empty
         * Once it's empty the game stop, we count the point
         */
        final Button confirm = findViewById(R.id.confirmToggleButton);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedAnswers = answersGroup.getCheckedRadioButtonId();
                if (selectedAnswers == -1){
                    Toast.makeText(QuizzGameActivity.this,
                            "Veuillez choisir une réponse", Toast.LENGTH_LONG).show();
                    return;
                }
                RadioButton selectedButton = findViewById(selectedAnswers);
                if (difficulty.equals("Special")) {
                    if (selectedButton.getText().equals(trueanswer)) {
                        score += 1;
                    }

                    Intent endSpecial = new Intent(QuizzGameActivity.this, ResultQuizzActivity.class);
                    endSpecial.putExtra("score", score);
                    endSpecial.putExtra("questionnumber", questionNumber);
                    startActivity(endSpecial);
                }

                if (clickCount == 0 && difficulty.equals("Special") == false) {
                    questionDifficulty.remove(0);
                    if (questionDifficulty.size() == 0) {
                        if (selectedButton.getText().equals(trueanswer)) {
                            score += 1;
                            success.setText("Bonne réponse !");
                            success.setVisibility(View.VISIBLE);
                        }
                        else{
                            success.setText("Mauvaise réponse !");
                            success.setVisibility(View.VISIBLE);
                        }
                            confirm.setText("Voir résultats");
                    } else {
                        confirm.setText("Prochaine question");
                        if (selectedButton.getText().equals(trueanswer)) {
                            success.setText("Bonne réponse !");
                            success.setVisibility(View.VISIBLE);
                            score += 1;
                        } else {
                            success.setText("Mauvaise réponse !");
                            success.setVisibility(View.VISIBLE);
                        }
                    }
                    clickCount += 1;
                } else {
                    if (difficulty.equals("Special") || questionDifficulty.size() == 0) {
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
                        intent.putExtra("difficulty", difficulty);
                        startActivity(intent);
                    }
                }
            }

        });

        /* Add a little clue pop-up */

        /***
         * Clue button to help the player, still had to preset a if condition
         */
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

        /***
         * Play sound is sound there's, if not, there's an image
         */
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

        /***
         * Sound image still display in the background, can remove by targeting it's ID,
         * checking it and removing the View when it's a sound.
         */

        ImageView mainImage = findViewById(R.id.mainGameImageView);
        if (difficulty.equals("Special")) {
            mainImage.setImageResource(questionStandAlone.getImgID());
        } else {
            mainImage.setImageResource(questionDifficulty.get(0).getImgID());
        }

    }

    /***
     * Preping the Game depending on the difficulty chosen
     * @param question ArrayList<Question> with all the object and the difficulty level in it.
     * @param difficulty the Difficulty chosen by the player before entering the Quizz.
     * @return
     */
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

    /***
     * Setting up the answers list for the first index(0) of the ArrayList created with the
     * difficulty set up.
     * @param questionDifficulty The smaller and shuffled ArrayList with only the difficulty asked
     * @return
     */
    private ArrayList<String> createAnswersArray(ArrayList<Question> questionDifficulty) {
        answers = new ArrayList<>();
        answers.add(questionDifficulty.get(0).getAnswerA());
        answers.add(questionDifficulty.get(0).getAnswerB());
        answers.add(questionDifficulty.get(0).getAnswerC());
        answers.add(questionDifficulty.get(0).getGoodAnswers());
        return answers;
    }
}
