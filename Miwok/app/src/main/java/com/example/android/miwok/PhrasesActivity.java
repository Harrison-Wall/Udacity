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

public class PhrasesActivity extends AppCompatActivity {

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

        // Create the list of family members
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?", "minto wuksus", -1, R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", -1, R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", -1, R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", -1, R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", -1, R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", -1, R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", -1, R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", -1, R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", -1, R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", -1, R.raw.phrase_come_here));


        // Make a list item view from the words ArrayList
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);

        // Get the family list layout
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
                    musicBox = MediaPlayer.create(PhrasesActivity.this, words.get(i).getmRawResID() ); //use the position (i) to play the file associated with the word.
                    musicBox.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    musicBox.setOnCompletionListener(mListener);
                }
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

            // Abandon all focus, release listener.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}