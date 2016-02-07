package com.fitness.dense.densefitness.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
    public static final String COLUMN_BENCHMARK_TYPE = "benchmark_type";

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
            + COLUMN_REPS + " integer, "
            + COLUMN_BENCHMARK_TYPE + " text"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

        Map<String, String> heroWorkouts = getHeroWorkouts();

        for (Map.Entry<String, String> entry : heroWorkouts.entrySet())
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_WORKOUT_NAME, entry.getKey());
            contentValues.put(COLUMN_DESCRIPTION, entry.getValue());
            contentValues.put(COLUMN_BENCHMARK_TYPE, "Hero");
            database.insert(TABLE_WORKOUT, null, contentValues);
        }
        
        
        /*ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_WORKOUT_NAME, "JT");
        contentValues.put(COLUMN_DESCRIPTION, "21-15-9 reps, for time\n" + "Handstand push-ups\n" + "Ring dips\n" + "Push-ups");
        contentValues.put(COLUMN_BENCHMARK_TYPE, "Hero");
        database.insert(TABLE_WORKOUT, null, contentValues);

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_WORKOUT_NAME, "Michael");
        cv.put(COLUMN_DESCRIPTION, "3 rounds for time\n" + "Run 800 meters\n" + "50 Back Extensions\n" + "50 Sit-ups");
        cv.put(COLUMN_ROUNDS, "3");
        contentValues.put(COLUMN_BENCHMARK_TYPE, "Hero");
        database.insert(TABLE_WORKOUT, null, cv);*/
    }

    public static Map<String, String> getHeroWorkouts() {
        Map<String, String> heroWorkouts = new HashMap<>();

        heroWorkouts.put("JT", "21-15-9 reps, for time\n" + "Handstand push-ups\n" + "Ring dips\n" + "Push-ups");
        heroWorkouts.put("Michael", "3 rounds for time\n" + "Run 800 meters\n" + "50 Back Extensions\n" + "50 Sit-ups");
        heroWorkouts.put("Murph", "For time\n" + "1 mile Run\n" + "100 Pull-ups\n" + "200 Push-ups\n" + "300 Squats\n" + "1 mile Run");
        heroWorkouts.put("Daniel", "For time\n" + "50 Pull-ups\n" + "400 meter run\n" + "95 pound Thruster, 21 reps\n" + "800 meter run\n" + "95 pound Thruster, 21 reps\n" + "400 meter run\n" + "50 Pull-ups");
        heroWorkouts.put("Josh", "For time\n" + "95 pound Overhead squat, 21 reps\n" + "42 Pull-ups\n" + "95 pound Overhead squat, 15 reps\n" + "30 Pull-ups\n" + "95 pound Overhead squat, 9 reps\n" + "18 Pull-ups");
        heroWorkouts.put("Jason", "100 Squats\n" + "5 Muscle-ups\n" + "75 Squats\n" + "10 Muscle-ups\n" + "50 Squats\n" + "15 Muscle-ups\n" + "25 Squats\n" + "20 Muscle-ups");
        heroWorkouts.put("Badger", "3 rounds for time\n" + "95 pound Squat clean, 30 reps\n" + "30 Pull-ups\n" + "Run 800 meters");
        heroWorkouts.put("Joshie", "3 rounds for time\n" + "40 pound Dumbbell snatch, 21 reps, right arm\n" + "21 L Pull-ups\n" + "40 pound Dumbbell snatch, 21 reps, left arm\n" + "21 L Pull-ups");
        heroWorkouts.put("Nate", "As many rounds as possible in 20 min\n" + "22 Muscle-ups\n" + "4 Handstand Push-ups\n" + "8 2-Pood Kettlebell swings");
        heroWorkouts.put("Randy", "75# power snatch, 75 reps for time");
        heroWorkouts.put("Tommy V", "For time\n" + "115 pound Thruster, 21 reps\n" + "15 ft Rope Climb, 12 ascents\n" + "115 pound Thruster, 15 reps\n" + "15 ft Rope Climb, 9 ascents\n" + "115 pound Thruster, 9 reps\n" + "15 ft Rope Climb, 6 ascents");
        heroWorkouts.put("Griff", "For time\n" + "Run 800 meters\n" + "Run 400 meters backwards\n" + "Run 800 meters\n" + "Run 400 meters backwards");
        heroWorkouts.put("Ryan", "Five rounds for time\n" + "7 Muscle-ups\n" + "21 Burpees Each burpee terminates with a jump 12 inches above max standing reach");
        heroWorkouts.put("Erin", "Five rounds for time\n" + "40 pound Dumbbells split clean, 15 reps\n" + "21 Pull-ups");
        heroWorkouts.put("Mr. Joshua", "Five rounds for time\n" + "Run 400 meters\n" + "30 Glute-ham sit-ups\n" + "250 pound Deadlift, 15 reps");
        heroWorkouts.put("DT", "Five rounds for time\n" + "155 pound Deadlift, 12 reps\n" + "155 pound Hang power clean, 9 reps\n" + "155 pound Push jerk, 6 reps");
        heroWorkouts.put("Danny", "As many rounds in 20 min of:\n" + "24″ box jump, 30 reps\n" + "115 pound push press, 20 reps\n" + "30 pull-ups");
        heroWorkouts.put("Hansen", "Five rounds for time\n" + "30 reps, 2 pood Kettlebell swing\n" + "30 Burpees\n" + "30 Glute-ham sit-ups");
        heroWorkouts.put("Tyler", "Five rounds for time\n" + "7 Muscle-ups\n" + "21 reps 95 pound Sumo-deadlift high-pull");
        heroWorkouts.put("Stephen", "30-25-20-15-10-5 rep rounds for time\n" + "GHD sit-up\n" + "Back extension\n" + "Knees to elbow\n" + "95 pound Stiff legged deadlift");
        heroWorkouts.put("Garrett", "Three rounds for time\n" + "75 Squats\n" + "25 Ring handstand push-ups\n" + "25 L-pull-ups");
        heroWorkouts.put("War Frank", "Three rounds for time\n" + "25 Muscle-ups\n" + "100 Squats\n" + "35 GHD situps");
        heroWorkouts.put("McGhee", "As many rounds as possible in 30 min\n" + "275 pound Deadlift, 5 reps\n" + "13 Push-ups\n" + "9 Box jumps, 24 inch box");
        heroWorkouts.put("Paul", "Five rounds for time\n" + "50 Double unders\n" + "35 Knees to elbows\n" + "185 pound Overhead walk, 20 yards");
        heroWorkouts.put("Jerry", "For time\n" + "Run 1 mile\n" + "Row 2K\n" + "Run 1 mile");
        heroWorkouts.put("Nutts", "For time:\n" + "10 Handstand push-ups\n" + "250 pound Deadlift, 15 reps\n" + "25 Box jumps, 30 inch box\n" + "50 Pull-ups\n" + "100 Wallball shots, 20 pounds, 10′\n" + "200 Double-unders\n" + "Run 400 meters with a 45lb plate");
        heroWorkouts.put("Arnie", "With a single 2 pood kettlebell:\n" + "21 Turkish get-ups, Right arm\n" + "50 Swings\n" + "21 Overhead squats, Left arm\n" + "50 Swings\n" + "21 Overhead squats, Right arm\n" + "50 Swings\n" + "21 Turkish get-ups, Left arm");
        heroWorkouts.put("The Seven", "Seven rounds for time of:\n" + "7 Handstand push-ups\n" + "135 pound Thruster, 7 reps\n" + "7 Knees to elbows\n" + "245 pound Deadlift, 7 reps\n" + "7 Burpees\n" + "7 Kettlebell swings, 2 pood\n" + "7 Pull-ups");
        heroWorkouts.put("RJ", "Five rounds for time of:\n" + "Run 800 meters\n" + "15 ft Rope Climb, 5 ascents\n" + "50 Push-ups");
        heroWorkouts.put("Luce", "Wearing a 20 pound vest, three rounds for time of:\n" + "1K Run\n" + "10 Muscle-ups\n" + "100 Squats");
        heroWorkouts.put("Johnson", "Complete as many rounds in 20 minutes as you can of:\n" + "245 pound Deadlift, 9 reps\n" + "8 Muscle-ups\n" + "155 pound Squat clean, 9 reps");
        heroWorkouts.put("Roy", "Five rounds for time of:\n" + "225 pound Deadlift, 15 reps (women 155#)\n" + "20 Box jumps, 24 inch box\n" + "25 Pull-ups");
        heroWorkouts.put("ADAM BROWN", "Two rounds for time of:\n" + "295 pound Deadlift, 24 reps\n" + "24 Box jumps, 24 inch box\n" + "24 Wallball shots, 20 pound ball\n" + "195 pound Bench press, 24 reps\n" + "24 Box jumps, 24 inch box\n" + "24 Wallball shots, 20 pound ball\n" + "145 pound Clean, 24 reps");
        heroWorkouts.put("Coe", "Ten rounds for time of:\n" + "95 pound Thruster, 10 reps\n" + "10 Ring push-ups");
        heroWorkouts.put("Severin", "50 Strict Pull-ups\n" + "100 Push-ups, release hands from floor at the bottom\n" + "Run 5K\n" + "\n" + "If you’ve got a twenty pound vest or body armor, wear it.");
        heroWorkouts.put("Jack", "Complete as many rounds as possible in 20 minutes of:\n" + "115 pound Push press, 10 reps (85#-w)\n" + "10 KB Swings, 1.5 pood (1 pood-w)\n" + "10 Box jumps, 24 inch box (20 inch box – w)");
        heroWorkouts.put("Forrest", "Three rounds for time of:\n" + "20 L-pull-ups\n" + "30 Toes to bar\n" + "40 Burpees\n" + "Run 800 meters");
        heroWorkouts.put("Bulger", "Ten rounds of:\n" + "Run 150 meters\n" + "7 Chest to bar pull-ups\n" + "135 pound Front squat, 7 reps\n" + "7 Handstand push-ups");
        heroWorkouts.put("Blake", "Four rounds for time of:\n" + "100 foot Walking lunge with 45lb plate held overhead\n" + "30 Box jump, 24 inch box\n" + "20 Wallball shots, 20 pound ball\n" + "10 Handstand push-ups");
        heroWorkouts.put("Collin", "Six rounds for time of:\n" + "Carry 50 pound sandbag 400 meters\n" + "115 pound Push press, 12 reps\n" + "12 Box jumps, 24 inch box\n" + "95 pound Sumo deadlift high-pull, 12 reps");
        heroWorkouts.put("Thompson", "10 rounds for time of:\n" + "15 ft Rope Climb, 1 ascent\n" + "95 pound Back squat, 29 reps\n" + "135 pound barbells Farmer carry, 10 meters\n" + "\n" + "Begin the rope climbs seated on the floor.");
        heroWorkouts.put("Whitten", "Five rounds for time of:\n" + "22 Kettlebell swings, 2 pood\n" + "22 Box jump, 24 inch box\n" + "Run 400 meters\n" + "22 Burpees\n" + "22 Wall ball shots, 20 pound ball");


        return heroWorkouts;
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
