package com.cmpt276a3.activites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.cmpt276a3.R;
import com.cmpt276a3.model.Board;
import com.cmpt276a3.model.Cell;
import com.cmpt276a3.model.Game;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        updateText(game);
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
        Board board = game.getBoard();
        Cell[][] boardArray = board.getBoardArray();
        Cell cell = boardArray[y][x];

        if (cell.hasMine() && !cell.isClicked()) {
            game.scanMines(x, y);
            lockButtonSizes();
            updateCellNumbers(x, y);

            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pikachu);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));
        } else {
            game.scanMines(x, y);
            setScanNumberText(x, y);
        }

       updateText(game);


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

    private void updateCellNumbers(int xScan, int yScan) {
        Board board = game.getBoard();
        // Scan row
        for (int x = 0; x < width; x++) {
            setScanNumberText(x, yScan);
        }

        // Scan column
        for (int y = 0; y < height; y++) {
            setScanNumberText(xScan, y);
        }
    }

    private void setScanNumberText(int x, int y) {
        Board board = game.getBoard();
        Cell cell = board.getBoardArray()[y][x];
        Button button = buttons[y][x];
        if (cell.isClicked()) {
            button.setText("" + cell.getScanNumber());
        }
    }

    private void updateText(Game game) {
        TextView textMine = findViewById(R.id.game_txtMines);
        textMine.setText("Found " + game.getFoundMines() + " of " + game.getTotalMines() + " Mines");

        TextView textScan = findViewById(R.id.game_txtScans);
        textScan.setText("# of Scans used: " + game.getScansUsed());

        TextView textPlayed = findViewById(R.id.game_txtPlayed);
        textPlayed.setText("Times Played:");
    }
}