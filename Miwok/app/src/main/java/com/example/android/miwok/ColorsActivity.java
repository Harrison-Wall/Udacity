package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create the list of colors
        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Red", "weṭeṭṭi"));
        words.add(new Word("Green", "chokokki"));
        words.add(new Word("Brown", "ṭakaakki"));
        words.add(new Word("Gray", "ṭopoppi"));
        words.add(new Word("Black", "kululli"));
        words.add(new Word("White", "kelelli"));
        words.add(new Word("Dusty Yellow", "ṭopiisә"));
        words.add(new Word("Mustard Yellow", "chiwiiṭә"));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Get the colors list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);
    }
}
