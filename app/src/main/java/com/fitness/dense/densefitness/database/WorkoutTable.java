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
        heroWorkouts.put("Bull", "Two rounds for time of:\n" + "200 Double-unders\n" + "135 pound Overhead squat, 50 reps\n" + "50 Pull-ups\n" + "Run 1 mile");
        heroWorkouts.put("Rankel", "Complete as many rounds as possible in 20 minutes of:\n" + "225 pound Deadlift, 6 reps\n" + "7 Burpee pull-ups\n" + "10 Kettlebell swings, 2 pood\n" + "Run 200 meters");
        heroWorkouts.put("Holbrook", "Ten rounds, each for time of:\n" + "115 pound Thruster, 5 reps\n" + "10 Pull-ups\n" + "100 meter Sprint\n" + "Rest 1 minute\n" + "Score is your fastest and slowest round.");
        heroWorkouts.put("Ledesma", "Complete as many rounds as possible in 20 minutes of:\n" + "5 Parallette handstand push-ups\n" + "10 Toes through rings\n" + "20 pound Medicine ball cleans, 15 reps");
        heroWorkouts.put("Wittman", "Seven rounds for time of:\n" + "1.5 pood Kettlebell swing, 15 reps\n" + "95 pound Power clean, 15 reps\n" + "15 Box jumps, 24″ box");
        heroWorkouts.put("McCluskey", "Three rounds of:\n" + "9 Muscle-ups\n" + "15 Burpee pull-ups\n" + "21 Pull-ups\n" + "Run 800 meters\n" + "\n" + "If you’ve got a twenty pound vest or body armor, wear it.");
        heroWorkouts.put("Weaver", "Four rounds for time of:\n" + "10 L-pull-ups\n" + "15 Push-ups\n" + "15 Chest to bar Pull-ups\n" + "15 Push-ups\n" + "20 Pull-ups\n" + "15 Push-ups");
        heroWorkouts.put("Abbate", "Run 1 mile\n" + "155 pound Clean and jerk, 21 reps\n" + "Run 800 meters\n" + "155 pound Clean and jerk, 21 reps\n" + "Run 1 Mile");
        heroWorkouts.put("Hammer", "Five rounds, each for time, of:\n" + "135 pound Power clean, 5 reps\n" + "135 pound Front squat, 10 reps\n" + "135 pound Jerk, 5 reps\n" + "20 Pull-ups\n" + "Rest 90 seconds");
        heroWorkouts.put("Moore", "Complete as many rounds in 20 minutes as you can of:\n" + "15 ft Rope Climb, 1 ascent\n" + "Run 400 meters\n" + "Max rep Handstand push-up");
        heroWorkouts.put("Wilmot", "Six rounds for time of:\n" + "50 Squats\n" + "25 Ring dips");
        heroWorkouts.put("Moon", "Seven rounds for time of:\n" + "40 pound dumbbell Hang split snatch, 10 reps Right arm\n" + "15 ft Rope Climb, 1 ascent\n" + "40 pound dumbbell Hang split snatch, 10 reps Left arm\n" + "15 ft Rope Climb, 1 ascent\n" + "\n" + "Alternate feet in the split snatch sets.");
        heroWorkouts.put("Small", "Three rounds for time of:\n" + "Row 1000 meters\n" + "50 Burpees\n" + "50 Box jumps, 24″ box\n" + "Run 800 meters");
        heroWorkouts.put("Morrison", "50-40-30-20 and 10 rep rounds of:\n" + "Wall ball shots, 20 pound ball\n" + "Box jump, 24 inch box\n" + "Kettlebell swings, 1.5 pood");
        heroWorkouts.put("Gator", "Eight rounds for time of:\n" + "185 pound Front squat, 5 reps\n" + "26 Ring push-ups");
        heroWorkouts.put("Bradley", "10 rounds for time of:\n" + "Sprint 100 meters\n" + "10 Pull-ups\n" + "Sprint 100 meters\n" + "10 Burpees\n" + "Rest 30 seconds");
        heroWorkouts.put("Meadows", "For time:\n" + "20 Muscle-ups\n" + "25 Lowers from an inverted hang on the rings, slowly, with straight body and arms\n" + "30 Ring handstand push-ups\n" + "35 Ring rows\n" + "40 Ring push-ups");
        heroWorkouts.put("Santiago", "Seven rounds for time of:\n" + "35 pound Dumbbell hang squat clean, 18 reps\n" + "18 Pull-ups\n" + "135 pound Power clean, 10 reps\n" + "10 Handstand push-ups");
        heroWorkouts.put("Carse", "21-18-15-12-9-6-3 reps for time of:\n" + "95 pound Squat clean\n" + "Double-under\n" + "185 pound Deadlift\n" + "24″ Box jump\n" + "Begin each round with a 50 meter Bear crawl.");
        heroWorkouts.put("Bradshaw", "10 rounds for time of:\n" + "3 Handstand push-ups\n" + "225 pound Deadlift, 6 reps\n" + "12 Pull-ups\n" + "24 Double-unders");
        heroWorkouts.put("White", "Five rounds for time of:\n" + "15′ Rope climb, 3 ascents\n" + "10 Toes to bar\n" + "21 Walking lunge steps with 45lb plate held overhead\n" + "Run 400 meters");
        heroWorkouts.put("Santora", "Three rounds for reps of:\n" + "155 pound Squat cleans, 1 minute\n" + "20′ Shuttle sprints (20′ forward + 20′ backwards = 1 rep), 1 minute\n" + "245 pound Deadlifts, 1 minute\n" + "Burpees, 1 minute\n" + "155 pound Jerks, 1 minute\n" + "Rest 1 minute");
        heroWorkouts.put("Wood", "5 Rounds for time of:\n" + "Run 400 meters\n" + "10 Burpee box jumps, 24″ box\n" + "95 pound Sumo-deadlift high-pull, 10 reps\n" + "95 pound Thruster, 10 reps\n" + "\n" + "Rest 1 minute");
        heroWorkouts.put("Hidalgo", "For time:\n" + "Run 2 miles\n" + "Rest 2 minutes\n" + "135 pound Squat clean, 20 reps\n" + "20 Box jump, 24″ box\n" + "20 Walking lunge steps with 45lb plate held overhead\n" + "20 Box jump, 24″ box\n" + "135 pound Squat clean, 20 reps\n" + "Rest 2 minutes\n" + "Run 2 miles\n" + "If you’ve got a twenty pound vest or body armor, wear it.");
        heroWorkouts.put("Ricky", "Complete as many rounds as possible in 20 minutes of:\n" + "10 Pull-ups\n" + "75 pound dumbbell Deadlift, 5 reps\n" + "135 pound Push-press, 8 reps");
        heroWorkouts.put("Dae Han", "Three rounds for time of:\n" + "Run 800 meters with a 45 pound barbell\n" + "15 foot Rope climb, 3 ascents\n" + "135 pound Thruster, 12 reps");
        heroWorkouts.put("Desforges", "Five rounds for time of:\n" + "225 pound Deadlift, 12 reps\n" + "20 Pull-ups\n" + "135 pound Clean and jerk, 12 reps\n" + "20 Knees to elbows");
        heroWorkouts.put("Rahoi", "Complete as many rounds as possible in 12 minutes of:\n" + "24 inch Box Jump, 12 reps\n" + "95 pound Thruster, 6 reps\n" + "6 Bar-facing burpees");
        heroWorkouts.put("Del", "For Time:\n" + "25 Burpees\n" + "Run 400 meters with a 20 pound medicine ball\n" + "25 Weighted pull-ups with a 20 pound dumbbell\n" + "Run 400 meters with a 20 pound medicine ball\n" + "25 Handstand push-ups\n" + "Run 400 meters with a 20 pound medicine ball\n" + "25 Chest-to-bar pull-ups\n" + "Run 400 meters with a 20 pound medicine ball\n" + "25 Burpees");
        heroWorkouts.put("Pheezy", "Three rounds for time of:\n" + "165 pound Front squat, 5 reps\n" + "18 Pull-ups\n" + "225 pound Deadlift, 5 reps\n" + "18 Toes-to-bar\n" + "165 pound Push jerk, 5 reps\n" + "18 Hand-release push-ups");
        heroWorkouts.put("Jag 28", "For time:\n" + "Run 800 meters\n" + "28 Kettlebell swings, 2 pood\n" + "28 Strict Pull-ups\n" + "28 Kettlebell clean and jerk, 2 pood each\n" + "28 Strict Pull-ups\n" + "Run 800 meters");
        heroWorkouts.put("Brian", "Three rounds for time of:\n" + "15 foot Rope climb, 5 ascents\n" + "185 pound Back squat, 25 reps");
        heroWorkouts.put("Nick", "12 rounds for time of:\n" + "45 pound Dumbbell hang squat clean, 10 reps\n" + "6 Handstand push-ups on dumbbells");
        heroWorkouts.put("Strange", "Eight rounds for time of:\n" + "600 meter Run\n" + "1.5 pood Weighted pull-up, 11 reps\n" + "11 Walking lunge steps, carrying 1.5 pood kettlebells\n" + "1.5 pood Kettlebell thruster, 11 reps");
        heroWorkouts.put("Lumberjack 20", "20 Deadlifts (275lbs/185lbs)\n" + "Run 400m\n" + "20 KB swings (2pood/1.5pood)\n" + "Run 400m\n" + "20 Overhead Squats (115lbs/75lbs)\n" + "Run 400m\n" + "20 Burpees\n" + "Run 400m\n" + "20 Pullups (Chest to Bar)\n" + "Run 400m\n" + "20 Box jumps (24″/20″)\n" + "Run 400m\n" + "20 DB Squat Cleans (45lbs/30lbs each)\n" + "Run 400m");
        heroWorkouts.put("Brenton", "Five rounds for time of:\n" + "Bear crawl 100 feet\n" + "Standing broad-jump, 100 feet\n" + "\n" + "Do three Burpees after every five broad-jumps. If you’ve got a twenty pound vest or body armor, wear it.");
        heroWorkouts.put("Tumilson", "8 rounds for time of:\n" + "Run 200 meters\n" + "11 Dumbbell burpee deadlifts, 60 pound dumbbells");
        heroWorkouts.put("Ship", "Nine rounds for time of:\n" + "185 pound Squat clean, 7 reps\n" + "8 Burpee box jumps, 36″ box");
        heroWorkouts.put("Jared", "4 rounds for time of:\n" + "Run 800 meters\n" + "40 Pull-ups\n" + "70 Push-ups");
        heroWorkouts.put("Tully", "Four rounds for time of:\n" + "Swim 200 meters\n" + "40 pound Dumbbell squat cleans, 23 reps");
        heroWorkouts.put("Holleyman", "30 rounds for time of:\n" + "5 Wall ball shots, 20 pound ball\n" + "3 Handstand push-ups\n" + "225 pound Power clean, 1 rep");
        heroWorkouts.put("Adrian", "Seven rounds for time of:\n" + "3 Forward rolls\n" + "5 Wall climbs\n" + "7 Toes to bar\n" + "9 Box jumps, 30″ box");
        heroWorkouts.put("Glen", "For time:\n" + "135 pound Clean and jerk, 30 reps\n" + "Run 1 mile\n" + "15 foot Rope climb, 10 ascents\n" + "Run 1 mile\n" + "100 Burpees");
        heroWorkouts.put("Tom", "Complete as many rounds in 25 minutes as you can of:\n" + "7 Muscle-ups\n" + "155 pound Thruster, 11 reps\n" + "14 Toes-to-bar");
        heroWorkouts.put("Moose", "1000 Meter Row (To Represent his joining of the U.S. Army in 2010)\n" + "then (10x)\n" + "7 Bar Facing Burpee’s (7 to represent the month of July that he was K.I.A.)\n" + "3 Thrusters (95#/65#) (3 to represent the day in which he was K.I.A.)\n" + "10 Rounds\n" + "then\n" + "1200 Meter Run (with Weighted Vest or Med. Ball 20#/14#)");
        heroWorkouts.put("Ralph", "Four rounds for time of:\n" + "250 pound Deadlift, 8 reps\n" + "16 Burpees\n" + "15 foot Rope climb, 3 ascents\n" + "Run 600 meters");
        heroWorkouts.put("Clovis", "For time:\n" + "Run 10 miles\n" + "150 Burpee pull-ups");
        heroWorkouts.put("Weston", "Five rounds for time of:\n" + "Row 1000 meters\n" + "200 meter Farmer carry, 45 pound dumbbells\n" + "45 pound dumbbell Waiter walk, 50 meters, Right arm\n" + "45 pound dumbbell Waiter walk, 50 meters, Left arm");
        heroWorkouts.put("Hortman", "Complete as many rounds as possible in 45 minutes of:\n" + "Run 800 meters\n" + "80 Squats\n" + "8 Muscle-ups");
        heroWorkouts.put("Hamilton", "Three rounds for time of:\n" + "Row 1000 meters\n" + "50 Push-ups\n" + "Run 1000 meters\n" + "50 Pull-ups");
        heroWorkouts.put("Zeus", "Three rounds for time of:\n" + "30 Wall ball shots, 20 pound ball\n" + "75 pound Sumo deadlift high-pull, 30 reps\n" + "30 Box jump, 20″ box\n" + "75 pound Push press, 30 reps\n" + "Row 30 calories\n" + "30 Push-ups\n" + "Body weight Back squat, 10 reps");
        heroWorkouts.put("Barraza", "Complete as many rounds as possible in 18 minutes of:\n" + "Run 200 meters\n" + "275 pound Deadlift, 9 reps\n" + "6 Burpee bar muscle-ups");
        heroWorkouts.put("Klepto", "4 rounds for time of:\n" + "27 Box jumps, 24″ box\n" + "20 Burpees\n" + "11 Squat cleans, 145 pounds");
        heroWorkouts.put("Schmalls", "Run 800 meters\n" + "Then two rounds of:\n" + "50 Burpees\n" + "40 Pull-ups\n" + "30 One-legged squats\n" + "20 Kettlebell swings, 1.5 pood\n" + "10 Handstand push-ups\n" + "Then,\n" + "Run 800 meters");
        heroWorkouts.put("Gallant", "For time:\n" + "Run 1 mile with a 20 pound medicine ball\n" + "60 Burpee pull-ups\n" + "Run 800 meters with a 20 pound medicine ball\n" + "30 Burpee pull-ups\n" + "Run 400 meters with a 20 pound medicine ball\n" + "15 Burpee pull-ups");
        heroWorkouts.put("Bruck", "Four rounds for time of:\n" + "Run 400 meters\n" + "185 pound Back squat, 24 reps\n" + "135 pound Jerk, 24 reps");
        heroWorkouts.put("Dobogai", "Seven rounds for time of:\n" + "8 Muscle-ups\n" + "22 yard Farmer carry, 50 pound dumbbells");
        heroWorkouts.put("Donny", "21-15-9-9-15-21 reps for time of:\n" + "225 pound Deadlift\n" + "Burpee");
        heroWorkouts.put("Hotshots 19", "Six rounds for time of:\n" + "30 Squats\n" + "135 pound Power clean, 19 reps\n" + "7 Strict Pull-ups\n" + "Run 400 meters");
        heroWorkouts.put("The Don", "For time:\n" + "66 Deadlifts, 110 pounds\n" + "66 Box jump, 24 inch box\n" + "66 Kettlebell swings, 1.5 pood\n" + "66 Knees to elbows\n" + "66 Sit-ups\n" + "66 Pull-ups\n" + "66 Thrusters, 55 pounds\n" + "66 Wall ball shots, 20 pound ball\n" + "66 Burpees\n" + "66 Double-unders\n" + "\n");
        heroWorkouts.put("Roney", "Four rounds for time of:\n" + "Run 200 meters\n" + "135 pound Thruster, 11 reps\n" + "Run 200 meters\n" + "135 pound Push press, 11 reps\n" + "Run 200 meters\n" + "135 pound Bench press, 11 reps");
        heroWorkouts.put("Lee", "Five rounds for time of:\n" + "Run 400 meters\n" + "345 pound Deadlift, 1 rep\n" + "185 pound Squat clean, 3 reps\n" + "185 pound Push jerk, 5 reps\n" + "3 Muscle-ups\n" + "15 foot Rope climb, 1 ascent");
        heroWorkouts.put("Willy", "Three rounds for time of:\n" + "Run 800 meters\n" + "225 pound Front squat, 5 reps\n" + "Run 200 meters\n" + "11 Chest to bar pull-ups\n" + "Run 400 meters\n" + "12 Kettlebell swings, 2 pood");
        heroWorkouts.put("TK", "Complete as many rounds as possible in 20 minutes of:\n" + "8 Strict Pull-ups\n" + "8 Box jumps, 36″ box\n" + "12 Kettlebell swings, 2 pood");
        heroWorkouts.put("DG", "Complete as many rounds as possible in 10 minutes of:\n" + "8 Toes to bar\n" + "35 pound Dumbbell thruster, 8 reps\n" + "35 pound Dumbbell walking lunge, 12 steps");
        heroWorkouts.put("Coffey", "For time:\n" + "Run 800 meters\n" + "135 pound Back squat, 50 reps\n" + "135 pound Bench press, 50 reps\n" + "Run 800 meters\n" + "135 pound Back squat, 35 reps\n" + "135 pound Bench press, 35 reps\n" + "Run 800 meters\n" + "135 pound Back squat, 20 reps\n" + "135 pound Bench press, 20 reps\n" + "Run 800 meters\n" + "1 Muscle-up");
        heroWorkouts.put("Wyk", "5 rounds for time:\n" + "225-lb. front squats, 5 reps\n" + "15-foot rope climbs, 5 ascents\n" + "Run 400 meters with a 45-lb. plate");
        heroWorkouts.put("Alexander", "5 rounds for time of:\n" + "31 back squats, 135 lb.\n" + "12 power cleans, 185 lb.");
        heroWorkouts.put("Helton", "Three rounds for time of:\n" + "Run 800 meters\n" + "50 pound Dumbbell squat cleans, 30 reps\n" + "30 Burpees");
        heroWorkouts.put("Zembiec", "5 rounds for time of:\n" + "11 back squats, 185 lb.\n" + "7 strict burpee pull-ups\n" + "400-meter run");
        heroWorkouts.put("Nukes", "8 minutes to complete:\n" + "1-mile run\n" + "315-lb. deadlifts, max reps\n" + "Then, 10 minutes to complete:\n" + "1-mile run\n" + "225-lb. power cleans, max reps\n" + "Then, 12 minutes to complete:\n" + "1-mile run\n" + "135-lb. overhead squats, max reps");
        heroWorkouts.put("Rocket", "Complete as many rounds as possible in 30 minutes of:\n" + "50-yard swim\n" + "10 push-ups\n" + "15 squats");
        heroWorkouts.put("Kevin", "3 rounds for time of:\n" + "185-lb. deadlifts, 32 reps\n" + "32 hanging hip touches, alternating arms\n" + "800-meter running farmer carry, 15-lb. dumbbells");
        heroWorkouts.put("Feeks", "For time:\n" + "2 x 100-meter shuttle sprint\n" + "2 squat clean thrusters, 65-lb. dumbbells\n" + "4 x 100-meter shuttle sprint\n" + "4 squat clean thrusters, 65-lb. dumbbells\n" + "6 x 100-meter shuttle sprint\n" +
                "6 squat clean thrusters, 65-lb. dumbbells\n" + "8 x 100-meter shuttle sprint\n" + "8 squat clean thrusters, 65-lb. dumbbells\n" + "10 x 100-meter shuttle sprint\n" + "10 squat clean thrusters, 65-lb. dumbbells\n" + "12 x 100-meter shuttle sprint\n" + "12 squat clean thrusters, 65-lb. dumbbells\n" +
                "14 x 100-meter shuttle sprint\n" + "14 squat clean thrusters, 65-lb. dumbbells\n" + "16 x 100-meter shuttle sprint\n" + "16 squat clean thrusters, 65-lb. dumbbells");
        heroWorkouts.put("Riley", "For time:\n" + "Run 1.5 miles\n" + "150 burpees\n" + "Run 1.5 miles\n" + "\n" + "If you’ve got a weight vest or body armor, wear it.");
        heroWorkouts.put("Luke", "For time:\n" + "Run 400 meters\n" + "155-lb. clean and jerks, 15 reps\n" + "Run 400 meters\n" + "30 toes-to-bars\n" + "Run 400 meters\n" + "45 wall-ball shots, 20-lb. ball\n" + "Run 400 meters\n" +
                "1.5-pood kettlebell swings, 45 reps\n" + "Run 400 meters\n" + "30 ring dips\n" + "Run 400 meters\n" + "155-lb. weighted lunges, 15 steps\n" + "Run 400 meters");
        heroWorkouts.put("Loredo", "Six rounds for time of:\n" + "24 Squats\n" + "24 Push-ups\n" + "24 Walking lunge steps\n" + "Run 400 meters");

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
