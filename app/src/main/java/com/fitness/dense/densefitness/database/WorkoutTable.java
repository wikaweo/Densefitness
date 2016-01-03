package com.fitness.dense.densefitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fredrik on 2015-09-21.
 */
public class WorkoutTable {

    // Database table
    public static final String TABLE_WORKOUT = "workout";
    public static final String COLUMN_ID = "workout_id";
    public static final String COLUMN_WORKOUT_NAME = "workout_name";
    public static final String COLUMN_WORKOUT_DATE = "workout_date";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_ROUNDS = "rounds";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_REPS = "reps";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_WORKOUT
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WORKOUT_NAME + " text, "
            + COLUMN_WORKOUT_DATE + " integer, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_TIME + " integer, "
            + COLUMN_ROUNDS + " integer, "
            + COLUMN_WEIGHT + " integer, "
            + COLUMN_REPS + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL("insert into " + TABLE_WORKOUT + "(" + COLUMN_WORKOUT_NAME + ") values('Linda')");
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(WorkoutTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT);
        onCreate(database);
    }
}
