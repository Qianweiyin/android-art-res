package com.qwy.wifi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class WifiUtil {
    private ConnectivityManager connectivityManager;
    private WifiManager mWifiManager;

    private ConnectivityManager.NetworkCallback networkCallback;

    public void changeToWifi(String wifiName, String wifiPwd) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            changeToWifiAfterQ(wifiName, wifiPwd);
        } else {
            changeToWifiBeforeQ(wifiName, wifiPwd);
        }
    }

    /**
     * 切换到指定wifi Android版本小于10
     *
     * @param wifiName 指定的wifi名字
     * @param wifiPwd  wifi密码，如果已经保存过密码，可以传入null
     * @return
     */
    public void changeToWifiBeforeQ(String wifiName, String wifiPwd) {
        if (mWifiManager == null) {
            Log.i(TAG, " ***** init first ***** ");
            return;
        }

        String mWifiName = "\"" + wifiName + "\"";

        /**
         * 判断定位权限
         */
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        //获取wifi列表
        List wifiList = mWifiManager.getConfiguredNetworks();
        boolean bFindInList = false;
        for (int i = 0; i < wifiList.size(); ++i) {
            WifiConfiguration wifiInfo0 = (WifiConfiguration) wifiList.get(i);

            // 先找到对应的wifi
            if (mWifiName.equals(wifiInfo0.SSID) || wifiName.equals(wifiInfo0.SSID)) {
                // 1、 先启动，可能已经输入过密码，可以直接启动
                Log.i(TAG, " set wifi 1 = " + wifiInfo0.SSID);
                doChange2Wifi(wifiInfo0.networkId);
                return;
            }
        }

        // 2、如果wifi还没有输入过密码，尝试输入密码，启动wifi
        WifiConfiguration wifiNewConfiguration = createWifiInfo(wifiName, wifiPwd);//使用wpa2的wifi加密方式
        int newNetworkId = mWifiManager.addNetwork(wifiNewConfiguration);
        if (newNetworkId == -1) {
            Log.e(TAG, "操作失败,需要您到手机wifi列表中取消对设备连接的保存");
        } else {
            doChange2Wifi(newNetworkId);
        }
    }


    /**
     * 切换到指定wifi Android版本大于等于10
     *
     * @param wifiName 指定的wifi名字
     * @param wifiPwd  wifi密码，如果已经保存过密码，可以传入null
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void changeToWifiAfterQ(String wifiName, String wifiPwd) {
        if (mWifiManager == null || connectivityManager == null) {
            Log.i(TAG, " ***** init first ***** ");
            return;
        }

        /**
         * 判断定位权限
         */
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CHANGE_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        NetworkSpecifier specifier = new WifiNetworkSpecifier.Builder()
                .setSsid(wifiName)
                .setWpa2Passphrase(wifiPwd)
                .build();

        NetworkRequest request =
                new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .removeCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .setNetworkSpecifier(specifier)
                        .build();

        networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                Log.i(TAG, "onAvailable 切换到指定wifi成功");
                connectivityManager.unregisterNetworkCallback(networkCallback);
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                Log.i(TAG, "onUnavailable 切换到指定wifi失败");
                connectivityManager.unregisterNetworkCallback(networkCallback);
            }
        };
        connectivityManager.requestNetwork(request, networkCallback);

    }

    private boolean doChange2Wifi(int newNetworkId) {
        // 如果wifi权限没打开（1、先打开wifi，2，使用指定的wifi
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }

        boolean enableNetwork = mWifiManager.enableNetwork(newNetworkId, true);
        if (!enableNetwork) {
            Log.e(TAG, "切换到指定wifi失败");
            return false;
        } else {
            Log.e(TAG, "切换到指定wifi成功");
            return true;
        }
    }

    /**
     * 创建 WifiConfiguration，这里创建的是wpa2加密方式的wifi
     *
     * @param ssid     wifi账号
     * @param password wifi密码
     * @return
     */
    private WifiConfiguration createWifiInfo(String ssid, String password) {
        WifiConfiguration config = new WifiConfiguration();
        config.allowedAuthAlgorithms.clear();
        config.allowedGroupCiphers.clear();
        config.allowedKeyManagement.clear();
        config.allowedPairwiseCiphers.clear();
        config.allowedProtocols.clear();
        config.SSID = "\"" + ssid + "\"";
        config.preSharedKey = "\"" + password + "\"";
        config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        config.status = WifiConfiguration.Status.ENABLED;
        return config;
    }

    public static final String TAG = "WifiUtil";

    private static final WifiUtil ourInstance = new WifiUtil();
    private static Context mContext;

    public static WifiUtil getIns() {
        return ourInstance;
    }

    public void init(Context context) {
        mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            connectivityManager = (ConnectivityManager)
                    mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
    }

}