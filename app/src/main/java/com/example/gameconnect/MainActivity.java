package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activeplayer = 0;
    int [][] winningpositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{0,3,6},{2,4,6},{1,4,7},{2,5,8}};
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    boolean gameactive = true;
    public void dropin (View view){
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && gameactive)  {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(360).setDuration(300);
            for (int[] winningposition : winningpositions) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    String winner = "";
                    gameactive = false;
                    if (activeplayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    Button button = (Button) findViewById(R.id.button);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won!");
                    button.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                  }
            }
        }
    }
    public void  playAgain (View view){
        Button button = (Button) findViewById(R.id.button);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        button.setVisibility(View.INVISIBLE);
        winnerTextView.setText("Hi!");
        winnerTextView.setVisibility(View.VISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0; i<gridLayout.getChildCount(); i++ ){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activeplayer = 0;
        for(int j=0;j<gamestate.length;j++) {
            gamestate[j] = 2;
        }
        gameactive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}