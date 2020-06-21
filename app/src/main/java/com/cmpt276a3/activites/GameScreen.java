package com.cmpt276a3.activites;

import android.os.Bundle;

import com.cmpt276a3.R;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        populateBoard();
    }

    // Refer to Brian Fraser video: Dynamic Buttons with Images: Android Programming
    private void populateBoard() {
        TableLayout table = findViewById(R.id.game_tableBoard);

        for (int row = 0; row < 2; row++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int col = 0; col < 3; col++) {
                Button button = new Button(this);
                 button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));
                tableRow.addView(button);
            }
        }
    }
}