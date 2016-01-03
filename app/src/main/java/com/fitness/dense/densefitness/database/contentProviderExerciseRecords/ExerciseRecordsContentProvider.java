package com.fitness.dense.densefitness.database.contentProviderExerciseRecords;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.fitness.dense.densefitness.database.ExerciseRecordsDatabaseHelper;
import com.fitness.dense.densefitness.database.ExerciseRecordsTable;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Fredrik on 2015-12-30.
 */
public class ExerciseRecordsContentProvider extends ContentProvider {

    private ExerciseRecordsDatabaseHelper database;

    // used for the UriMacher
    private static final int EXERCISERECORDS = 10;
    private static final int EXERCISE_RECORDS_ID = 20;

    private static final String AUTHORITY = "com.fitness.dense.densefitness.database.contentProviderExerciseRecords";

    private static final String BASE_PATH = "exerciserecords";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/exerciserecords";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/exerciserecords";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, EXERCISERECORDS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EXERCISE_RECORDS_ID);
    }

    @Override
    public boolean onCreate() {
        database = new ExerciseRecordsDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case EXERCISERECORDS:
                break;
            case EXERCISE_RECORDS_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(ExerciseRecordsTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) { return null; }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case EXERCISERECORDS:
                id = sqlDB.insert(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case EXERCISERECORDS:
                rowsDeleted = sqlDB.delete(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS, selection,
                        selectionArgs);
                break;
            case EXERCISE_RECORDS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsDeleted = sqlDB.delete(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS,
                            ExerciseRecordsTable.COLUMN_ID + "=" + id, null);
                }
                else
                {
                    rowsDeleted = sqlDB.delete(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS,
                            ExerciseRecordsTable.COLUMN_ID + "=" + id
                                    + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case EXERCISERECORDS:
                rowsUpdated = sqlDB.update(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS,
                        values,
                        selection,
                        selectionArgs);
                break;
            case EXERCISE_RECORDS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsUpdated = sqlDB.update(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS,
                            values,
                            ExerciseRecordsTable.COLUMN_ID + "=" + id, null);
                }
                else
                {
                    rowsUpdated = sqlDB.update(ExerciseRecordsTable.TABLE_EXERCISE_RECORDS,
                            values,
                            ExerciseRecordsTable.COLUMN_ID + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { ExerciseRecordsTable.COLUMN_ID,
                ExerciseRecordsTable.COLUMN_EXERCISE_NAME,
                ExerciseRecordsTable.COLUMN_EXERCISE_DATE,
                ExerciseRecordsTable.COLUMN_RECORD_TYPE,
                ExerciseRecordsTable.COLUMN_RECORD_RESULT };
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
