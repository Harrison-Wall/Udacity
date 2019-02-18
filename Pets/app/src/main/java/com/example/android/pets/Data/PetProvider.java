package com.example.android.pets.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class PetProvider extends ContentProvider
{
    // Gives access to pets database
    private PetDbHelper mHelper;

    // static intgers for URI matches
    private static final int PETS = 100;
    private static final int PET_ID = 101;

    // NO_MATCH returns the matcher to its root
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Add the URI matches, run first when running this code
    static
    {
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS, PETS);
        sUriMatcher.addURI(PetContract.CONTENT_AUTHORITY, PetContract.PATH_PETS+"/#", PET_ID);
    }

    @Override
    public boolean onCreate()
    {
        mHelper = new PetDbHelper( getContext() );
        return true;
    }

    // Perform Query at given URI. Use the given projection, selection, selection arguments, and sort order.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return null;
    }

    // Return the MIME type of the URI content
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    // Insert new ContentValues into the URI
    @Override
    public Uri insert(Uri uri, ContentValues cvalues)
    {
        return null;
    }

    // Delete data from the URI matching the selection and selectionArgs
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    // Update the data in the URI at the selection with new ContentValues
    @Override
    public int update(Uri uri, ContentValues cvalues, String selection, String[] selectionArgs)
    {
        return 0;
    }
}
