package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word.
 * It contains a default translation and a Miwol translation of the word.
 */
public class Word
{
    private String mDefaultTrans, mMiwokTrans;

    public Word()
    {
        mDefaultTrans = "English";
        mMiwokTrans = "Miwok";
    }

    public Word(String def, String miw)
    {
        mDefaultTrans = def;
        mMiwokTrans = miw;
    }

    /**
     *  @return the default translation of the word
     */
    public String getDefaultTranslation()
    {
        return  mDefaultTrans;
    }

    /**
     *  @return the Miwok translation of the word
     */
    public String getMiwokTranslation ()
    {
        return mMiwokTrans;
    }
}
