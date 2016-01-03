package com.fitness.dense.densefitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExerciseRecordsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exerciserecordstable.db";
    private static final int DATABASE_VERSION = 1;

    public ExerciseRecordsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) { ExerciseRecordsTable.onCreate(database);}

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        ExerciseRecordsTable.onUpgrade(database, oldVersion, newVersion);
    }
}
