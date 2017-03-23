package com.ntflc.wifimanager;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 启动WifiService
        WifiService.startService(this);
        // 定义开关wifi_switch
        Switch wifi_switch = (Switch) findViewById(R.id.switch_view);
        // 设置初始状态
        setWifiEnabled(true);
        // 监听switch状态
        wifi_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    setWifiEnabled(true);
                } else {
                    setWifiEnabled(false);
                }
            }
        });
        // 监听Wifi状态
        WifiService.addWifiListener(new WifiListener() {
            @Override
            public void stateChanged(int state) {
                // 定义开关wifi_switch
                Switch wifi_switch = (Switch) findViewById(R.id.switch_view);
                switch (state) {
                    case WifiListener.WIFI_STATE_DISABLING:
                        if (wifi_switch.isChecked())
                            wifi_switch.setChecked(false);
                        break;
                    case WifiListener.WIFI_STATE_DISABLED:
                        setWifiEnabled(true);
                        break;
                    case WifiListener.WIFI_STATE_ENABLING:
                        if (!wifi_switch.isChecked())
                            wifi_switch.setChecked(true);
                        break;
                    case WifiListener.WIFI_STATE_ENABLED:
                        if (!wifi_switch.isChecked())
                            wifi_switch.setChecked(true);
                        break;
                }
            }
        });
    }

    private void setWifiEnabled(boolean isTrue) {
        WifiManager mWm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mWm.setWifiEnabled(isTrue);
    }

}
