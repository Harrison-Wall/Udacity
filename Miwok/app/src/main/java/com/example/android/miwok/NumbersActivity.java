package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity
{
    private MediaPlayer musicBox;
    private MediaPlayer.OnCompletionListener  mListener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            // Free Resources if needed
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create the list of numbers
        final ArrayList<Word> words = new ArrayList<Word>(); // Must be final to use in listView

        words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        // Get the numbers list layout
        final ListView listView = (ListView) findViewById(R.id.word_list);

        // Add the list to the layout
        listView.setAdapter(itemsAdapter);

        // set what to play when clicked
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // Free Resources if needed
                releaseMediaPlayer();

                // Create the media player
                musicBox = MediaPlayer.create(NumbersActivity.this, words.get(i).getmRawResID() ); //use the position (i) to play the file associated with the word.
                musicBox.start();

                musicBox.setOnCompletionListener(mListener);
            }
        });

    }

    // Free resources when activity is stopped.
    @Override
    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (musicBox != null) {
            // Regardless of the current state of the media player, release its resources
            musicBox.release();

            // Set the media player back to null.
            musicBox = null;
        }
    }
}
