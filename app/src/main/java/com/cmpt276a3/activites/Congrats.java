package com.cmpt276a3.activites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.cmpt276a3.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * Congrats class manages the congrats dialog, shown the game is over
 * The dialog is represented with the congrats layout
 */
public class Congrats extends AppCompatDialogFragment {

    @NonNull
    @Override
    // Refer to Brian Fraser video: AlertDialog via Fragment: Android Programming
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.message_congrats, null);

        // Button
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getContext(), MainMenu.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        };

        // Build the alert
        return new AlertDialog.Builder(getActivity())
                .setTitle("Congratulations")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();
    }
}
