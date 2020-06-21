package com.cmpt276a3.activites;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.cmpt276a3.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 *
 */
public class Welcome extends AppCompatDialogFragment {

    @NonNull
    @Override
    // Refer to Brian Fraser video: AlertDialog via Fragment: Android Programming
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create the view to show
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.welcome_message, null);

        //

        return super.onCreateDialog(savedInstanceState);
    }
}
