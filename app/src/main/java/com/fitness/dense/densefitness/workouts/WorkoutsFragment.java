package com.fitness.dense.densefitness.workouts;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.Toast;

import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.Information;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.OnStartDragListener;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.ItemTouchHelper.WorkoutItemTouchHelperCallback;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutTouchListener;
import com.fitness.dense.densefitness.workouts.WorkoutsListManager.WorkoutsAdapter;
import com.fitness.dense.densefitness.database.WorkoutTable;
import com.fitness.dense.densefitness.database.contentProviderWorkout.WorkoutContentProvider;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-09-17.
 */
public class WorkoutsFragment extends Fragment implements OnStartDragListener {
    public static final String ARG_OBJECT = "object";

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private WorkoutsAdapter adapter;
    private Context context;

    private ActionModeCallback actionModeCallback = new ActionModeCallback();
    private ActionMode actionMode;
    private ItemTouchHelper mItemTouchHelper;

    private Button btnAdd;
    private EditText workoutNameText;

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
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper.Callback callback = new WorkoutItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new WorkoutTouchListener(getActivity(), mRecyclerView, new WorkoutTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "onClick " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "onLongClick " + position, Toast.LENGTH_SHORT).show();

            }
        }));



        workoutNameText = (EditText)rootView.findViewById(R.id.etWorkoutName);
        btnAdd = (Button)rootView.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String workoutName = workoutNameText.getText().toString();

                 if(!workoutName.equals("")){
                     if(adapter.getItemCount() > 1){
                         Uri uri = WorkoutContentProvider.CONTENT_URI;
                         ContentValues values = new ContentValues();
                         values.put(WorkoutTable.COLUMN_WORKOUT_NAME, workoutName);
                         getActivity().getContentResolver().insert(uri, values);
                         Toast.makeText(context, workoutName + " added successfully", Toast.LENGTH_SHORT).show();
                         workoutNameText.setText("");

                         // Finns bättre lösning troligtvis än att ladda om på detta sättet.
                         //adapter = new WorkoutsAdapter(getActivity(), getAllWorkouts(), this);
                         //mRecyclerView.setAdapter(adapter);
                         //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                     }
                 }
             }
           }
        );

        return rootView;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public ArrayList<Information> getAllWorkouts() {
        ArrayList<Information> WorkoutList = new ArrayList<>();

        Uri uri = WorkoutContentProvider.CONTENT_URI;
        String[] columns = {WorkoutTable.COLUMN_WORKOUT_NAME};

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Information information = new Information();

                information.setTitle(cursor.getString(cursor.getColumnIndex(WorkoutTable.COLUMN_WORKOUT_NAME)));
                WorkoutList.add(information);
            }
            while (cursor.moveToNext());
        }
        return WorkoutList;
    }

    /**
     * Toggle the selection state of an item.
     *
     * If the item was the last one in the selection and is unselected, the selection is stopped.
     * Note that the selection must already be started (actionMode must not be null).
     *
     * @param position Position of the item to toggle the selection state
     */
    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        int count = adapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    public class ActionModeCallback implements ActionMode.Callback{
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate (R.menu.selected_menu, menu);
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_remove:
                    // TODO: actually remove items
                    Log.d(TAG, "menu_remove");
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelection();
            actionMode = null;
        }
    }
}

