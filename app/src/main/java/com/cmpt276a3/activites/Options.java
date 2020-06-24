package com.cmpt276a3.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cmpt276a3.R;

public class Options extends AppCompatActivity {
    int[][] boardChoices;
    int[] mineChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        setupBoardSelector();
        setupMinesSelector();
        loadOptions();
    }

    private void setupBoardSelector() {
        int rows;
        int cols;
        String[] boardResource = getResources().getStringArray(R.array.board_size);
        String[] boardStringList = new String[boardResource.length];
        boardChoices = new int[boardResource.length][2];

        for (int i = 0; i < boardResource.length; i++) {
            String[] boardNumbers = boardResource[i].split(",");
            rows = Integer.parseInt(boardNumbers[0]);
            cols = Integer.parseInt(boardNumbers[1]);
            boardChoices[i][0] = rows;
            boardChoices[i][1] = cols;

            boardStringList[i] = rows + " x " + cols;
        }



        Spinner spinner = findViewById(R.id.opt_spinBoard);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Options.this, android.R.layout.simple_spinner_item, boardStringList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("board settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("width", boardChoices[position][1]);
                editor.putInt("height", boardChoices[position][0]);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void setupMinesSelector() {
        mineChoices = getResources().getIntArray(R.array.mine_count);
        int mineCount = mineChoices.length;
        String[] mineStringList = new String[mineCount];


        for (int i = 0; i<mineCount; i++) {
            mineStringList[i] = mineChoices[i] + "";
        }

        Spinner spinner = findViewById(R.id.opt_spinMines);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Options.this, android.R.layout.simple_spinner_item, mineStringList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getSharedPreferences("board settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("mines", mineChoices[position]);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void loadOptions() {
        Spinner mineSpinner = findViewById(R.id.opt_spinMines);
        Spinner boardSpinner = findViewById(R.id.opt_spinBoard);
        SharedPreferences sharedPreferences = getSharedPreferences("board settings", MODE_PRIVATE);

        int height = sharedPreferences.getInt("height", 4);
        int indexBoard = 0;
        for (int i = 0; i<boardChoices.length; i++) {
            if (boardChoices[i][0] == height) {
                indexBoard = i;
                break;
            }
        }
        boardSpinner.setSelection(indexBoard);

        int mines = sharedPreferences.getInt("mines", 6);
        int indexMine = 0;
        for (int i = 0; i<mineChoices.length; i++) {
            if (mineChoices[i] == mines) {
                indexMine = i;
                break;
            }
        }
        mineSpinner.setSelection(indexMine);

    }
}