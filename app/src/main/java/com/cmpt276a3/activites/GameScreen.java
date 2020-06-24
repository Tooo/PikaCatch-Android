package com.cmpt276a3.activites;

import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * GameScreen class manages the displayed elements of the game
 * It holds the game, button, and extra game stats
 */
public class GameScreen extends AppCompatActivity {
    private Game game;
    private Button[][] buttons;
    private int timesPlayed;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        createBoard();
        updateGameStats();
        updateInitialStats();
        populateBoard();
    }

    private void createBoard() {
        SharedPreferences sharedPreferences = getSharedPreferences("board settings", MODE_PRIVATE);
        int height = sharedPreferences.getInt("height", 4);
        int width = sharedPreferences.getInt("width", 6);
        int mines = sharedPreferences.getInt("mines", 6);

        game = new Game(height, width, mines);
        buttons = new Button[height][width];
    }

    private void updateGameStats() {
        TextView textMine = findViewById(R.id.game_txtMines);
        textMine.setText("Found " + game.getFoundMines() + " of " + game.getTotalMines() + " Pikachu");

        TextView textScan = findViewById(R.id.game_txtScans);
        textScan.setText("# of Scans used: " + game.getScansUsed());
    }

    private void updateInitialStats() {
        Board board = game.getBoard();
        int height = board.getHeight();
        int width = board.getWidth();

        SharedPreferences sharedPreferences = getSharedPreferences("game stats", MODE_PRIVATE);

        timesPlayed = sharedPreferences.getInt("played", 0);
        TextView textPlayed = findViewById(R.id.game_txtPlayed);
        textPlayed.setText("Times Played: " + timesPlayed);

        String setting = height + "x" + width + ", " + game.getTotalMines();
        highScore = sharedPreferences.getInt(setting, -1);
        TextView textHigh = findViewById(R.id.game_txtHighScore);

        if (highScore == -1) {
            textHigh.setText("HighScore for " + setting + " Pikachu: N/A");
        } else {
            textHigh.setText("HighScore for " + setting + " Pikachu:" + highScore);
        }

    }

    // Refer to Brian Fraser video: Dynamic Buttons with Images: Android Programming
    private void populateBoard() {
        TableLayout table = findViewById(R.id.game_tableBoard);
        Board board = game.getBoard();
        int height = board.getHeight();
        int width = board.getWidth();

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

                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pokeball);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 100, 100, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));

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
        lockButtonSizes();
    }

    private void cellButtonClicked(int x, int y) {
        Button button = buttons[y][x];
        Board board = game.getBoard();
        Cell[][] boardArray = board.getBoardArray();
        Cell cell = boardArray[y][x];

        game.cellClicked(x, y);
        if (cell.hasMine() && !cell.hasScanned()) {

            updateCellNumbers(x,y);
            lockButtonSizes();

            int newWidth = button.getWidth();
            int newHeight = button.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pikachu);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            button.setBackground(new BitmapDrawable(resource, scaledBitmap));

        } else if (cell.hasMine() && cell.hasScanned()) {
            setScanNumberText(x, y);
        } else {
            setScanNumberText(x, y);
            button.setBackground(null);
        }

        updateGameStats();

        // Check when game is over
        if (game.getTotalMines() == game.getFoundMines()) {
            endGame();
        }

    }

    private void lockButtonSizes() {
        Board board = game.getBoard();
        int height = board.getHeight();
        int width = board.getWidth();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Button button = buttons[y][x];

                int buttonWidth = button.getWidth();
                button.setMinWidth(buttonWidth);
                button.setMaxWidth(buttonWidth);

                int buttonHeight = button.getHeight();
                button.setMinHeight(buttonHeight);
                button.setMaxHeight(buttonHeight);
            }
        }
    }

    private void updateCellNumbers(int xScan, int yScan) {
        Board board = game.getBoard();
        int height = board.getHeight();
        int width = board.getWidth();

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

        if (cell.hasScanned()) {
            button.setText("" + cell.getScanNumber());
        }
    }

    private void endGame() {
        Board board = game.getBoard();
        int height = board.getHeight();
        int width = board.getWidth();

        SharedPreferences sharedPreferences = getSharedPreferences("game stats", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        timesPlayed++;
        editor.putInt("played", timesPlayed);

        if (highScore == -1 || highScore > game.getScansUsed()) {
            String setting = height + "x" + width + ", " + game.getTotalMines();
            editor.putInt(setting, game.getScansUsed());
        }
        editor.apply();

        FragmentManager manager = getSupportFragmentManager();
        Congrats dialog = new Congrats();
        dialog.show(manager, "Congrats");

    }
}