package com.fitness.dense.densefitness.bodymass;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.dense.densefitness.R;
import com.fitness.dense.densefitness.database.BodyMassDatabaseHelper;
import com.fitness.dense.densefitness.database.BodyMassTable;
import com.fitness.dense.densefitness.database.contentProviderBodyMass.BodyMassContentProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Fredrik on 2015-09-20.
 */
public class BodyMassFragment extends DialogFragment implements ActionMode.Callback {
    public static final String ARG_OBJECT = "object";

    private TextView mDate;
    private EditText mWeight;
    private EditText mFat;
    private EditText mMuscleMass;
    private Button buttonSave;

    private ActionMode.Callback mLastCallback;
    ActionMode actionMode;
    private Context context;
    private Uri bodyMassUri;

    private TableLayout tableLayout;
    private BodyMassDatabaseHelper bodyMassDatabaseHelper;

    private int[] checkedItems;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.body_mass_fragment, container, false);
        /*Bundle args = getArguments();
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                Integer.toString(args.getInt(WorkoutsFragment.ARG_OBJECT)));*/
        context = inflater.getContext();

        bodyMassDatabaseHelper = new BodyMassDatabaseHelper(context);
        tableLayout = (TableLayout)rootView.findViewById(R.id.tlBodyMassHistory);

        UpdateTable();

        SetTextBoxes(rootView);

        //Bundle args = getArguments();

        // check from the saved Instance
        bodyMassUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
                .getParcelable(BodyMassContentProvider.CONTENT_ITEM_TYPE);

        buttonSave = (Button) rootView.findViewById(R.id.btnSave);
        onSaveClick();

        mDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(fragmentManager, "datePicker");
            }
        });

        setHasOptionsMenu(true);

        //BodyMassTable.TABLE_BODY_MASS
        // Defines a string to contain the selection clause
        String mSelectionClause = null;

// Initializes an array to contain selection arguments
        String[] mSelectionArgs = {""};

        //bodyMassUri = BodyMassContentProvider.CONTENT_URI;
        //int numberOfRows = count(bodyMassUri, mSelectionClause, mSelectionArgs);

        //checkedItems = new int[cursor.getCount()];

        return rootView;
    }

    public int count(Uri uri,String selection,String[] selectionArgs) {
        Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{"count(*)"},
                selection, selectionArgs, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return 0;
        } else {
            cursor.moveToFirst();
            int result = cursor.getInt(0);
            cursor.close();
            return result;
        }
    }

    private void UpdateTable() {
        TableRow rowHeader = setTableRowHeader();
        tableLayout.addView(rowHeader);

        SQLiteDatabase db = bodyMassDatabaseHelper.getReadableDatabase();
        db.beginTransaction();

        try
        {
            GetBodyMassData(tableLayout, db);
        }
        catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.endTransaction();
            db.close();
        }
    }

    private void onSaveClick() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = mDate.getText().toString();
                String weight = mWeight.getText().toString();
                String fat = mFat.getText().toString();
                String muscleMass = mMuscleMass.getText().toString();

                boolean isInputValid = validateInput(date.toString(), weight.toString(), fat.toString(), muscleMass.toString());

                if (isInputValid) {
                    ContentValues values = setContentValues(date, weight, fat, muscleMass);

                    if (bodyMassUri == null) {
                        bodyMassUri = getContext().getContentResolver().insert(BodyMassContentProvider.CONTENT_URI, values);
                    } else {
                        getContext().getContentResolver().update(bodyMassUri, values, null, null);
                    }

                    tableLayout.removeAllViews();

                    UpdateTable();

                    Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
                    ClearTextBoxes();
                }
            }
        });
    }

    private void ClearTextBoxes() {
        mDate.setText("");
        mWeight.setText("");
        mFat.setText("");
        mMuscleMass.setText("");
    }

    private void SetTextBoxes(View rootView) {
        mDate = (TextView) rootView.findViewById(R.id.edDate);
        mWeight = (EditText) rootView.findViewById(R.id.edWeight);
        mFat = (EditText) rootView.findViewById(R.id.edFat);
        mMuscleMass = (EditText) rootView.findViewById(R.id.edMuscleMass);

        mDate.setInputType(InputType.TYPE_NULL);
        mDate.requestFocus();
    }

    private void GetBodyMassData(TableLayout tableLayout, SQLiteDatabase db) {
        String query = "select * from body_mass";
        Cursor cursor = db.rawQuery(query, null);
        int counter = 0;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext())
            {
                // Read columns data
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String weight = cursor.getString(cursor.getColumnIndex("weight"));
                String fat = cursor.getString(cursor.getColumnIndex("fat"));
                String muscleMass = cursor.getString(cursor.getColumnIndex("muscle_mass"));

                TableRow row = addTableRows(date, weight, fat, muscleMass);
                row.setTag(counter);
                tableLayout.addView(row);
                counter++;
            }
            cursor.close();
        }
        db.setTransactionSuccessful();
    }

    @NonNull
    private TableRow addTableRows(String date, String weight, String fat, String muscleMass) {
        TableRow row = new TableRow(context);
        row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        row.setBackgroundResource(R.drawable.row_border);

        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox clickedCheckBox = (CheckBox) view;
                if (clickedCheckBox.isChecked()) {
                    TableRow row = (TableRow)view.getParent();
                    int rowId = (int) row.getTag();
                    getActivity().startActionMode(BodyMassFragment.this);
                }

            }
        });

        row.addView(checkBox);

        String[] colText = {date, weight, fat, muscleMass};

        for (String text : colText) {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setPadding(5, 5, 5, 5);
            textView.setText(text);
            row.addView(textView);
        }
        return row;
    }

    @NonNull
    private TableRow setTableRowHeader() {
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#009688"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        rowHeader.setBackgroundResource(R.drawable.row_border);

        String[] headerText={" ", "Date", "Weight(kg)", "Fat(%)", "Muscles(%)"};
        for(String c:headerText) {
            TextView textView = new TextView(getActivity());
            textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(15);
            textView.setPadding(5, 5, 5, 5);
            textView.setText(c);
            rowHeader.addView(textView);
        }
        return rowHeader;
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

    @NonNull
    private ContentValues setContentValues(String date, String weight, String fat, String muscleMass) {
        ContentValues values = new ContentValues();
        values.put(BodyMassTable.COLUMN_DATE, date);
        values.put(BodyMassTable.COLUMN_WEIGHT, weight);
        values.put(BodyMassTable.COLUMN_FAT, fat);
        values.put(BodyMassTable.COLUMN_MUSCLE_MASS, muscleMass);
        return values;
    }

    public boolean validateInput(String date, String weight, String fat, String muscleMass){
        if((date.isEmpty() && date != null) || (weight.isEmpty() && weight != null) || (fat.isEmpty() && fat != null) || (muscleMass.isEmpty() && muscleMass != null))
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
        errorMessageDialog.show(getFragmentManager(), "Error Message");
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
        Toast.makeText(getActivity(), "CAB for Radiogroup!",
                Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(calendar.getTime());

            TextView datewate = (TextView)getActivity().findViewById(R.id.edDate);
            datewate.setText(formattedDate);
        }
    }
}
