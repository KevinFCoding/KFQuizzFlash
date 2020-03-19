package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListActivity extends AppCompatActivity {

    private List<Question> questions;
    private QuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);


            questions = new ArrayList<>();
            for (int i = 0; i < 18; i++) {
                questions.add(new Question("Donkey Kong", "Mario", "Kid Icarus", "Zelda", "D'où vient ce son", "Princesse", 0, R.raw.wwstart, "videogames", 0));
                questions.add(new Question("Skyrim", "Zelda", "", "Mario", "D'où vient ce son", "Papa moustachu", 0, R.raw.mariojump, "videogames", 0));
                questions.add(new Question("Europa", "Warhammer", "Starcraft", "Warcraft", "D'où vient ce son", "Plombier", 0, R.raw.leeroy, "videogames", 0));
                questions.add(new Question("Max Pain", "Mario", "Fire Emblem", "Skyrim", "D'où vient ce son", "V eme du nom", 0, R.raw.skyrim, "videogames", 1));
                questions.add(new Question("Half-Life", "Metal Gear", "GTA", "Portal", "D'où vient ce son", "This was a triumph", 0, R.raw.glados, "videogames", 1));
                questions.add(new Question("Sonic", "Mario", "Kid Icarus", "Warcraft", "D'où vient ce son", "MEUPORG", 0, R.raw.paysan, "videogames", 1));
                questions.add(new Question("Sonic", "Mario", "Kid Icarus", "Diablo", "D'où vient ce son", "Fresh meat", 0, R.raw.diablo, "videogames", 2));
                questions.add(new Question("Sonic", "Mario", "Kid Icarus", "Mortal Kombat", "D'où vient ce son", "Finish him", 0, R.raw.fatality, "videogames", 2));
                questions.add(new Question("Sonic", "Mario", "Kid Icarus", "Smash Bros", "D'où vient ce son", "FFA", 0, R.raw.ssbannouncer, "videogames",  2));
                questions.add(new Question("Sonic", "Mario", "Kid Icarus", "Mass Effect", "D'où vient ce son", "bestgame", 0, R.raw.shepard, "videogames",  2));

                questions.add(new Question("Stars", "Star Gate", "Star Trek", "Star Wars", "D'où vient ce son", "Parfait", 0, R.raw.chechew, "film",  0));
                questions.add(new Question("Batman", "Pain & Gain", "Predestination", "Inception", "D'où vient ce son", "Premier à l'utilisé", 0, R.raw.inceptionbutton, "Film",  0));
                questions.add(new Question("Chicago", "Casino", "Love Actually", "Lala Land", "D'où vient cette image", "Musical", R.drawable.lala, 0, "Film",  0));
                questions.add(new Question("Sonic", "Alien", "John Wick", "Léon", "D'où vient cette image", "Nettoyeur", R.drawable.leon, 0, "Film",  1));
                questions.add(new Question("Lalaland", "Don't Breath", "Just Breath", "Star Wars", "D'où vient ce son", "Trouve!", 0, R.raw.darthvaderr, "Film",  1));
                questions.add(new Question("Nul", "Labyrinthe", "Divergente", "Hunger Games", "D'où vient ce son", "Dalle", 0, R.raw.hungergame, "Film",  1));
                questions.add(new Question("Scream", "Life", "Earth", "Jurassic Park", "D'où vient ce son", "Dino", 0, R.raw.trex, "Film",  2));
                questions.add(new Question("Life", "Cloverfield", "Pacific Rim", "Godzilla", "D'où vient ce son", "Grodino", 0, R.raw.godzilla_1, "Film",  2));
                questions.add(new Question("Robocop", "John Wick", "Sniper", "StarWars", "D'où vient ce son", "Parfait", 0, R.raw.vaderihaveyounow, "Film",  2));

            }

            adapter = new QuestionsAdapter(questions);

            RecyclerView recyclerView = findViewById(R.id.listrecyclerView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

