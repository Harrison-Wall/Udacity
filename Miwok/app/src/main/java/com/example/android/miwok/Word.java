package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word.
 * It contains a default translation and a Miwol translation of the word.
 */
public class Word
{
    private String mDefaultTrans, mMiwokTrans;
    private int mImageResID, mRawResID;

    private final int NO_IMAGE_PROVIDED = -1;
    private final int NO_RAW_PROVIDED = -1;

    public Word()
    {
        mDefaultTrans = "English";
        mMiwokTrans = "Miwok";
        mImageResID = NO_IMAGE_PROVIDED;
        mRawResID = NO_RAW_PROVIDED;
    }

    public Word(String def, String miw)
    {
        mDefaultTrans = def;
        mMiwokTrans = miw;
        mImageResID = NO_IMAGE_PROVIDED;
        mRawResID = NO_RAW_PROVIDED;
    }

    public Word(String def, String miw, int iID)
    {
        mDefaultTrans = def;
        mMiwokTrans = miw;
        mImageResID = iID;
        mRawResID = NO_RAW_PROVIDED;
    }

    public Word(String def, String miw, int iID, int rID)
    {
        mDefaultTrans = def;
        mMiwokTrans = miw;
        mImageResID = iID;
        mRawResID = rID;
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

    /**
     * @return the id of the image
     */
    public int getImageID() { return mImageResID; }

    /**
     * @return if the word has an associated image
     */
    boolean hasImage()
    {
        return mImageResID != NO_IMAGE_PROVIDED;
    }

    /**
     *
     * @return the resource id for the audio file
     */
    public int getmRawResID()
    {
        return mRawResID;
    }
}
