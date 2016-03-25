package com.fitness.dense.densefitness.workouts.WorkoutsListManager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fitness.dense.densefitness.DividerItemDecoration;
import com.fitness.dense.densefitness.Interfaces.WorkoutListener;
import com.fitness.dense.densefitness.Interfaces.NewWorkoutListener;
import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.workouts.Benchmark.Benchmarks;
import com.fitness.dense.densefitness.workouts.WorkoutDetails;
import com.fitness.dense.densefitness.R;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.WorkoutItemTouchHelperCallback;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutTouchListener;
import com.fitness.dense.densefitness.database.WorkoutTable;
import com.fitness.dense.densefitness.database.contentProviderWorkout.WorkoutContentProvider;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-09-17.
 */
public class WorkoutsFragment extends Fragment implements NewWorkoutListener, ActionMode.Callback, WorkoutListener {
    public static final String ARG_OBJECT = "object";

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private WorkoutsAdapter adapter;
    private Context context;
    ActionMode actionMode = null;

    public WorkoutsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.workouts_fragment, container, false);

        context = inflater.getContext();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.workoutlist);
        adapter = new WorkoutsAdapter(getActivity(), getAllWorkouts(), this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);

        return rootView;
    }

    public ArrayList<Information> getAllWorkouts() {
        ArrayList<Information> WorkoutList = new ArrayList<>();

        Uri uri = WorkoutContentProvider.CONTENT_URI;
        String[] columns = {WorkoutTable.COLUMN_ID, WorkoutTable.COLUMN_WORKOUT_NAME, WorkoutTable.COLUMN_DESCRIPTION, WorkoutTable.COLUMN_WORKOUT_DATE};
        String whereClause = WorkoutTable.COLUMN_BENCHMARK_TYPE + " is null or " + WorkoutTable.COLUMN_BENCHMARK_TYPE + " =  ?";

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, whereClause, new String[] {""}, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Information information = new Information();

                information.setId(cursor.getInt(cursor.getColumnIndex(WorkoutTable.COLUMN_ID)));
                information.setTitle(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_WORKOUT_NAME)));
                information.setDescription(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_DESCRIPTION)));
                information.setDate(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_WORKOUT_DATE)));
                WorkoutList.add(information);
            }
            while (cursor.moveToNext());
        }
        return WorkoutList;
    }

    @Override
    public void onNewWorkoutClick() {
        Intent intent = new Intent(getActivity(), WorkoutDetails.class);
        startActivity(intent);
    }

    @Override
    public void onHeroWorkoutClick() {
        Intent intent = new Intent(getActivity(), Benchmarks.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.selected_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        ArrayList<String> checkedItems = adapter.getCheckedItems();

        if(checkedItems.size() == 0) {
            mode.finish();
            return false;
        }

        String columnidStrings = "";
        for (int i = 0; i < checkedItems.size(); i++)
        {
            String workoutId = checkedItems.get(i).toString();
            if(columnidStrings == "")
                columnidStrings += String.valueOf(workoutId);
            else
                columnidStrings += "," + String.valueOf(workoutId);
        }

        try
        {
            if(!columnidStrings.contains(",")) {
                Uri uri = Uri.parse(WorkoutContentProvider.CONTENT_URI + "/"+ columnidStrings);

                ContentResolver contentResolver = getActivity().getContentResolver();
                contentResolver.delete(uri, null, null);
            }
            else {
                Uri uri = Uri.parse(WorkoutContentProvider.CONTENT_URI + "/" + "2");

                ContentResolver contentResolver = getActivity().getContentResolver();
                contentResolver.delete(uri, columnidStrings, null);
            }
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }

        adapter = new WorkoutsAdapter(getActivity(), getAllWorkouts(), this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mode.finish();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    public void onCheckedChanged() {
        actionMode = getActivity().startActionMode(WorkoutsFragment.this);
    }

    @Override
    public void removeTrashCan() {
        if(actionMode != null)
            actionMode.finish();
    }
}

