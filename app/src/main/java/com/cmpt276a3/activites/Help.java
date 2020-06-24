package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.cmpt276a3.R;

/**
 *  Help class manages the help activity
 *  It used to allow links to be clickable
 */
public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setupLinks();
    }

    private void setupLinks() {
        int[] idLinks = {R.id.help_txtCourse, R.id.help_txtPoGo, R.id.help_txtEmma, R.id.help_txtLG, R.id.help_txtHat, R.id.help_txtAnony, R.id.help_txtCave};

        for (int link:idLinks) {
            TextView textView = findViewById(link);
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}