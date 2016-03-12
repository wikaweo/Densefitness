package com.fitness.dense.densefitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fredrik on 2016-02-13.
 */
public class ExerciseDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercisetable.db";
    private static final int DATABASE_VERSION = 1;

    public ExerciseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) { ExerciseTable.onCreate(database);}

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        ExerciseTable.onUpgrade(database, oldVersion, newVersion);
    }
}
