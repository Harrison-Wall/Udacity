package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increments Team A's score to 3
     */
    public void plus3TeamA(View view)
    {
       scoreTeamA += 3;
       displayForTeamA(scoreTeamA);
    }

    /**
     * Increments Team A's score to 2
     */
    public void plus2TeamA(View view)
    {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Increments Team A's score to 1
     */
    public void plus1TeamA(View view)
    {
        scoreTeamA ++;
        displayForTeamA(scoreTeamA);
    }

    /**
     * Displays the given score for Team B.
     */
    public void displayForTeamB(int score)
    {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Increments Team B's score to 3
     */
    public void plus3TeamB(View view)
    {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increments Team B's score to 2
     */
    public void plus2TeamB(View view)
    {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    /**
     * Increments Team B's score to 1
     */
    public void plus1TeamB(View view)
    {
        scoreTeamB ++;
        displayForTeamB(scoreTeamB);
    }
}
