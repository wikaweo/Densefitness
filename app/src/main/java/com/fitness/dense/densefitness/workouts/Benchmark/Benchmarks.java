package com.fitness.dense.densefitness.workouts.Benchmark;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.WorkoutTable;
import com.fitness.dense.densefitness.database.contentProviderWorkout.WorkoutContentProvider;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutsAdapter;

import java.util.ArrayList;

public class Benchmarks extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BenchmarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmarks);

        mRecyclerView = (RecyclerView) findViewById(R.id.benchmarklist);
        adapter = new BenchmarkAdapter(this, getAllBenchmarks());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public ArrayList<Properties> getAllBenchmarks() {
        ArrayList<Properties> WorkoutList = new ArrayList<>();

        Uri uri = WorkoutContentProvider.CONTENT_URI;
        String[] columns = {WorkoutTable.COLUMN_ID, WorkoutTable.COLUMN_WORKOUT_NAME, WorkoutTable.COLUMN_DESCRIPTION, WorkoutTable.COLUMN_WORKOUT_DATE};
        String whereClause = WorkoutTable.COLUMN_BENCHMARK_TYPE + " = ?";

        Cursor cursor = getContentResolver().query(uri, columns, whereClause, new String[] {"Hero"}, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Properties information = new Properties();

                information.setTitle(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_WORKOUT_NAME)));
                information.setDescription(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_DESCRIPTION)));
                WorkoutList.add(information);
            }
            while (cursor.moveToNext());
        }
        return WorkoutList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_benchmarks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
