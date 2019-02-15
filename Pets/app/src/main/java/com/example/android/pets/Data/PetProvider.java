package com.example.android.pets.Data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class PetProvider extends ContentProvider
{
    private PetDbHelper mHelper;

    @Override
    public boolean onCreate()
    {
        mHelper = new PetDbHelper( getContext() );
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] args, String s, String[] strings1, String s1)
    {
        return null;
    }

    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues cvalues)
    {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings)
    {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues cvalues, String s, String[] strings)
    {
        return 0;
    }
}
