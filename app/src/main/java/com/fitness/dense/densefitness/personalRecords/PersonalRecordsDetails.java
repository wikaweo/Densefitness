package com.fitness.dense.densefitness.personalRecords;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.ExerciseRecordsTable;
import com.fitness.dense.densefitness.database.contentProviderExerciseRecords.ExerciseRecordsContentProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PersonalRecordsDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mTypeChoices;
    private EditText mExercise;
    private TextView mDate;
    private EditText mTypeOfRecord;
    private EditText mWeight;
    private EditText mNotes;
    private TextInputLayout mWeightWrapper;
    private TextInputLayout mTypeOfRecordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_records_details);

        Intent intent = getIntent();
        Bundle values = intent.getExtras();
        String exercise = null;
        if(values != null)
            exercise = values.getString("exercise");

        InitiateInputFields();

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.typeChoices, android.R.layout.simple_spinner_item);
        mTypeChoices.setAdapter(adapter);
        mTypeChoices.setOnItemSelectedListener(this);

        if(exercise != null)
            mExercise.setText(exercise);

        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    private void InitiateInputFields() {
        mExercise = (EditText) findViewById(R.id.etExerciseForPersonalRecords);
        mTypeChoices = (Spinner) findViewById(R.id.spTypeOfRecordChoice);
        mTypeOfRecord = (EditText) findViewById(R.id.etTypeOfRecord);
        mWeight = (EditText) findViewById(R.id.etPersonalRecordsWeight);
        mNotes = (EditText) findViewById(R.id.etNotes);
        mWeightWrapper = (TextInputLayout) findViewById(R.id.personalRecordsWeightWrapper);
        mTypeOfRecordWrapper = (TextInputLayout) findViewById(R.id.typeOfRecordWrapper);
        mDate = (TextView) findViewById(R.id.edPersonalRecordDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_records_details, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView selectedText = (TextView) view;

        SetVisibility(selectedText);
        SetHint(selectedText);
    }


    private void SetVisibility(TextView selectedText) {
        if(selectedText.getText().equals("Weight"))
            mWeightWrapper.setVisibility(View.VISIBLE);
        else
            mWeightWrapper.setVisibility(View.GONE);
    }

    private void SetHint(TextView selectedText) {
        if(selectedText.getText().equals("Weight"))
            mTypeOfRecordWrapper.setHint("Reps");

        if(selectedText.getText().equals("Reps"))
            mTypeOfRecordWrapper.setHint("Reps");

        if(selectedText.getText().equals("Distance"))
            mTypeOfRecordWrapper.setHint("Distance");

        if(selectedText.getText().equals("Time"))
            mTypeOfRecordWrapper.setHint("Time");
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onSaveClick(MenuItem item)
    {
        String exercise = mExercise.getText().toString();
        String date = mDate.getText().toString();
        String typeChoise = mTypeChoices.getSelectedItem().toString();
        String typeRecord = mTypeOfRecord.getText().toString();
        String weight = mWeight.getText().toString();
        //String notes = mNotes.getText().toString();

        //boolean isInputValid = validateInput(title, date);

        //if (isInputValid) {
            ContentValues values = setContentValues(exercise, date, typeChoise, typeRecord, weight);
            getContentResolver().insert(ExerciseRecordsContentProvider.CONTENT_URI, values);

            Toast.makeText(this, "Record added successfully", Toast.LENGTH_SHORT).show();
            ClearTextBoxes();
        //}
    }

    @NonNull
    private ContentValues setContentValues(String exercise, String date, String typeChoise, String typeRecord, String weight) {
        ContentValues values = new ContentValues();
        values.put(ExerciseRecordsTable.COLUMN_FK_EXERCISE_ID, exercise);
        values.put(ExerciseRecordsTable.COLUMN_PERSONAL_RECORD_DATE, date);
        values.put(ExerciseRecordsTable.COLUMN_RECORD_TYPE, typeChoise);
        values.put(ExerciseRecordsTable.COLUMN_RECORD_RESULT, typeRecord);
        values.put(ExerciseRecordsTable.COLUMN_RECORD_WEIGHT, weight);
        return values;
    }

    public void onCancelClick(MenuItem item)
    {
        ClearTextBoxes();
    }

    private void ClearTextBoxes() {
        mTypeOfRecord.setText("");
        mWeight.setText("");
        mNotes.setText("");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(calendar.getTime());

            TextView datewate = (TextView)getActivity().findViewById(R.id.edPersonalRecordDate);
            datewate.setText(formattedDate);
        }
    }
}
