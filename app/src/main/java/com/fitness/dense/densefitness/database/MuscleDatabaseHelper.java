package com.fitness.dense.densefitness.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fredrik on 2015-12-26.
 */
public class MuscleDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "muscletable.db";
    private static final int DATABASE_VERSION = 1;

    public MuscleDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) { MuscleTable.onCreate(database); }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        MuscleTable.onUpgrade(database, oldVersion, newVersion);
    }
}
