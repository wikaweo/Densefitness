package com.fitness.dense.densefitness.personalRecords.personalRecords;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.Interfaces.PersonalRecordListener;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.ExerciseRecordsTable;
import com.fitness.dense.densefitness.database.contentProviderExerciseRecords.ExerciseRecordsContentProvider;
import com.fitness.dense.densefitness.personalRecords.Exercises;

import java.util.ArrayList;

/**
 * Created by Fredrik on 2015-09-20.
 */
public class PersonalRecords extends DialogFragment implements PersonalRecordListener {
    public static final String ARG_OBJECT = "object";

    private RecyclerView mRecyclerView;
    private PersonalRecordsAdapter adapter;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.personal_records_fragment, container, false);

        context = inflater.getContext();
        /*Bundle args = getArguments();
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                Integer.toString(args.getInt(WorkoutsFragment.ARG_OBJECT)));*/

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.personalRecordList);
        adapter = new PersonalRecordsAdapter(getActivity(), getAllMuscles());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;

    }

    private ArrayList<PersonalRecordsRow> getAllMuscles() {
        ArrayList<PersonalRecordsRow> personalRecordsRows = new ArrayList<>();

        Uri uri = ExerciseRecordsContentProvider.CONTENT_URI;
        String[] columns = {ExerciseRecordsTable.COLUMN_PERSONAL_RECORD_DATE};

        Cursor cursor = getActivity().getContentResolver().query(uri, columns, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PersonalRecordsRow personalRecordsRow = new PersonalRecordsRow();

                personalRecordsRow.setTitle(cursor.getString(cursor.getColumnIndex(ExerciseRecordsTable.COLUMN_PERSONAL_RECORD_DATE)));
                personalRecordsRows.add(personalRecordsRow);
            }
            while (cursor.moveToNext());
        }
        return personalRecordsRows;
    }

    @Override
    public void onNewPersonalRecordClick() {
        //MainActivity mainActivity = (MainActivity) context;
        //mainActivity.switchContent();

        Intent intent = new Intent(getActivity(), Exercises.class);
        startActivity(intent);
    }
}
