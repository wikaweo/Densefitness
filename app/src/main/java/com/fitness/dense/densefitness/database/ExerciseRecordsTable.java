package com.fitness.dense.densefitness.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExerciseRecordsTable {

    // Database table
    public static final String TABLE_EXERCISE_RECORDS = "exercise_records";
    public static final String COLUMN_ID = "exercise_id";
    public static final String COLUMN_EXERCISE_NAME = "exercise_name";
    public static final String COLUMN_EXERCISE_DATE = "exercise_date";
    public static final String COLUMN_RECORD_TYPE = "record_type";
    public static final String COLUMN_RECORD_RESULT = "record_result";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_EXERCISE_RECORDS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_EXERCISE_NAME + " text, "
            + COLUMN_EXERCISE_DATE + " integer, "
            + COLUMN_RECORD_TYPE + " integer, "
            + COLUMN_RECORD_RESULT + " integer"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL("insert into " + TABLE_EXERCISE_RECORDS + "(" + COLUMN_EXERCISE_NAME + ") values('WOD')");
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(WorkoutTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_RECORDS);
        onCreate(database);
    }
}
