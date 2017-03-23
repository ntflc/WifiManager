package com.ntflc.wifimanager;

/**
 * Created by ntflc on 2016/12/7.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class WifiService extends Service {

    public static String ACTION_START_SERVICE = "action_start_service";
    public static String ACTION_STOP_SERVICE = "action_stop_service";
    private static WifiObserver wifiObserver = new WifiObserver();

    public static void startService(Context context) {
        Intent intent = new Intent(context, WifiService.class);
        intent.setAction(ACTION_START_SERVICE);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent(context, WifiService.class);
        intent.setAction(ACTION_STOP_SERVICE);
        context.startService(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        if (intent.getAction().equals(ACTION_START_SERVICE)) {
            // startService();
        } else if (intent.getAction().equals(ACTION_STOP_SERVICE)) {
            stopSelf();
        }
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        registerReceiver(wifiReceiver, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(wifiReceiver);
        wifiObserver.clearWifiListener();
        super.onDestroy();
    }

    public static void addWifiListener(WifiListener wifiListener) {
        wifiObserver.addWifiListener(wifiListener);
    }

    public static void removeWifiListener(WifiListener wifiListener) {
        wifiObserver.removeWifiListener(wifiListener);
    }

    private BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
                int state = intent.getIntExtra("wifi_state", 0);
                wifiObserver.stateChanged(state);
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
