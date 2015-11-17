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
    public static final String COLUMN_MUSCLE_GROUPS = "muscle_groups";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_WORKOUT
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_WORKOUT_NAME + " text, "
            + COLUMN_MUSCLE_GROUPS + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL("insert into " + TABLE_WORKOUT + "(" + COLUMN_WORKOUT_NAME + "," + COLUMN_MUSCLE_GROUPS + ") values('WOD', 'Chest')");
        database.execSQL("insert into " + TABLE_WORKOUT + "(" + COLUMN_WORKOUT_NAME + "," + COLUMN_MUSCLE_GROUPS + ") values('Dense', 'Back')");
        database.execSQL("insert into " + TABLE_WORKOUT + "(" + COLUMN_WORKOUT_NAME + "," + COLUMN_MUSCLE_GROUPS + ") values('Fitness', 'Abs')");
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
