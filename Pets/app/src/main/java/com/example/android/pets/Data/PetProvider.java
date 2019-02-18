package com.example.android.pets.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import static com.example.android.pets.Data.PetDbHelper.LOG_TAG;

public class PetProvider extends ContentProvider
{
    // Gives access to pets database
    private PetDbHelper mHelper;

    // static integers for URI matches
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
        SQLiteDatabase petDB = mHelper.getReadableDatabase();

        Cursor cursor = null;

        int match = sUriMatcher.match(uri);
        switch(match)
        {
            case PETS:
            {
                cursor = petDB.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case PET_ID:
            {
                selection = PET_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = petDB.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            default:
                throw new IllegalArgumentException("Cannot Query: Unknown Uri: " + uri);
        }

        return cursor;
    }

    // Return the MIME type of the URI content
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    // Insert new ContentValues into the database at the URI
    @Override
    public Uri insert(Uri uri, ContentValues cvalues)
    {
        final int match = sUriMatcher.match(uri);

        switch(match)
        {
            case PETS:
                return insertPet(uri, cvalues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for: " + uri);

        }
    }

    // Helper Method for inserting Pets specifically, database may contain more than just pets
    private Uri insertPet(Uri uri, ContentValues cvalues)
    {
        SQLiteDatabase database = mHelper.getWritableDatabase();

        long id = database.insert(PetContract.PetEntry.TABLE_NAME, null, cvalues);

        if(id == -1)
        {
            Log.e(LOG_TAG, "Failed to insert new row for: " + uri);
            return null;
        }

        // Return the uri with the id of the new row
        return ContentUris.withAppendedId(uri, id);
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
