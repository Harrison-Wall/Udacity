package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create the list of numbers
        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("One", "Lutti"));
        words.add(new Word("Two", "Otiiko"));
        words.add(new Word("Three", "tolookosu"));
        words.add(new Word("Four", "oyyisa"));
        words.add(new Word("Five", "massokka"));
        words.add(new Word("Six", "temmokka"));
        words.add(new Word("Seven", "kenekaku"));
        words.add(new Word("Eight", "kawinta"));
        words.add(new Word("Nine", "wo'e"));
        words.add(new Word("Ten", "na'aacha"));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Get the numbers list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);

    }
}
