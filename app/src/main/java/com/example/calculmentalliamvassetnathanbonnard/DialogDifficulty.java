package com.example.calculmentalliamvassetnathanbonnard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

public class DialogDifficulty extends DialogFragment {
    private int selected = 1;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] choices = {getString(R.string.easy), getString(R.string.normal), getString(R.string.hard)};
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(getString(R.string.choix_difficulte))
                .setPositiveButton(getString(R.string.jouer), (dialog, which) -> {
                    switch (selected) {
                        case 0: startActivity(new Intent(getContext(), GameEasyActivity.class)); break;
                        case 1: startActivity(new Intent(getContext(), GameNormalActivity.class)); break;
                        case 2: startActivity(new Intent(getContext(), GameHardActivity.class)); break;
                        default:
                            Log.d("TEST", Integer.toString(selected));

                    }
                })
                .setNegativeButton(getString(R.string.annuler), (dialog, which) -> {
                })
                .setSingleChoiceItems(choices, selected, (dialog, which) -> {
                    selected = which;
        });
        return builder.create();
    }
}