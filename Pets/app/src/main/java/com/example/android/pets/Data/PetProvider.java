package com.example.android.pets.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.android.pets.Data.PetContract.PetEntry;
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
                cursor = petDB.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case PET_ID:
            {
                selection = PET_ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = petDB.query(PetEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
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
        // Data Validation
        String name = cvalues.getAsString(PetEntry.COLUMN_PET_NAME);
        if(name == null) // Name cannot be null
        {
            throw new IllegalArgumentException("Pet requires a name");
        }

        Integer gender = cvalues.getAsInteger(PetEntry.COLUMN_PET_GENDER);
        if(gender == null || !PetEntry.isValidGender(gender)) // Gender must be one of available options
        {
            throw new IllegalArgumentException("Pet requiers a valid gender");
        }

        Integer weight = cvalues.getAsInteger(PetEntry.COLUMN_PET_WEIGHT);
        if(weight != null && weight <= 0 )   // Can be null, if not must be > 0
        {
            throw new IllegalArgumentException("Pet requiers a valid weight");
        }


        SQLiteDatabase database = mHelper.getWritableDatabase();

        long id = database.insert(PetEntry.TABLE_NAME, null, cvalues);

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
        final int match = sUriMatcher.match(uri);

        switch (match)
        {
            case PETS:
                return updatePet(uri, cvalues, selection, selectionArgs);
            case PET_ID:
                // Extract ID from URI so we can operate only on that ID
                selection = PetContract.PetEntry._ID+"=?";
                selectionArgs = new String[] { String.valueOf( ContentUris.parseId(uri) ) };
                return updatePet(uri, cvalues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Cannot Update, unkown Uri: " + uri);
        }
    }

    // Helper method to update a Pet
    private int updatePet(Uri uri, ContentValues cvalues, String selection, String[] selectionArgs)
    {
        // Data Validation
        if( cvalues.containsKey(PetEntry.COLUMN_PET_NAME) ) // May not be updating fields
        {
            String name = cvalues.getAsString(PetEntry.COLUMN_PET_NAME);
            if(name == null) // Name cannot be null
            {
                throw new IllegalArgumentException("Pet requires a name");
            }
        }
        if( cvalues.containsKey(PetEntry.COLUMN_PET_GENDER) )
        {
            Integer gender = cvalues.getAsInteger(PetEntry.COLUMN_PET_GENDER);
            if(gender == null || !PetEntry.isValidGender(gender)) // Gender must be one of available options
            {
                throw new IllegalArgumentException("Pet requiers a valid gender");
            }
        }
        if( cvalues.containsKey(PetEntry.COLUMN_PET_WEIGHT) )
        {
            Integer weight = cvalues.getAsInteger(PetEntry.COLUMN_PET_WEIGHT);
            if(weight != null && weight <= 0 )   // Can be null, if not must be > 0
            {
                throw new IllegalArgumentException("Pet requiers a valid weight");
            }
        }
        if( cvalues.size() == 0 ) // No Values, don't update
        {
            return 0;
        }

        SQLiteDatabase databse = mHelper.getWritableDatabase();

        // Update the selected Pet(s) with the given values and return the number of rows updated

        return databse.update(PetEntry.TABLE_NAME, cvalues, selection, selectionArgs);
    }
}
