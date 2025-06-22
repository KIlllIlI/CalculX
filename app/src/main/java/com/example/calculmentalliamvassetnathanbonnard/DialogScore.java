package com.example.calculmentalliamvassetnathanbonnard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DialogScore extends DialogFragment {
    private AppDatabase db;
    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        RoomDatabase.Callback my_callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "scores-db").addCallback(my_callback).build();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText input = new EditText(getContext());
        input.setHint(R.string.pseudo);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder
                .setTitle(getString(R.string.mettre_score))
                .setView(input)
                .setPositiveButton(getString(R.string.accepter), (dialog, which) -> {
                    addScoreInBackground(input.getText().toString());
                    startActivity(new Intent(getContext(), MainActivity.class));
                })
                .setNegativeButton(getString(R.string.refuser), (dialog, which) -> {
                    startActivity(new Intent(getContext(), MainActivity.class));
                });
        return builder.create();
    }

    public void addScoreInBackground(String pseudo){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                if(score > db.scoreDAO().getWorstGuy().get(0).score) {
                    List<Score> scoreslist = db.scoreDAO().getClassement();
                    if (scoreslist.size() >= 10) {
                        db.scoreDAO().delete(db.scoreDAO().getWorstGuy().get(0));
                    }
                    db.scoreDAO().insert(new Score(pseudo, score));

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }
            }
        });
    }
}