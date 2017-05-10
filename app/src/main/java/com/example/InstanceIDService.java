package com.example;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

import java.io.IOException;
import java.util.ArrayList;

public class InstanceIDService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        //LogUtils.setLog(this, "onTokenRefresh -> ");
        GcmSaver.setGCMDeviceId(this, "");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InstanceID instanceID = InstanceID.getInstance(InstanceIDService.this);
                    instanceID.deleteToken(App.SENDER_ID,
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE);

                    String token = instanceID.getToken(App.SENDER_ID,
                            GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                    GcmSaver.setGCMDeviceId(InstanceIDService.this, token);
                    registerDeviceInServer(token);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void registerDeviceInServer(String token) {
        //some work with server side
    }
}
