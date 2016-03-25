package com.fitness.dense.densefitness.personalRecords;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.fitness.dense.densefitness.DividerItemDecoration;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.ExerciseRecordsTable;
import com.fitness.dense.densefitness.database.ExerciseTable;
import com.fitness.dense.densefitness.database.contentProviderExercise.ExerciseContentProvider;
import com.fitness.dense.densefitness.database.contentProviderExerciseRecords.ExerciseRecordsContentProvider;

import java.util.ArrayList;

public class Exercises extends AppCompatActivity {
    private int muscle;
    private RecyclerView mRecyclerView;
    private ExercisesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        mRecyclerView = (RecyclerView) findViewById(R.id.exercisesList);
        adapter = new ExercisesAdapter(this, getAllExercises());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercises, menu);
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


    private ArrayList<ExercisesInformation> getAllExercises() {
        ArrayList<ExercisesInformation> exercisesInformationList = new ArrayList<>();

        Uri uri = ExerciseContentProvider.CONTENT_URI;
        String[] columns = {ExerciseTable.COLUMN_EXERCISE_NAME};

        Cursor cursor = getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExercisesInformation exerciseInformation = new ExercisesInformation();

                exerciseInformation.setTitle(cursor.getString(cursor.getColumnIndex(ExerciseTable.COLUMN_EXERCISE_NAME)));
                exercisesInformationList.add(exerciseInformation);
            }
            while (cursor.moveToNext());
        }
        return exercisesInformationList;
    }
}
