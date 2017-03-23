package com.ntflc.wifimanager;

/**
 * Created by ntflc on 2016/12/7.
 */

import java.util.HashSet;

public class WifiObserver implements WifiListener {

    private HashSet<WifiListener> listenerSet = new HashSet<WifiListener>();

    public void addWifiListener(WifiListener wifiListener) {
        if (!listenerSet.contains(wifiListener)) {
            listenerSet.add(wifiListener);
        }
    }

    public void removeWifiListener(WifiListener wifiListener) {
        if (listenerSet.contains(wifiListener)) {
            listenerSet.remove(wifiListener);
        }
    }

    public void clearWifiListener() {
        listenerSet.clear();
    }

    @Override
    public void stateChanged(int state) {
        for (WifiListener wifiListener : listenerSet) {
            wifiListener.stateChanged(state);
        }
    }

}
