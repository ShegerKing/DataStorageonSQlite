package com.example.beakaltiliskew.datastorageonsqlite;

/**
 * Created by Beakal Tiliskew on 3/25/2018.
 */

import android.provider.BaseColumns;

/**
 * Created by Beakal Tiliskew on 3/25/2018.
 */

public class Contract {

    private Contract() {


    }

    public static class infoEntry implements BaseColumns {
        public static final String TABLE_NAME = "personInfo";
        public static final String COLUMN_NAME_FIRST = "first";
        public static final String COLUMN_NAME_LAST = "last";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_EMAIL = "email";
    }

}