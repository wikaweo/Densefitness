package com.fitness.dense.densefitness.personalRecords;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.ExerciseRecordsTable;
import com.fitness.dense.densefitness.database.contentProviderExerciseRecords.ExerciseRecordsContentProvider;

import java.util.ArrayList;

public class ExercisesFragment extends Fragment {
    private int muscle;
    private RecyclerView mRecyclerView;
    private ExercisesAdapter adapter;

    public ExercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();

        if(bundle != null)
            muscle = bundle.getInt("position", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_exercises, container, false);
        View rootView = inflater.inflate(R.layout.fragment_exercises, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.exercisesList);
        adapter = new ExercisesAdapter(getActivity(), getAllExercises());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    private ArrayList<ExercisesInformation> getAllExercises() {
        ArrayList<ExercisesInformation> exercisesInformationList = new ArrayList<>();

        Uri uri = ExerciseRecordsContentProvider.CONTENT_URI;
        String[] columns = {ExerciseRecordsTable.COLUMN_EXERCISE_NAME};

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                ExercisesInformation exerciseInformation = new ExercisesInformation();

                exerciseInformation.setTitle(cursor.getString(cursor.getColumnIndex(ExerciseRecordsTable.COLUMN_EXERCISE_NAME)));
                exercisesInformationList.add(exerciseInformation);
            }
            while (cursor.moveToNext());
        }
        return exercisesInformationList;
    }
}
