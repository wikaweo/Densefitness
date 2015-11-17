package com.fitness.dense.densefitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fredrik on 2015-09-21.
 */
public class BodyMassTable {

    // Database table
    public static final String TABLE_BODY_MASS = "body_mass";
    public static final String COLUMN_ID = "body_mass_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_FAT = "fat";
    public static final String COLUMN_MUSCLE_MASS = "muscle_mass";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_BODY_MASS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_DATE + " real, "
            + COLUMN_WEIGHT + " real, "
            + COLUMN_FAT + " real, "
            + COLUMN_MUSCLE_MASS + " real"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(BodyMassTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_BODY_MASS);
        onCreate(database);
    }
}
