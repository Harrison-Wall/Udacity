package com.example.android.quakereport;

import java.util.Date;

public class Earthquake
{
    private String mLocation;
    private float mMag;
    private Date mDate;

    public Earthquake()
    {
        mLocation = "Not Set";
        mMag = 0.0f;
        mDate = null;
    }

    public Earthquake(String pLocation, float pMag, int pMilli)
    {
        mLocation = pLocation;
        mMag = pMag;
        mDate = new Date(pMilli);
    }

    // Getters
    public String getLocation()
    {
        return mLocation;
    }

    public float getMag()
    {
        return mMag;
    }

    public String getDate()
    {
        return String.format("%1$tB %1$td, %1$tY", mDate);
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

    public void setDate(int pMilli)
    {
        mDate.setTime(pMilli);
        return;
    }
}
