package com.example.myapplication.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class CommonUtils {
    static final String TAG = "CommonUtils";

    public static boolean isNull(String string) {
        if(string == null || string.equals("") || string.trim().equals("")) {
            return true;
        }
        return false;
    }

    public static String isNull(String string,String defaultValue) {
        if(string == null || string.equals("") || string.trim().equals("")) {
            return defaultValue;
        }
        return string;
    }

    public static String getMacAddress(Context context) {
        String macAddress;

        if(android.os.Build.VERSION.SDK_INT > 24)
            macAddress = getMac();
        else{
            macAddress = getMac("24ver");
        }

        Log.e(TAG, "macAddress 0 : " + macAddress);
        if(TextUtils.isEmpty(macAddress) == false) {
            return macAddress;
        }
        macAddress = getMacAddressForWifi(context);
        Log.e(TAG, "macAddress 1 : " + macAddress);
        if(TextUtils.isEmpty(macAddress) == false) {
            return macAddress;
        }
        macAddress = getMacForAndroid6();
        Log.e(TAG, "macAddress 2 : " + macAddress);
        if(TextUtils.isEmpty(macAddress) == false) {
            return macAddress;
        }
        return macAddress;
    }

    /**
     * API 22, API 28 확인 완료.
     * @return
     */
    public static String getMac() {
        final String interfaceName = "wlan0";
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac==null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx=0; idx<mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
                return buf.toString();
            }
        } catch (Exception ex) { } // for now eat exceptions

        return "";
    }

    private static String getMac(String str) {
        String macSerial = "";

        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            String line;
            while ((line = input.readLine()) != null) {
                macSerial += line.trim();
            }

            input.close();
        } catch (IOException e) {
        }

        if (!isNull(macSerial)){
            macSerial=macSerial.toUpperCase();
        }

        return macSerial;
    }

    private static String getMacForAndroid6() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString().toUpperCase();
            }
        } catch (Exception ex) {
        }
        return "";
//        return "02:00:00:00:00:00";
    }

    @Deprecated
    private static String getMacAddressForWifi(Context context) {
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }
}
