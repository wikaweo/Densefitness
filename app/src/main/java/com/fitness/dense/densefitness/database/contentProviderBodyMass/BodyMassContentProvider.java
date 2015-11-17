package com.fitness.dense.densefitness.database.contentProviderBodyMass;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.fitness.dense.densefitness.database.BodyMassDatabaseHelper;
import com.fitness.dense.densefitness.database.BodyMassTable;

/**
 * Created by Fredrik on 2015-09-21.
 */
public class BodyMassContentProvider extends ContentProvider {

    // database
    private BodyMassDatabaseHelper database;

    // used for the UriMacher
    private static final int BODYMASS = 10;
    private static final int BODYMASS_ID = 20;

    private static final String AUTHORITY = "com.fitness.dense.densefitness.database.contentProviderBodyMass";

    private static final String BASE_PATH = "bodymass";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/bodymass";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/bodymass";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, BODYMASS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", BODYMASS_ID);
    }

    @Override
    public boolean onCreate() {
        database = new BodyMassDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(BodyMassTable.TABLE_BODY_MASS);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case BODYMASS:
                break;
            case BODYMASS_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(BodyMassTable.COLUMN_ID + "="
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
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        switch (uriType) {
            case BODYMASS:
                id = sqlDB.insert(BodyMassTable.TABLE_BODY_MASS, null, values);
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
            case BODYMASS:
                rowsDeleted = sqlDB.delete(BodyMassTable.TABLE_BODY_MASS, selection,
                        selectionArgs);
                break;
            case BODYMASS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsDeleted = sqlDB.delete(BodyMassTable.TABLE_BODY_MASS,
                            BodyMassTable.COLUMN_ID + "=" + id, null);
                }
                else if(selection.contains(","))
                {
                    rowsDeleted = sqlDB.delete(BodyMassTable.TABLE_BODY_MASS,
                            BodyMassTable.COLUMN_ID + " in " + "(" + selection + ")", null);
                }
                else
                {
                    rowsDeleted = sqlDB.delete(BodyMassTable.TABLE_BODY_MASS,
                            BodyMassTable.COLUMN_ID + "=" + id
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
            case BODYMASS:
                rowsUpdated = sqlDB.update(BodyMassTable.TABLE_BODY_MASS,
                        values,
                        selection,
                        selectionArgs);
                break;
            case BODYMASS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection))
                {
                    rowsUpdated = sqlDB.update(BodyMassTable.TABLE_BODY_MASS,
                            values,
                            BodyMassTable.COLUMN_ID + "=" + id, null);
                }
                else
                {
                    rowsUpdated = sqlDB.update(BodyMassTable.TABLE_BODY_MASS,
                            values,
                            BodyMassTable.COLUMN_ID + "=" + id
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
        String[] available = { BodyMassTable.COLUMN_DATE,
                BodyMassTable.COLUMN_WEIGHT, BodyMassTable.COLUMN_FAT,
                BodyMassTable.COLUMN_MUSCLE_MASS, BodyMassTable.COLUMN_ID };
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
