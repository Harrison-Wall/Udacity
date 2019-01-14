package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>
{
    private String urlToLoad;

    public EarthquakeLoader(Context context, String url)
    {
        super(context);
        urlToLoad = url;
    }

    @Override
    protected void onStartLoading()
    {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground()
    {
        if( urlToLoad == null )
        {
            return null;
        }

        return QueryUtils.FetchData(urlToLoad);
    }

}
