package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cmpt276a3.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setupSkipButton();

    }

    private void setupSkipButton() {
        Button button = findViewById(R.id.welc_btnSkip);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, MainMenu.class);
                finish();
                startActivity(intent);
            }
        });
    }
}