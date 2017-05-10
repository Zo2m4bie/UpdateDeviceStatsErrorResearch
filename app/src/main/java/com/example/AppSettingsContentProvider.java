package com.example;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

public class AppSettingsContentProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.AppSettingsContentProvider";

    private static final String GCM_DEVICE_ID = "gcm_device_id";

    public static final Uri GCM_DEVICE_ID_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + GCM_DEVICE_ID);


    public static final String KEY_DATA = "KEY_DATA";

    private static final int URI_GCM_DEVICE_ID = 7;


    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, GCM_DEVICE_ID, URI_GCM_DEVICE_ID);
    }


    private AppSettings mSettings = null;

    @Override
    public boolean onCreate() {
        mSettings = new AppSettings(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Object returnData = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_GCM_DEVICE_ID:
                returnData = mSettings.getGCMDeviceId();
                break;
        }
        String[] arrColumnNames = {"Data"};
        Object[] arrValue = {returnData};
        MatrixCursor matrixCursor = new MatrixCursor(arrColumnNames);
        matrixCursor.addRow(arrValue);
        return matrixCursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (URI_MATCHER.match(uri)) {
            case URI_GCM_DEVICE_ID:
                String strDeviceId = values.getAsString(KEY_DATA);
                mSettings.setGCMDeviceId(strDeviceId);
                break;
        }
        return 0;
    }
}
