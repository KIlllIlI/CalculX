package com.example.calculmentalliamvassetnathanbonnard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScoreActivity extends AppCompatActivity {

    AppDatabase db;

    ArrayList<TextView> pseudos = new ArrayList<>();
    TextView pseudo1;
    TextView pseudo2;
    TextView pseudo3;
    TextView pseudo4;
    TextView pseudo5;
    TextView pseudo6;
    TextView pseudo7;
    TextView pseudo8;
    TextView pseudo9;
    TextView pseudo10;

    ArrayList<TextView> scores = new ArrayList<>();
    TextView score1;
    TextView score2;
    TextView score3;
    TextView score4;
    TextView score5;
    TextView score6;
    TextView score7;
    TextView score8;
    TextView score9;
    TextView score10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_score);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pseudo1 = findViewById(R.id.text_pseudo1);
        pseudo2 = findViewById(R.id.text_pseudo2);
        pseudo3 = findViewById(R.id.text_pseudo3);
        pseudo4 = findViewById(R.id.text_pseudo4);
        pseudo5 = findViewById(R.id.text_pseudo5);
        pseudo6 = findViewById(R.id.text_pseudo6);
        pseudo7 = findViewById(R.id.text_pseudo7);
        pseudo8 = findViewById(R.id.text_pseudo8);
        pseudo9 = findViewById(R.id.text_pseudo9);
        pseudo10 = findViewById(R.id.text_pseudo10);

        pseudos.add(pseudo1);
        pseudos.add(pseudo2);
        pseudos.add(pseudo3);
        pseudos.add(pseudo4);
        pseudos.add(pseudo5);
        pseudos.add(pseudo6);
        pseudos.add(pseudo7);
        pseudos.add(pseudo8);
        pseudos.add(pseudo9);
        pseudos.add(pseudo10);

        score1 = findViewById(R.id.text_score1);
        score2 = findViewById(R.id.text_score2);
        score3 = findViewById(R.id.text_score3);
        score4 = findViewById(R.id.text_score4);
        score5 = findViewById(R.id.text_score5);
        score6 = findViewById(R.id.text_score6);
        score7 = findViewById(R.id.text_score7);
        score8 = findViewById(R.id.text_score8);
        score9 = findViewById(R.id.text_score9);
        score10 = findViewById(R.id.text_score10);

        scores.add(score1);
        scores.add(score2);
        scores.add(score3);
        scores.add(score4);
        scores.add(score5);
        scores.add(score6);
        scores.add(score7);
        scores.add(score8);
        scores.add(score9);
        scores.add(score10);

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

         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "scores-db").addCallback(my_callback).build();

         addScoreInBackground();
    }

    public void addScoreInBackground(){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                List<Score> scoreslist = db.scoreDAO().getClassement();

                for(int i = 0; i < scoreslist.size() && i < 10; i++) {
                    pseudos.get(i).setText(scoreslist.get(i).pseudo);
                    scores.get(i).setText(Integer.toString(scoreslist.get(i).score));
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ScoreActivity.this, "Added to ma maman", Toast.LENGTH_LONG);
                    }
                });
            }
        });
    }
}