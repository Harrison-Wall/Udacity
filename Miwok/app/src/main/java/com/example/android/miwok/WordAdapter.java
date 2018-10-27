package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter <Word>
{
    /**
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param words A List of AndroidFlavor objects to display in a list
     */
    public WordAdapter(Activity context, ArrayList<Word> words)
    {
        // initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // the adapter is not going to use this second argument
        super(context, 0, words);
    }

    @Override
    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate (R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the Miwok TextView
        TextView MiwokTextView = (TextView) listItemView.findViewById(R.id.miwok_list_text);
        MiwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the default TextView
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_list_text);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView
        //ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        //iconView.setImageResource(currentWord.getImageResourceId());

        return listItemView;
    }

}
