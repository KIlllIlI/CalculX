package com.example.calculmentalliamvassetnathanbonnard;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;

public class GameNormalActivity extends Game {
    private int indice_resultat;
    private Button bouton_option_1;
    private Button bouton_option_2;
    private Button bouton_option_3;
    private Button bouton_option_4;
    private ArrayList<Button> boutons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_normal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle(getString(R.string.normal));

        bound = 25;
        operations_autorisees.add(Operations.ADD);
        operations_autorisees.add(Operations.MOINS);



        calcul = findViewById(R.id.text_calcul);
        bouton_option_1 = findViewById(R.id.bouton_option_1);
        bouton_option_2 = findViewById(R.id.bouton_option_2);
        bouton_option_3 = findViewById(R.id.bouton_option_3);
        bouton_option_4 = findViewById(R.id.bouton_option_4);

        boutons = new ArrayList<Button>();
        boutons.add(bouton_option_1);
        boutons.add(bouton_option_2);
        boutons.add(bouton_option_3);
        boutons.add(bouton_option_4);

        timer_temps.schedule(tickTemps, 1000, 1000);

        UnCalcul();
    }

    @Override
    protected void reponse(Game difficulty, String message) {
        super.reponse(difficulty, message);
        for (Button bouton : boutons)
        {
            bouton.setEnabled(false);
            bouton.setText("");
        }
    }

    @Override
    protected int UnCalcul() {
        int resultat = super.UnCalcul();
        Random rand = new Random();
        Button bouton_bonresultat = boutons.get(indice_resultat);


        for (Button bouton : boutons) {
            bouton.setEnabled(true);
            bouton.setOnClickListener( view -> {
                addTemps(-5);
                reponse(this, "👎");
            });
        }



        indice_resultat = rand.nextInt(4);

        ArrayList<Integer> ListBadResults = new ArrayList<Integer>();

        int ecart = 4;

        for (int i=resultat-ecart; i<resultat+ecart; i++)
        {
            if (i != resultat)
                ListBadResults.add(i);
        }

        int lenght = ecart*2;

        for (Button button : boutons)
        {
            int indicemauvaisresultat = rand.nextInt(lenght-1);
            Integer unmauvaisresultat = ListBadResults.get(indicemauvaisresultat);
            ListBadResults.remove(indicemauvaisresultat);
            button.setText(unmauvaisresultat.toString());
            lenght--;
        }

        bouton_bonresultat.setText(Integer.toString(resultat));

        bouton_bonresultat.setOnClickListener( view -> {
            addScore(2);
            addTemps(5);
            reponse(this, "👍");
        });

        return resultat;
    }
}