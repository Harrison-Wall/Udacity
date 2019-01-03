package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        Earthquake currentAndroidFlavor = getItem(position);

        TextView locTextView = (TextView) listItemView.findViewById(R.id.eq_loc);
        TextView distTextView = (TextView) listItemView.findViewById(R.id.eq_dist);

        if( currentAndroidFlavor.getLocation().contains("of") )
        {
            String locParts[] = currentAndroidFlavor.getLocation().split("of ");
            distTextView.setText(locParts[0]+"OF");
            locTextView.setText(locParts[1]);
        }
        else
        {
            distTextView.setText("NEAR THE");
            locTextView.setText(currentAndroidFlavor.getLocation());
        }

        TextView magTextView = (TextView) listItemView.findViewById(R.id.eq_mag);
        magTextView.setText("" + currentAndroidFlavor.getMag() );


        TextView dateView = (TextView) listItemView.findViewById(R.id.eq_date);
        dateView.setText(currentAndroidFlavor.getDate());


        TextView timeView = (TextView) listItemView.findViewById(R.id.eq_time);
        timeView.setText(currentAndroidFlavor.getTime());

        return listItemView;
    }
}
