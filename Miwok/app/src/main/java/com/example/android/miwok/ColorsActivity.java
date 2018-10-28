package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity
{
    /**Handles playback of all files**/
    private MediaPlayer musicBox;

    /**Handles when to release media resources**/
    private MediaPlayer.OnCompletionListener  mListener =  new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            // Free Resources if needed
            releaseMediaPlayer();
        }
    };

    // Listener is called on when {@link AudioManager} focus changes.
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener()
    {
        public void onAudioFocusChange(int focusChange)
        {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                // Pause playback and reset player to the start of the file.
                musicBox.pause();
                musicBox.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                // Stop playback, because you lost the Audio Focus. Release unneeded resources.
                musicBox.stop();
                releaseMediaPlayer();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                // Resume playback.
                musicBox.start();
            }
        }
    };

    /**Handles audio focus when playing sound.**/
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // set up the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create the list of colors
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("Green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("Brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("Gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("Black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("White", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("Dusty Yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("Mustard Yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        // Get the colors list layout
        ListView listView = (ListView) findViewById(R.id.word_list);

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

                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // Check if the audio manager got the focus
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    musicBox = MediaPlayer.create(ColorsActivity.this, words.get(i).getmRawResID() ); //use the position (i) to play the file associated with the word.
                    musicBox.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    musicBox.setOnCompletionListener(mListener);
                }
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        // Free resources when activity is stopped.
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

            // Abandon all focus, release listener.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}