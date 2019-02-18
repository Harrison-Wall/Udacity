package com.example.android.pets.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract
{
    void PetContract(){}

    // URI Constants
    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";


    public static final class PetEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Pets";

        // COLUMNS for Name, Breed, Gender, Weight
        public static final String _ID               = BaseColumns._ID;
        public static final String COLUMN_PET_NAME   = "name";
        public static final String COLUMN_PET_BREED  = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";

        // Possible Values for Gender
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE    = 1;
        public static final int GENDER_FEMALE  = 2;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        // MIME type for a List of Pets
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE +"/"+ CONTENT_AUTHORITY +"/"+ PATH_PETS;

        // MIME type for a single Pet
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE +"/"+ CONTENT_AUTHORITY +"/"+ PATH_PETS;


        public static boolean isValidGender(int mGender)
        {
            switch (mGender)
            {
                case GENDER_MALE:
                case GENDER_FEMALE:
                case GENDER_UNKNOWN:
                    return true;
                default:
                    return false;
            }
        }

    }
}
