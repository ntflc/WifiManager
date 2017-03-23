package com.ntflc.wifimanager;

/**
 * Created by ntflc on 2016/12/7.
 */

public interface WifiListener {

    public static final int WIFI_STATE_DISABLING    = 0;
    public static final int WIFI_STATE_DISABLED     = 1;
    public static final int WIFI_STATE_ENABLING     = 2;
    public static final int WIFI_STATE_ENABLED      = 3;

    public void stateChanged(int state);

}
