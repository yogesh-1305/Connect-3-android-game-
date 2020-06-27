package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 is O, 1 is X
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o1);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x1);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(200);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner;
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = "Zero";
                    } else {
                        winner = "Cross";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.textView);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerText.setText(winner + " has won!");
                }
            }
        }else {
            playAgain(view);
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.textView);
        winnerText.setText("Lets see Who Wins?");
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}