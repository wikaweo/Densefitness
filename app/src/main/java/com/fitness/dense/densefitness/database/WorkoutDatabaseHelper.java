package com.fitness.dense.densefitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Fredrik on 2015-09-21.
 */
public class WorkoutDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "workouttable.db";
    private static final int DATABASE_VERSION = 1;

    public WorkoutDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        WorkoutTable.onCreate(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        WorkoutTable.onUpgrade(database, oldVersion, newVersion);
    }
}
