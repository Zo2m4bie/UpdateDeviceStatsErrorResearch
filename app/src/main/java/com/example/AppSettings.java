package com.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSettings {

    private final static String PREFERENCES_STRING_KEY_GCM_DEVICE_ID = "PREFERENCES_STRING_KEY_GCM_DEVICE_ID";

    private SharedPreferences m_SharedPreferences = null;

    public AppSettings(Context context) {
        super();
        m_SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setGCMDeviceId(String strDevicegId){
        SharedPreferences.Editor editor = m_SharedPreferences.edit();
        editor.putString(PREFERENCES_STRING_KEY_GCM_DEVICE_ID, strDevicegId);
        editor.commit();
    }

    public String getGCMDeviceId(){
        return m_SharedPreferences.getString(PREFERENCES_STRING_KEY_GCM_DEVICE_ID, "");
    }
}
