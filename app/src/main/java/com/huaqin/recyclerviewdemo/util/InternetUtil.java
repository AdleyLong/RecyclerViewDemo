package com.huaqin.recyclerviewdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by ubuntu on 17-4-13.
 */

public class InternetUtil {
    private static final String TAG = InternetUtil.class.getSimpleName();
    //没有网络连接
    public static final int NETWORN_NONE = 0;
    //wifi连接
    public static final int NETWORN_WIFI = 1;
    //手机网络数据连接类型
    public static final int NETWORN_2G = 2;
    public static final int NETWORN_3G = 3;
    public static final int NETWORN_4G = 4;
    public static final int NETWORN_MOBILE = 5;

    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 获取当前网络连接类型
     *
     * @param context
     * @return
     */
    public static int getNetworkState(Context context) {
        //获取系统的网络服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);

        //如果当前没有网络
        if (null == connManager)
            return NETWORN_NONE;

        //获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORN_NONE;
        }

        // 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORN_WIFI;
                }
        }

        // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
                        //如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            return NETWORN_2G;
                        //如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            return NETWORN_3G;
                        //如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return NETWORN_4G;
                        default:
                            //中国移动 联通 电信 三种3G制式
                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName
                                    .equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase
                                    ("CDMA2000")) {
                                return NETWORN_3G;
                            } else {
                                return NETWORN_MOBILE;
                            }
                    }
                }
        }
        return NETWORN_NONE;
    }


    /**
     * 判断当前是否为gprs网络
     *
     * @author gut
     */
    public static boolean isGPRS(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 获取网络类型
     *
     * @return
     */
    public static String getNetWorkType(Context context) {
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        String type = "";
        if (info != null) {
            switch (info.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    type = "WIFI";
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    switch (info.getSubtype()) {
                        case TelephonyManager.NETWORK_TYPE_LTE:  // 4G
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                            type = "4G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_UMTS: // 3G
                        case TelephonyManager.NETWORK_TYPE_CDMA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                            type = "3G";
                            break;
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 2G
                        case TelephonyManager.NETWORK_TYPE_EDGE:
                            type = "2G";
                            break;
                    }
                    break;
            }
        }
        return type;
    }

    /**
     * 判断是否有网络
     *
     * @param error
     * @return
     */
    public static boolean isNoNetwork(String error) {
        if (error.contains("UnknownHostException") || error.contains("TimeoutException") || error.contains("ConnectException")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取ssid信息
     *
     * @param context
     * @return
     */
    public static String getSSID(Context context) {
        String result = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            result = wifiInfo.getSSID();
        }
        return result;
    }

    /**
     * 获取网关信息
     */
    public static String getWifiGateWay(Context context) {
        String result = null;
        WifiManager wifi_service = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifi_service.getDhcpInfo();
        result = long2Ip((long) dhcpInfo.gateway);
        return result;
    }

    /**
     * 将long类型转换成ip
     *
     * @param ip
     * @return
     */
    private static String long2Ip(long ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf((int) (ip & 255L)));
        sb.append('.');
        sb.append(String.valueOf((int) (ip >> 8 & 255L)));
        sb.append('.');
        sb.append(String.valueOf((int) (ip >> 16 & 255L)));
        sb.append('.');
        sb.append(String.valueOf((int) (ip >> 24 & 255L)));
        return sb.toString();
    }

    /**
     * 获取网关Mac地址
     *
     * @param gateWay
     * @return
     */
    public static String getGateWayMac(String gateWay) {
        Log.d(TAG, "getGateWayMac() start gateWay=" + gateWay);
        String result = "";
        String gatewaymacfile = readFile("/proc/net/arp");
        if (gatewaymacfile != null && gatewaymacfile.length() > 0) {
            String[] tmp = gatewaymacfile.split(gateWay + " ");
            if (tmp.length >= 2) {
                String temp = tmp[1];
                int test = temp.indexOf(":");
                if (test - 2 >= 0 && test + 15 <= temp.length()) {
                    result = temp.substring(test - 2, test + 15);
                    result = result.toLowerCase();
                } else {
                    Log.d(TAG, "getGateWayMac() string is error temp=" + temp);
                }
            } else {
                Log.d(TAG, "getGateWayMac() not gateWayMac");
            }
        } else {
            Log.d(TAG, "getGateWayMac() read /proc/net/arp file failed.");
        }

        Log.d(TAG, "getGateWayMac() end result=" + result);
        return result;
    }

    public static String readFile(String filePath) {
        StringBuffer result = new StringBuffer();
        String line = null;

        try {
            BufferedReader e = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));

            while ((line = e.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        } catch (FileNotFoundException var9) {
            var9.printStackTrace();
        } catch (IOException var10) {
            var10.printStackTrace();
        } finally {
            ;
        }

        Log.d(TAG, "readFile() end result=" + result.toString());
        return result.toString();
    }

    /**
     * 获取ip地址
     *
     * @param connectType
     * @return
     */
    public static String getLocalIP(int connectType) {
        Log.d(TAG, "getLocalIP() start");
        String result = "";
        if (connectType == 1) {
            result = getSystemProperties("dhcp.eth0.ipaddress");
        } else {
            try {
                Enumeration ex = NetworkInterface.getNetworkInterfaces();

                while (ex.hasMoreElements()) {
                    NetworkInterface intf = (NetworkInterface) ex.nextElement();
                    Enumeration enumIPAddr = intf.getInetAddresses();

                    while (enumIPAddr.hasMoreElements()) {
                        InetAddress inetAddress = (InetAddress) enumIPAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof
                                Inet4Address) {
                            result = inetAddress.getHostAddress().toString();
                            break;
                        }
                    }

                    if (result != null && result.length() > 0) {
                        break;
                    }
                }
            } catch (SocketException var6) {
                result = "";
                System.err.print("error");
            }
        }

        Log.d(TAG, "getLocalIP() end");
        return result;
    }

    public static String getSystemProperties(String key) {
        Log.d(TAG, "getSystemProperties() start key=" + key);
        String str = null;

        try {
            Class e = Class.forName("android.os.SystemProperties");
            Method get = e.getMethod("get", new Class[]{String.class});
            str = (String) get.invoke(e, new Object[]{key});
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        Log.d(TAG, "getSystemProperties() end result=" + str);
        return str;
    }

    public static int getNetworkType(Context context) {
        Log.d(TAG, "getNetworkType() start.");
        byte result = -1;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo active = cm.getActiveNetworkInfo();
        if (active != null) {
            int type = active.getType();
            switch (type) {
                case 0:
                    result = 4;
                    break;
                case 1:
                    result = 2;
                    break;
                case 9:
                    if (hasPPPoEInterface()) {
                        result = 3;
                    } else {
                        result = 1;
                    }
            }
        } else {
            Log.d(TAG, "getNetworkType() not ActiveNetwork.");
        }

        Log.d(TAG, "getNetworkType() end result=" + result);
        return result;
    }

    private static boolean hasPPPoEInterface() {
        Log.d(TAG, "hasPPPoEInterface() start.");
        boolean result = false;
        ArrayList nameList = new ArrayList();

        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();

            while (nis.hasMoreElements()) {
                NetworkInterface i = (NetworkInterface) nis.nextElement();
                nameList.add(i.getName());
            }
        } catch (SocketException var4) {
            var4.printStackTrace();
        }

        for (int var5 = 0; var5 < nameList.size(); ++var5) {
            if (((String) nameList.get(var5)).contains("ppp0")) {
                result = true;
                break;
            }
        }

        Log.d(TAG, "hasPPPoEInterface() end result = " + result);
        return result;
    }
}
