package com.example.wto.myapplication.connection;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by WTO on 2017/8/8 0008.
 *
 */
public class ConnectService extends Service
{
    private static final String TAG = "ConnectServer";
//    private static final String host = "192.168.1.30";
    private static final String host = "192.168.1.29";
    private static final Integer port = 8000;

    private ConnectBingder binder = new ConnectBingder();
    public class ConnectBingder extends Binder
    {
        public ConnectService getService()
        {
            return ConnectService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
