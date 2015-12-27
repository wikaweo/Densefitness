package com.fitness.dense.densefitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fredrik on 2015-12-26.
 */
public class MuscleTable {

    // Database table
    public static final String TABLE_MUSCLE = "muscle";
    public static final String COLUMN_ID = "muscle_id";
    public static final String COLUMN_MUSCLE_NAME = "muscle_name";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_MUSCLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MUSCLE_NAME + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Favorites')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Chest')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Back')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Shoulders')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Arms')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Abs')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Legs')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Butt')");
        database.execSQL("insert into " + TABLE_MUSCLE + "(" + COLUMN_MUSCLE_NAME + ") values('Olympic lifting')");
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(WorkoutTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_MUSCLE);
        onCreate(database);
    }
}
