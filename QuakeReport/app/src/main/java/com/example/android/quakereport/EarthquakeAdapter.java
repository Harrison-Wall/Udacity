package com.example.android.quakereport;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake>
{

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> quakes)
    {
        super(context, 0, quakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        // Get the object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        TextView locTextView = (TextView) listItemView.findViewById(R.id.eq_loc);
        TextView distTextView = (TextView) listItemView.findViewById(R.id.eq_dist);

        if( currentEarthquake.getLocation().contains("of") )
        {
            String locParts[] = currentEarthquake.getLocation().split("of ");
            distTextView.setText(locParts[0]+"OF");
            locTextView.setText(locParts[1]);
        }
        else
        {
            distTextView.setText("NEAR THE");
            locTextView.setText(currentEarthquake.getLocation());
        }

        TextView magTextView = (TextView) listItemView.findViewById(R.id.eq_mag);
        magTextView.setText("" + currentEarthquake.getMag() );

        // Set the proper background color on the magnitude circle.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMag());
        magnitudeCircle.setColor(magnitudeColor);

        TextView dateView = (TextView) listItemView.findViewById(R.id.eq_date);
        dateView.setText(currentEarthquake.getDate());


        TextView timeView = (TextView) listItemView.findViewById(R.id.eq_time);
        timeView.setText(currentEarthquake.getTime());

        return listItemView;
    }

    public int getMagnitudeColor( double pMagnitude )
    {
        int retVal;

        switch( (int) Math.floor(pMagnitude) )
        {
            case 0:
            case 1:
                retVal = R.color.magnitude1;
                break;
            case 2:
                retVal = R.color.magnitude2;
                break;
            case 3:
                retVal = R.color.magnitude3;
                break;
            case 4:
                retVal = R.color.magnitude4;
                break;
            case 5:
                retVal = R.color.magnitude5;
                break;
            case 6:
                retVal = R.color.magnitude6;
                break;
            case 7:
                retVal = R.color.magnitude7;
                break;
            case 8:
                retVal = R.color.magnitude8;
                break;
            case 9:
                retVal = R.color.magnitude9;
                break;
            default:
                retVal = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), retVal);
    }
}
