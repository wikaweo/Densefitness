package com.fitness.dense.densefitness.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2016-02-13.
 */
public class ExerciseTable {

    // Database table
    public static final String TABLE_EXERCISE = "exercise";
    public static final String COLUMN_ID = "exercise_id";
    public static final String COLUMN_EXERCISE_NAME = "exercise_name";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_EXERCISE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_EXERCISE_NAME + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        //database.execSQL("PRAGMA foreign_keys=ON;");

        ArrayList<String> exercises = getAllExercises();

        for (String item : exercises)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_EXERCISE_NAME, item);
            database.insert(TABLE_EXERCISE, null, contentValues);
        }
    }

    public static ArrayList<String> getAllExercises()
    {
        ArrayList<String> exercises = new ArrayList<>();

        exercises.add("Back Squat");
        exercises.add("Bar Muscle-up");
        exercises.add("Bench Press");
        exercises.add("Box Jump");
        exercises.add("Burpee");
        exercises.add("Chest to bar");
        exercises.add("Chin-ups");
        exercises.add("Clean and Jerk");
        exercises.add("Deadlift");
        exercises.add("Double-under");
        exercises.add("Farmers Walk");
        exercises.add("Front Squat");
        exercises.add("Good Mornings");
        exercises.add("Handstand");
        exercises.add("Handstand Push-up");
        exercises.add("Hang Clean");
        exercises.add("Hang Power Clean");
        exercises.add("Hang Power Snatch");
        exercises.add("Hang Snatch");
        exercises.add("Jerk");
        exercises.add("Jumping Squats");
        exercises.add("Kettlebell Swing");
        exercises.add("Kipping Pull-up");
        exercises.add("Knees to elbows");
        exercises.add("Lounges");
        exercises.add("Mountain Climbers");
        exercises.add("Muscle-up");
        exercises.add("Overhead Squat");
        exercises.add("Pistol");
        exercises.add("Power Clean");
        exercises.add("Power Snatch");
        exercises.add("Press");
        exercises.add("Pull-ups");
        exercises.add("Push Jerk");
        exercises.add("Push Press");
        exercises.add("Push-up");
        exercises.add("Ring Dips");
        exercises.add("Rope Climbing");
        exercises.add("Rope jump");
        exercises.add("Row");
        exercises.add("Run");
        exercises.add("Russian Twist");
        exercises.add("Sit-up");
        exercises.add("Snatch");
        exercises.add("Squat");
        exercises.add("Strict Pull-up");
        exercises.add("Sumo Deadlift");
        exercises.add("Thruster");
        exercises.add("Toe to bar");
        exercises.add("Dip");
        exercises.add("V-ups");
        exercises.add("Wall Ball");

        return exercises;
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
