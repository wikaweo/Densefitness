package com.fitness.dense.densefitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExerciseTable {

    // Database table
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_ID = "exercise_id";
    public static final String COLUMN_FK_EXERCISE_MUSCLE = "fk_exercise_muscle";
    public static final String COLUMN_EXERCISE_NAME = "exercise_name";
    public static final String COLUMN_EXERCISE_NEW_WORKOUT = "exercise_new_workout";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_EXERCISE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FK_EXERCISE_MUSCLE + " integer, "
            + COLUMN_EXERCISE_NAME + " text, "
            + COLUMN_EXERCISE_NEW_WORKOUT + " integer, "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Squat', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Benchpress', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Deadlift', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Push press', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Pull-up', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Hollow rock', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Snatch', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Clean & Jerk', '0')");
        database.execSQL("insert into " + TABLE_EXERCISE + "(" + COLUMN_EXERCISE_NAME + "," + COLUMN_EXERCISE_NEW_WORKOUT + ") values('Sit-up', '0')");
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(WorkoutTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
        onCreate(database);
    }
}
