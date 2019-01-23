package com.example.android.pets.Data;

import android.provider.BaseColumns;

public final class PetContract
{
    void PetContract(){}

    public static final class PetEntry
    {
        public static final String TABLE_NAME = "Pets";

        // COLUMNS for Name, Breed, Gender, Weight
        public static final String _ID           = BaseColumns._ID;
        public static final String COLUMN_NAME   = "name";
        public static final String COLUMN_BREED  = "breed";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_WEIGHT = "weight";

        // Possible Values for Gender
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE    = 1;
        public static final int GENDER_FEMALE  = 2;
    }
}
