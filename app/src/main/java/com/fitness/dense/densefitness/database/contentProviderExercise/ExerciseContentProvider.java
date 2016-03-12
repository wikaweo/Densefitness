package com.fitness.dense.densefitness.database.contentProviderExercise;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.fitness.dense.densefitness.database.ExerciseDatabaseHelper;
import com.fitness.dense.densefitness.database.ExerciseTable;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Fredrik on 2016-02-13.
 */
public class ExerciseContentProvider extends ContentProvider {

    private ExerciseDatabaseHelper database;

    // used for the UriMacher
    private static final int EXERCISE = 10;
    private static final int EXERCISE_ID = 20;

    private static final String AUTHORITY = "com.fitness.dense.densefitness.database.contentProviderExercise";

    private static final String BASE_PATH = "exercise";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/exercise";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/exercise";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, EXERCISE);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", EXERCISE_ID);
    }

    @Override
    public boolean onCreate() {
        database = new ExerciseDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(ExerciseTable.TABLE_EXERCISE);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case EXERCISE:
                break;
            case EXERCISE_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(ExerciseTable.COLUMN_ID + "="
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
            case EXERCISE:
                id = sqlDB.insert(ExerciseTable.TABLE_EXERCISE, null, values);
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
            case EXERCISE:
                rowsDeleted = sqlDB.delete(ExerciseTable.TABLE_EXERCISE, selection,
                        selectionArgs);
                break;
            case EXERCISE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsDeleted = sqlDB.delete(ExerciseTable.TABLE_EXERCISE,
                            ExerciseTable.COLUMN_ID + "=" + id, null);
                }
                else
                {
                    rowsDeleted = sqlDB.delete(ExerciseTable.TABLE_EXERCISE,
                            ExerciseTable.COLUMN_ID + "=" + id
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
            case EXERCISE:
                rowsUpdated = sqlDB.update(ExerciseTable.TABLE_EXERCISE,
                        values,
                        selection,
                        selectionArgs);
                break;
            case EXERCISE_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsUpdated = sqlDB.update(ExerciseTable.TABLE_EXERCISE,
                            values,
                            ExerciseTable.COLUMN_ID + "=" + id, null);
                }
                else
                {
                    rowsUpdated = sqlDB.update(ExerciseTable.TABLE_EXERCISE,
                            values,
                            ExerciseTable.COLUMN_ID + "=" + id
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
        String[] available = { ExerciseTable.COLUMN_ID,
                ExerciseTable.COLUMN_EXERCISE_NAME};
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
