package com.cmpt276a3.activites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.cmpt276a3.R;
import com.cmpt276a3.model.Board;
import com.cmpt276a3.model.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameScreen extends AppCompatActivity {
    Game game = new Game();
    int height = game.getBoard().getHeight();
    int width = game.getBoard().getWidth();
    Button buttons[][] = new Button[height][width];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        populateBoard(game);
    }

    // Refer to Brian Fraser video: Dynamic Buttons with Images: Android Programming
    private void populateBoard(Game game) {
        TableLayout table = findViewById(R.id.game_tableBoard);
        Board board = game.getBoard();

        for (int y = 0; y < height; y++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for (int x = 0; x < width; x++) {
                final int FINAL_X = x;
                final int FINAL_Y = y;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                button.setText("" + x + "," + y);

                // Make text not clip on small buttons
                button.setPadding(0, 0,0,0);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cellButtonClicked(FINAL_X, FINAL_Y);
                    }
                });
                tableRow.addView(button);
                buttons[y][x] = button;

            }
        }
    }

    private void cellButtonClicked(int x, int y) {
        Button button = buttons[y][x];

        lockButtonSizes();

        int newWidth = button.getWidth();
        int newHeight = button.getHeight();
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pikachu);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
        Resources resource = getResources();
        button.setBackground(new BitmapDrawable(resource, scaledBitmap));
    }

    private void lockButtonSizes() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Button button = buttons[y][x];

                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);

            }
        }
    }
}