package com.fitness.dense.densefitness.workouts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fitness.dense.densefitness.Interfaces.NewWorkoutListener;
import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.Information;
import com.fitness.dense.densefitness.R;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.WorkoutItemTouchHelperCallback;
//import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutTouchListener;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutsAdapter;
import com.fitness.dense.densefitness.database.WorkoutTable;
import com.fitness.dense.densefitness.database.contentProviderWorkout.WorkoutContentProvider;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-09-17.
 */
public class WorkoutsFragment extends Fragment implements NewWorkoutListener {
    public static final String ARG_OBJECT = "object";

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private WorkoutsAdapter adapter;
    private Context context;

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
        adapter = new WorkoutsAdapter(getActivity(), getAllWorkouts());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    public ArrayList<Information> getAllWorkouts() {
        ArrayList<Information> WorkoutList = new ArrayList<>();

        Uri uri = WorkoutContentProvider.CONTENT_URI;
        String[] columns = {WorkoutTable.COLUMN_WORKOUT_NAME, WorkoutTable.COLUMN_DESCRIPTION, WorkoutTable.COLUMN_WORKOUT_DATE};

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Information information = new Information();

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
        Toast.makeText(getActivity(), "New workout", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), WorkoutDetails.class);
        startActivity(intent);
    }
}

