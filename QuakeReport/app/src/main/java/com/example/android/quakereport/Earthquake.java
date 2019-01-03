package com.example.android.quakereport;

import java.util.Date;

public class Earthquake
{
    private String mLocation, mURL;
    private double mMag;
    private Date mDate;

    public Earthquake()
    {
        mLocation = "Not Set";
        mMag = 0.0;
        mDate = null;
    }

    public Earthquake(String pLocation, double pMag, long pMilli, String pURL)
    {
        mLocation = pLocation;
        mMag = pMag;
        mDate = new Date(pMilli);
        mURL = pURL;
    }

    // Getters
    public String getLocation()
    {
        return mLocation;
    }

    public double getMag()
    {
        return mMag;
    }

    public String getURL() { return mURL; }

    public String getDate()
    {
        if( mDate != null )
            return String.format("%1$tb %1$td, %1$tY", mDate);
        else
            return "Date Not Set";
    }

    public String getTime()
    {
        if( mDate != null )
            return String.format("%1$tl:%1$tM %1$tp", mDate);
        else
            return "Date Not Set";
    }

    // Setters
    public void setLocation(String pLocation)
    {
        mLocation = pLocation;
        return;
    }

    public void setMag(float pMag)
    {
        mMag = pMag;
        return;
    }

    public void setDate(long pMilli)
    {
        if( mDate != null )
            mDate.setTime(pMilli);
        else
            mDate = new Date(pMilli);

        return;
    }
}
