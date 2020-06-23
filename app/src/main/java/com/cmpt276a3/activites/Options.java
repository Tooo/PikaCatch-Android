package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cmpt276a3.R;

public class Options extends AppCompatActivity {
    int[] mineChoices = {6, 10, 15, 20};
    int[][] boardChoices = {{4,6}, {5,10}, {6,15}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setupBoardSelector();
        setupMinesSelector();
    }

    private void setupBoardSelector() {
        String[] boardStringList = new String[boardChoices.length];
        for (int i = 0; i<boardChoices.length; i++) {
            boardStringList[i] = boardChoices[i][0] + " x " + boardChoices[i][1];
        }

        Spinner spinner = findViewById(R.id.opt_spinBoard);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Options.this, android.R.layout.simple_spinner_item, boardStringList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setupMinesSelector() {
        String[] mineStringList = new String[mineChoices.length];
        for (int i = 0; i<mineChoices.length; i++) {
            mineStringList[i] = mineChoices[i] + "";
        }


        Spinner spinner = findViewById(R.id.opt_spinMines);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Options.this, android.R.layout.simple_spinner_item, mineStringList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}