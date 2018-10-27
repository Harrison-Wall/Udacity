package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create the list of family members
        ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?", "minto wuksus"));
        words.add(new Word("What is your name?", "tinnә oyaase'nә"));
        words.add(new Word("My name is...", "oyaaset..."));
        words.add(new Word("How are you feeling?", "michәksәs?"));
        words.add(new Word("I’m feeling good.", "kuchi achit"));
        words.add(new Word("Are you coming?", "әәnәs'aa?"));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm"));
        words.add(new Word("I’m coming.", "әәnәm"));
        words.add(new Word("Let’s go.", "yoowutis"));
        words.add(new Word("Come here.", "әnni'nem"));


        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

        // Get the family list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);
    }
}
