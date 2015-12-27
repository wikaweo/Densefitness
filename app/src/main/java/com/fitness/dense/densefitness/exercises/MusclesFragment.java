package com.fitness.dense.densefitness.exercises;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.MuscleTable;
import com.fitness.dense.densefitness.database.contentProviderMuscle.MuscleContentProvider;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-09-20.
 */
public class MusclesFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    private RecyclerView mRecyclerView;
    private MusclesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.muscles_fragment, container, false);
        /*Bundle args = getArguments();
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                Integer.toString(args.getInt(WorkoutsFragment.ARG_OBJECT)));*/

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.exercisesGrid);
        adapter = new MusclesAdapter(getActivity(), getAllMuscles());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        return rootView;

    }

    private ArrayList<MuscleCell> getAllMuscles() {
        ArrayList<MuscleCell> MuscleList = new ArrayList<>();

        Uri uri = MuscleContentProvider.CONTENT_URI;
        String[] columns = {MuscleTable.COLUMN_MUSCLE_NAME};

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                MuscleCell exerciseCell = new MuscleCell();

                exerciseCell.setTitle(cursor.getString(cursor.getColumnIndex(MuscleTable.COLUMN_MUSCLE_NAME)));
                MuscleList.add(exerciseCell);
            }
            while (cursor.moveToNext());
        }
        return MuscleList;
    }
}
