package com.example.calculmentalliamvassetnathanbonnard;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button bouton_jouer;
    private Button bouton_score;
    private Button bouton_apropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bouton_jouer = findViewById(R.id.bouton_jouer);
        bouton_jouer.setOnClickListener( view -> {

                    new DialogDifficulty().show(getSupportFragmentManager(), "GAME_DIALOG");
                });

        bouton_apropos = findViewById(R.id.bouton_a_propos);
        bouton_apropos.setOnClickListener( view -> {
            startActivity(new Intent(MainActivity.this, AproposActivity.class));
        });

        bouton_score = findViewById(R.id.bouton_score);
        bouton_score.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ScoreActivity.class));
        });
    }
}