package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public int mainPlayer=0;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    int winningpositons[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activeGame=1;
    int count=0;
    public void  dropIn(View view) {
        ImageView imageView = (ImageView) view;
        int getRowCount = Integer.parseInt(imageView.getTag().toString());
        TextView winnertag=(TextView) findViewById(R.id.winnertag);
        if(activeGame==1) {
            if (gamestate[getRowCount] == 2) {
                imageView.setTranslationY(-1500);
                gamestate[getRowCount] = mainPlayer;
                if (mainPlayer == 0) {
                    imageView.setImageResource(R.drawable.yellow);
                    mainPlayer = 1;
                } else if (mainPlayer == 1) {
                    imageView.setImageResource(R.drawable.red);
                    mainPlayer = 0;
                }
                imageView.animate().translationYBy(1500).setDuration(500).rotation(3600);
                count++;
                for (int[] winningposition : winningpositons) {

                    if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                        String winner = (mainPlayer == 0) ? "Red" : "Yellow";
                        winnertag.setVisibility(View.VISIBLE);
                        winnertag.setText(winner + " has won.");
                        Button but = (Button) findViewById(R.id.playagain);
                        but.setVisibility(View.VISIBLE);
                        activeGame = 0;

                    }
                }
                if(count==9)
                {
                    winnertag.setVisibility(View.VISIBLE);
                    winnertag.setText( "Game Draw!.");
                    Button but = (Button) findViewById(R.id.playagain);
                    but.setVisibility(View.VISIBLE);
                    activeGame = 0;
                }


            }
        }


    }

    public void onclick(View view)
    {
//        mainPlayer=0;
//
//
        Button but = (Button) findViewById(R.id.playagain);
        TextView text = (TextView) findViewById(R.id.winnertag);
        but.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        activeGame=1;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
            GridLayout grid = (GridLayout) findViewById(R.id.Board);

            ImageView block = (ImageView) grid.getChildAt(i);
            block.setImageDrawable(null);

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}