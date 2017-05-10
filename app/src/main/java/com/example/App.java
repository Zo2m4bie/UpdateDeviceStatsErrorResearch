package com.example;

import android.app.Application;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


public class App extends Application {

    public static final String SENDER_ID = "";

    @Override
    public void onCreate() {
        super.onCreate();
        setNewGCMToken();
    }


    public void setNewGCMToken(){
        String strGCMDeviceId = GcmSaver.getGCMDeviceId(this);

        if (strGCMDeviceId.isEmpty()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String token = InstanceID.getInstance(App.this).getToken(SENDER_ID,
                                GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                        GcmSaver.setGCMDeviceId(App.this,token);
                        //LogUtils.setLog(Mail2WorldApplication.this, "Mail2WorldApplication -> token -> " + token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
