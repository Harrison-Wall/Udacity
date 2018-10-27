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

        words.add(new Word("Father", "әpә", R.drawable.family_father));
        words.add(new Word("Mother", "әṭa" ,R.drawable.family_mother));
        words.add(new Word("Son", "angsi", R.drawable.family_son));
        words.add(new Word("Daughter", "tune", R.drawable.family_daughter));
        words.add(new Word("Older Brother", "taachi", R.drawable.family_older_brother));
        words.add(new Word("Younger Brother", "chalitti", R.drawable.family_younger_brother));
        words.add(new Word("Older Sister", "teṭe", R.drawable.family_older_sister));
        words.add(new Word("Younger Sister", "kolliti", R.drawable.family_younger_sister));
        words.add(new Word("Grandmother", "ama", R.drawable.family_grandmother));
        words.add(new Word("Grandfather", "paapa", R.drawable.family_grandfather));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words);

        // Get the family list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);

    }
}
