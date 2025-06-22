package com.example.calculmentalliamvassetnathanbonnard;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class GameHardActivity extends Game {

    private TextInputEditText input;
    private int bonne_reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_hard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle(getString(R.string.hard));

        bound = 30;
        operations_autorisees.add(Operations.ADD);
        operations_autorisees.add(Operations.MOINS);
        operations_autorisees.add(Operations.MULTIPLIER);
        operations_autorisees.add(Operations.DIVISER);

        calcul = findViewById(R.id.text_calcul);

        input = findViewById(R.id.input);
        input.requestFocus();
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (input.getText().toString().equals(Integer.toString(bonne_reponse))) {
                    addScore(3);
                    addTemps(10);
                    reponseIncognito("👍");
                } else {
                    addTemps(-5);
                    reponseIncognito("👎");
                }

                v.setText("");
                return true;
            }
        });

        timer_temps.schedule(tickTemps, 1000, 1000);

        UnCalcul();
    }

    protected void reponseIncognito(String message) {
        reponse(this, message);
    }

    @Override
    protected void reponse(Game difficulty, String message) {
        super.reponse(difficulty, message);
    }

    @Override
    protected int UnCalcul() {
        bonne_reponse = super.UnCalcul();
        return bonne_reponse;
    }
}