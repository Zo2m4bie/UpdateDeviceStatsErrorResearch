package com.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.net.Uri;

public class GcmSaver {

    public static void setGCMDeviceId(Context context, String strGCMDeviceId){
        setString(context, AppSettingsContentProvider.GCM_DEVICE_ID_CONTENT_URI, strGCMDeviceId);
    }

    protected static void setString(Context context, Uri uri, String strValue){
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppSettingsContentProvider.KEY_DATA, strValue);
        context.getContentResolver().update(uri, contentValues, null, null);
    }


    public static String getGCMDeviceId(Context context){
        boolean bIsNotEnd = true;
        String strGCMDeviceId = "";
        while (bIsNotEnd) {
            bIsNotEnd = false;
            Uri uriGCMDeviceId = AppSettingsContentProvider.GCM_DEVICE_ID_CONTENT_URI;
            Cursor cursorGCMDeviceId = context.getContentResolver().query(uriGCMDeviceId, null, null, null, null);
            if(cursorGCMDeviceId != null){
                try {
                    if(cursorGCMDeviceId.moveToFirst()) {
                        cursorGCMDeviceId.moveToFirst();
                        strGCMDeviceId = cursorGCMDeviceId.getString(0);
                    }
                } catch (CursorIndexOutOfBoundsException e){
                    bIsNotEnd = true;
                }finally {
                    cursorGCMDeviceId.close();
                }
            }else{
                bIsNotEnd = true;
            }
        }
        return strGCMDeviceId;
    }
}
