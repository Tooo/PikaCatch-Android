package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.cmpt276a3.R;
import com.cmpt276a3.model.Game;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStartButton();
        setupOptionsButton();
    }

    private void setupStartButton() {
        Button button = findViewById(R.id.main_btnStart);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, GameScreen.class);
                startActivity(intent);
            }
        });
    }

    private void setupOptionsButton() {
        Button button = findViewById(R.id.main_btnOptions);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, Options.class);
                startActivity(intent);
            }
        });
    }
}