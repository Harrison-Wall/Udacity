package com.example.android.quakereport;

import android.renderscript.ScriptGroup;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

public final class QueryUtils
{
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils()
    {

    }

    public static List<Earthquake> FetchData(String pUrl)
    {
        URL url = createURL(pUrl);
        String jsonResp = null;

        try
        {
            jsonResp = makeHTTPReq(url);
        }
        catch(IOException e)
        {
            Log.e(LOG_TAG, "Problem making HTTP request: ", e);
        }

        List<Earthquake> earthquakes = extractEarthquakes(jsonResp);

        return earthquakes;
    }

    /**
     * Creates a URL object from the USGS_URL
     */
    private static URL createURL(String pURLString)
    {
        URL url = null;

        try
        {
            url = new URL(pURLString);
        }
        catch(MalformedURLException e)
        {
            Log.e(LOG_TAG, "Problem Building URL", e);

        }

        return url;
    }

    /**
     * Sets up a URL Connection
     */
    private static String makeHTTPReq(URL url) throws IOException
    {
        String jResponse = "";


        if( url == null ) // If no URL return empty
        {
            return jResponse;
        }

        HttpURLConnection urlConnect = null;
        InputStream inStream = null;

        try
        {
            // Set up the connection
            urlConnect = (HttpURLConnection) url.openConnection();
            urlConnect.setReadTimeout(10000);
            urlConnect.setConnectTimeout(15000);
            urlConnect.setRequestMethod("GET");
            urlConnect.connect();

            if( urlConnect.getResponseCode() == 200 ) // If successful
            {
                // Read Input from URL
                inStream = urlConnect.getInputStream();
                jResponse = readFromStream(inStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error in connecting to URL: " + urlConnect.getResponseCode());
            }
        }
        catch (IOException e)
        {
            Log.e(LOG_TAG, "IO Exception", e);
        }
        finally
        {
            // Clean up connections and Input Stream
            if(urlConnect != null)
                urlConnect.disconnect();

            if(inStream != null)
            {
                // Any exception would be caught above. This is stupid.
                try
                {
                    inStream.close();
                }
                catch (IOException e)
                {
                    Log.e(LOG_TAG, "IO Exception", e);
                }
            }
        }

        return jResponse;
    }

    /**
     * Convert the {@link InputStream} into a JSON response
     */
    private static String readFromStream(InputStream inStream) throws IOException
    {
        StringBuilder output = new StringBuilder();

        if( inStream != null ) // Check that connection was made
        {
            InputStreamReader inputReader = new InputStreamReader(inStream, Charset.forName("UTF-8"));
            BufferedReader bReader = new BufferedReader(inputReader);
            String line = bReader.readLine();

            while(line != null) // While there is still info to read
            {
                output.append(line);
                line = bReader.readLine();
            }
        }

        return output.toString();
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Earthquake> extractEarthquakes(String eqJSON)
    {
        if( TextUtils.isEmpty(eqJSON) ) // If no JSON data
            return null;

        List<Earthquake> earthquakes = new ArrayList<>();

        try
        {
            // Parse the response given by the JSON_RESPONSE string and build up a list of Earthquake objects with the corresponding data.
            JSONObject rootObj = new JSONObject( eqJSON );
            JSONArray featuresArr = rootObj.optJSONArray("features");

            double tempMag;
            String tempLoc, tempURL;
            long tempTime;
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
