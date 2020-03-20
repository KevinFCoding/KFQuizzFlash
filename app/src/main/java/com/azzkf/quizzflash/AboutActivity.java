package com.azzkf.quizzflash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    String TAG = "AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView version = findViewById(R.id.versionAboutTextView);
        /***
         * Directly get the VERSION NAME from the Config so it's Dynamic on the upgrade
         */
        version.setText(BuildConfig.VERSION_NAME);
    }
}
