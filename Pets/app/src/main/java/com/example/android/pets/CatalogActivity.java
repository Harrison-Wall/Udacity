/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.Data.PetContract;
import com.example.android.pets.Data.PetDbHelper;
import com.example.android.pets.Data.PetContract.PetEntry;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity
{

    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new PetDbHelper(this);
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo()
    {
        String projection[] = {PetEntry._ID, PetEntry.COLUMN_PET_NAME, PetEntry.COLUMN_PET_BREED, PetEntry.COLUMN_PET_GENDER, PetEntry.COLUMN_PET_WEIGHT};

        // get a Cursor that contains all rows from the pets table.

        Cursor cursor = getContentResolver().query(PetEntry.CONTENT_URI, projection, null, null, null);

        // Add pets to the TextView
        TextView displayView = (TextView) findViewById(R.id.text_view_pet);
        try
        {
            // Set up header for data
            displayView.setText("The Pets Table Contains: " + cursor.getCount() + " Pets");
            displayView.append("\n" + PetEntry._ID + " " + PetEntry.COLUMN_PET_NAME + " " + PetEntry.COLUMN_PET_BREED
                    + " " + PetEntry.COLUMN_PET_GENDER + " " + PetEntry.COLUMN_PET_WEIGHT);

            // Add all cursor info to the TextView
            while( cursor.moveToNext() )
            {
                displayView.append("\n" + cursor.getString( cursor.getColumnIndex(PetEntry._ID)) + " - "
                        + cursor.getString( cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME)) + " - "
                        + cursor.getString( cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED)) + " - "
                        + cursor.getString( cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER)) + " - "
                        + cursor.getString( cursor.getColumnIndex(PetEntry.COLUMN_PET_WEIGHT)) );
            }
        }
        finally // Clean up Cursor
        {
            cursor.close();
        }
    }

    // Hardcoded - for debugging only
    private void insertPet()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(PetEntry.COLUMN_PET_NAME, "Toby");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, PetEntry.GENDER_MALE);
        values.put(PetEntry.COLUMN_PET_WEIGHT, 7);

        Uri newUri = getContentResolver().insert(PetEntry.CONTENT_URI, values);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId())
        {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;

            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
