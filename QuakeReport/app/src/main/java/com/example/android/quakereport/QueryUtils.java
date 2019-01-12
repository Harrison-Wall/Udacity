package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class QueryUtils
{
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private static final String JSON_RESPONSE = "";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils()
    {

    }

    /**
     * Set up a URL connection to the USGS_URL
     */

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes()
    {
        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        double tempMag;
        String tempLoc, tempURL;
        long tempTime;

        try
        {
            // Parse the response given by the JSON_RESPONSE string and build up a list of Earthquake objects with the corresponding data.
            JSONObject rootObj = new JSONObject( JSON_RESPONSE );
            JSONArray featuresArr = rootObj.optJSONArray("features");

            int arrLength = featuresArr.length();
            for( int i = 0; i < arrLength; i++ )
            {
                JSONObject quakeObj = featuresArr.getJSONObject(i).getJSONObject("properties");

                tempMag  = Double.parseDouble( quakeObj.optString("mag") );
                tempLoc  = quakeObj.optString("place");
                tempTime = Long.parseLong( quakeObj.optString("time") );
                tempURL = quakeObj.optString("url");

                earthquakes.add( new Earthquake( tempLoc, tempMag, tempTime, tempURL ) );
            }

        }
        catch (JSONException e)
        {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return earthquakes;
    }
}
