package com.fitness.dense.densefitness.workouts;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.MainActivity;
import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.bodymass.ErrorMessageDialog;
import com.fitness.dense.densefitness.database.WorkoutTable;
import com.fitness.dense.densefitness.database.contentProviderWorkout.WorkoutContentProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WorkoutDetails extends AppCompatActivity {

    private EditText mTitle;
    private TextView mDate;
    private EditText mDescription;
    private EditText mTime;
    private EditText mRounds;
    private EditText mReps;
    private EditText mWeight;

    private Uri workoutUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        InitiateInputFields();
        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    public void onSaveClick(MenuItem item)
    {
        String title = mTitle.getText().toString();
        String date = mDate.getText().toString();
        String description = mDescription.getText().toString();
        String time = mTime.getText().toString();
        String rounds = mRounds.getText().toString();
        String reps = mReps.getText().toString();
        String weight = mWeight.getText().toString();

        boolean isInputValid = validateInput(title, date);

        if (isInputValid) {
            ContentValues values = setContentValues(title, date,description, time, rounds, reps, weight);
            workoutUri = getContentResolver().insert(WorkoutContentProvider.CONTENT_URI, values);

            Toast.makeText(this, "Workout added successfully", Toast.LENGTH_SHORT).show();
            ClearTextBoxes();
        }
    }

    @NonNull
    private ContentValues setContentValues(String title, String date, String description, String time, String rounds, String reps, String weight) {
        ContentValues values = new ContentValues();
        values.put(WorkoutTable.COLUMN_WORKOUT_NAME, title);
        values.put(WorkoutTable.COLUMN_WORKOUT_DATE, date);
        values.put(WorkoutTable.COLUMN_DESCRIPTION, description);
        values.put(WorkoutTable.COLUMN_TIME, time);
        values.put(WorkoutTable.COLUMN_ROUNDS, rounds);
        values.put(WorkoutTable.COLUMN_WEIGHT, reps);
        values.put(WorkoutTable.COLUMN_REPS, weight);
        return values;
    }

    private void ClearTextBoxes() {
        mTitle.setText("");
        mDate.setText("");
        mDescription.setText("");
        mTime.setText("");
        mRounds.setText("");
        mReps.setText("");
        mWeight.setText("");
    }

    public void onCancelClick(MenuItem item)
    {
        ClearTextBoxes();
    }

    private void InitiateInputFields() {
        mTitle = (EditText) findViewById(R.id.etWorkoutTitle);
        mDate = (TextView) findViewById(R.id.edWorkoutDate);
        mDescription = (EditText) findViewById(R.id.etWorkoutDescription);
        mTime = (EditText) findViewById(R.id.etWorkoutTime);
        mRounds = (EditText) findViewById(R.id.etWorkoutRounds);
        mReps = (EditText) findViewById(R.id.etWorkoutReps);
        mWeight = (EditText) findViewById(R.id.etWorkoutWeight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_details, menu);
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
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public boolean validateInput(String title, String date){
        if(title.isEmpty() || date.isEmpty())
        {
            showErrorDialog();
            return false;
        }
        else
        {
            return true;
        }
    }

    private void showErrorDialog() {
        ErrorMessageDialog errorMessageDialog = new ErrorMessageDialog();
        errorMessageDialog.show(getSupportFragmentManager(), "Error Message");
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

            TextView datewate = (TextView)getActivity().findViewById(R.id.edWorkoutDate);
            datewate.setText(formattedDate);
        }
    }
}
