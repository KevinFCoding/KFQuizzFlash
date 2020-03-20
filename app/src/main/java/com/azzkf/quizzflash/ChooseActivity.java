package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    /***
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        /***
         * Set-up buttons for latter OnClickUse
         */
        Button filmButton = findViewById(R.id.filmButton);
        Button vgButton = findViewById(R.id.vgButton);

        /***
         * Choose to play a Quizz about Movies or Video Games, take from HubTag to avoid
         * code repetition.
         */

        filmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HubTag hubOject = new HubTag("Commencer le Quizz Cinema", "Voir la liste des questions cinéma", R.drawable.filmz, "film");
                navigateToHub(hubOject);
            }
        });

        vgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HubTag hubOject = new HubTag("Commencer le Quizz JeuxVidéo", "Voir la liste des questions jeux vidéos", R.drawable.marioo, "videogames");
                navigateToHub(hubOject);
            }
        });

    }

    /***
     * Use the object ht to param the next View
     * @param ht an object from the Hubtag class, use to configurate the HubActivity with text.
     */
    private void navigateToHub(HubTag ht) {
        Intent intent = new Intent(ChooseActivity.this, HubActivity.class);
        intent.putExtra("tagChoosed", ht);
        startActivity(intent);
    }

}
