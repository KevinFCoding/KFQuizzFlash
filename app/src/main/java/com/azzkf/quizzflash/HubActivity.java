package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class HubActivity extends AppCompatActivity {

    private String TAG = "HubActivity";
    private HubTag hub;
    private ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        Intent srcIntent = getIntent();

        /***
         * Directly getting information from the hubtag class to set-up the style.
         */
        hub = srcIntent.getParcelableExtra("tagChoosed");

        Button quizzButton = findViewById(R.id.hubQuizzButton);
        Button listButton = findViewById(R.id.hubListButton);

        final ImageView backgroundHub = findViewById(R.id.hubBackgroundImageView);

        backgroundHub.setImageResource(hub.getBackgroundHub());
        quizzButton.setText(hub.getWhichQuizz());
        listButton.setText(hub.getWhichList());

        /***
         * Preping here the different difficulty for the player to choose
         */
        quizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(HubActivity.this, QuizzGameActivity.class);

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(HubActivity.this);
                builderSingle.setIcon(R.drawable.leon);
                builderSingle.setTitle("Selectionnez la difficulté : ");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HubActivity.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Facile");
                arrayAdapter.add("Moyen");
                arrayAdapter.add("Difficile");

                builderSingle.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String strName = arrayAdapter.getItem(which);
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(HubActivity.this);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Niveau sélectionné : ");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                questions = generateQuestion(hub.getChoosedQuizz());
                                intent.putExtra("questions", questions);
                                intent.putExtra("back", hub.getBackgroundHub());
                                intent.putExtra("difficulty", strName);
                                startActivity(intent);
                            }
                        });
                        builderInner.show();
                    }
                });
                builderSingle.show();

            }
        });
        /***
         * Information for the second bouton : the ListView throwing the list of Questions
         * (Videogames or Films)
         */
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions = generateQuestion(hub.getChoosedQuizz());
                Intent intent = new Intent(HubActivity.this, QuestionsListActivity.class);
                intent.putExtra("questions", questions);
                startActivity(intent);
            }
        });

    }

    /***
     * Mock setup of the Question, it's a simple object with : assets and strings, need a difficulty
     * too to function or NPE.
     * @param choosedQuizz Toggle if which Questions is Charged
     * @return
     */
    private ArrayList<Question> generateQuestion(String choosedQuizz) {
        questions = new ArrayList<>();
        // If we use an API use a FOR here instead of this code

        OkHttpClient client = new OkHttpClient();
        
        /***
         * Can be setup in a JSON for better perf
         */
        if (choosedQuizz.equals("videogames")) {
            questions.add(new Question("Donkey Kong", "Mario", "Kid Icarus", "Zelda", "D'où vient ce son", "Princesse", 0, R.raw.wwstart, "videogames", "Facile"));
            questions.add(new Question("Skyrim", "Zelda", "", "Mario", "D'où vient ce son", "Papa moustachu", 0, R.raw.mariojump, "videogames", "Facile"));
            questions.add(new Question("Europa", "Warhammer", "Starcraft", "Warcraft", "D'où vient ce son", "Plombier", 0, R.raw.leeroy, "videogames", "Facile"));

            questions.add(new Question("Max Pain", "Mario", "Fire Emblem", "Skyrim", "D'où vient ce son", "V eme du nom", 0, R.raw.skyrim, "videogames", "Moyen"));
            questions.add(new Question("Half-Life", "Metal Gear", "GTA", "Portal", "D'où vient ce son", "This was a triumph", 0, R.raw.glados, "videogames", "Moyen"));
            questions.add(new Question("Everquest", "Warhammer", "Life is Strange", "Warcraft", "D'où vient ce son", "MEUPORG", 0, R.raw.paysan, "videogames", "Moyen"));

            questions.add(new Question("Path of Exil", "Mario", "Kid Icarus", "Diablo", "D'où vient ce son", "Fresh meat", 0, R.raw.diablo, "videogames", "Difficile"));
            questions.add(new Question("Street Fighter", "GTA", "Killer Instinct", "Mortal Kombat", "D'où vient ce son", "Finish him", 0, R.raw.fatality, "videogames", "Difficile"));
            questions.add(new Question("Tennis Party", "Pong", "Mario", "Smash Bros", "D'où vient ce son", "FFA", 0, R.raw.ssbannouncer, "videogames", "Difficile"));
            questions.add(new Question("GTA", "Walking Dead", "Star Trek Online", "Mass Effect", "D'où vient ce son", "bestgame", 0, R.raw.shepard, "videogames", "Difficile"));
        } else {


            questions.add(new Question("Stars", "Star Gate", "Star Trek", "Star Wars", "D'où vient ce son", "Parfait", 0, R.raw.chechew, "film", "Facile"));
            questions.add(new Question("Batman", "Pain & Gain", "Predestination", "Inception", "D'où vient ce son", "Premier à l'utilisé", 0, R.raw.inceptionbutton, "Film", "Facile"));
            questions.add(new Question("Chicago", "Casino", "Love Actually", "Lala Land", "D'où vient cette image", "Musical", R.drawable.lala, 0, "Film", "Facile"));

            questions.add(new Question("Sonic", "Alien", "John Wick", "Léon", "D'où vient cette image", "Nettoyeur", R.drawable.leon, 0, "Film", "Moyen"));
            questions.add(new Question("Lalaland", "Don't Breath", "Just Breath", "Star Wars", "D'où vient ce son", "Trouve!", 0, R.raw.darthvaderr, "Film", "Moyen"));
            questions.add(new Question("Nul", "Labyrinthe", "Divergente", "Hunger Games", "D'où vient ce son", "Dalle", 0, R.raw.hungergame, "Film", "Moyen"));

            questions.add(new Question("Scream", "Life", "Earth", "Jurassic Park", "D'où vient ce son", "Dino", 0, R.raw.trex, "Film", "Difficile"));
            questions.add(new Question("Life", "Cloverfield", "Pacific Rim", "Godzilla", "D'où vient ce son", "Grodino", 0, R.raw.godzilla_1, "Film", "Difficile"));
            questions.add(new Question("Robocop", "John Wick", "Sniper", "StarWars", "D'où vient ce son", "Parfait", 0, R.raw.vaderihaveyounow, "Film", "Difficile"));

        }
        // If no image add to all a placeholder sound image
        for (
                int j = 0; j < questions.size(); j++) {
            if (questions.get(j).getImgID() == 0) {
                questions.get(j).setImgID(R.drawable.sound);
            }
        }
        return questions;
    }
}
