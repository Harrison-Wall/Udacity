package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create the list of family members
        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Father", "әpә"));
        words.add(new Word("Mother", "әṭa"));
        words.add(new Word("Son", "angsi"));
        words.add(new Word("Daughter", "tune"));
        words.add(new Word("Older Brother", "taachi"));
        words.add(new Word("Younger Brother", "chalitti"));
        words.add(new Word("Older Sister", "teṭe"));
        words.add(new Word("Younger Sister", "kolliti"));
        words.add(new Word("Grandmother", "ama"));
        words.add(new Word("Grandfather", "paapa"));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Get the family list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);

    }
}
