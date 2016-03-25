package com.fitness.dense.densefitness.personalRecords.personalRecords;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitness.dense.densefitness.DividerItemDecoration;
import com.fitness.dense.densefitness.Interfaces.PersonalRecordListener;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.ExerciseDatabaseHelper;
import com.fitness.dense.densefitness.database.ExerciseRecordsDatabaseHelper;
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

    private ExerciseRecordsDatabaseHelper exerciseDatabaseHelper;

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

        exerciseDatabaseHelper = new ExerciseRecordsDatabaseHelper(context);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.personalRecordList);
        adapter = new PersonalRecordsAdapter(getActivity(), getAllRecords());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);

        return rootView;

    }

    private ArrayList<PersonalRecordsRow> getAllRecords() {
        ArrayList<PersonalRecordsRow> personalRecordsRows = new ArrayList<>();

        SQLiteDatabase database = exerciseDatabaseHelper.getReadableDatabase();
        database.beginTransaction();

        try
        {
            personalRecordsRows = GetRecordData(database);
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            database.endTransaction();
            database.close();
        }
        exerciseDatabaseHelper.close();
        return personalRecordsRows;
    }

    private ArrayList<PersonalRecordsRow> GetRecordData(SQLiteDatabase db) {
        ArrayList<PersonalRecordsRow> personalRecordsRows = new ArrayList<>();
        String query = "select * from " + ExerciseRecordsTable.TABLE_EXERCISE_RECORDS;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                PersonalRecordsRow personalRecordsRow = new PersonalRecordsRow();

                personalRecordsRow.setExercise(cursor.getString(cursor.getColumnIndex(ExerciseRecordsTable.COLUMN_RECORD_EXERCISE_NAME)));
                personalRecordsRow.setDate(cursor.getString(cursor.getColumnIndex(ExerciseRecordsTable.COLUMN_PERSONAL_RECORD_DATE)));
                personalRecordsRow.setRecordResult(cursor.getString(cursor.getColumnIndex(ExerciseRecordsTable.COLUMN_RECORD_RESULT)));
                personalRecordsRows.add(personalRecordsRow);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.setTransactionSuccessful();

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
