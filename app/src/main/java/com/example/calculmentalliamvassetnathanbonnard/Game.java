package com.example.calculmentalliamvassetnathanbonnard;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity {
    protected TextView calcul;
    protected MenuItem menu_timer;
    protected MenuItem menu_score;

    protected Integer score = 0;

    protected Timer timer_temps = new Timer();
    protected TimerTask tickTemps = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(()-> {
                addTemps(-1);
            });
        }
    };
    protected Integer temps=90;
    protected ArrayList<Operations> operations_autorisees = new ArrayList<Operations>();
    protected int bound = 10;

    protected void reponse(Game difficulty, String message) {
        calcul.setText(message);

        (new Handler()).postDelayed(() -> difficulty.UnCalcul(), 250);
    }
    protected void addScore(int ajout) {
        score += ajout;
        updateScore();
    }

    protected void updateScore() {
        menu_score.setTitle(getString(R.string.score) + " : " + score.toString());
    }

    protected void addTemps(int ajout) {
        temps += ajout;
        updateTemps();
    }

    protected void updateTemps() {
        menu_timer.setTitle(getString(R.string.temps) + " : " + temps.toString());

        if (temps <= 0) {
            menu_timer.setTitle(getString(R.string.plus_de_temps));
            timer_temps.cancel();
            DialogScore dialogue = new DialogScore();
            dialogue.setScore(score);
            dialogue.show(getSupportFragmentManager(), "GAME_DIALOG");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_jeu, menu);

        menu_timer = menu.findItem(R.id.menu_timer);
        menu_score = menu.findItem(R.id.menu_score);

        updateTemps();

        return super.onCreateOptionsMenu(menu);
    }

    protected int UnCalcul(){
        Random rand = new Random();
        Integer resultat = -1;
        Integer a = -1;
        Integer b = -1;
        Log.d("SIZE", Integer.toString(operations_autorisees.size()));
        Operations operation = operations_autorisees.get(rand.nextInt(operations_autorisees.size()));

        switch (operation) {
            case ADD:
                a = rand.nextInt(bound);
                b = rand.nextInt(bound);
                resultat = a + b;
                break;

            case DIVISER:
                b = rand.nextInt(bound)+1;
                a = b * rand.nextInt(15);
                resultat = a / b;
                break;

            case MOINS:
                b = rand.nextInt(bound);
                a = rand.nextInt(bound)+b;
                resultat = a - b;
                break;

            case MULTIPLIER:
                a = rand.nextInt(bound);
                b = rand.nextInt(bound);
                resultat = a * b;
                break;
        }

        calcul.setText(String.format("%s " + operation.getSymbole() + " %s", a.toString(), b.toString()));

        return resultat;
    }
}
