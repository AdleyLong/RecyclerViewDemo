package com.huaqin.recyclerviewdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.util.Log;

import com.huaqin.recyclerviewdemo.App;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ubuntu on 17-4-13.
 */

public class FileUtil {
    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static File getAvailableCacheDir() {
        if (isExternalStorageWritable()) {
            return App.singleton.getExternalCacheDir();
        } else {
            return App.singleton.getCacheDir();
        }
    }
}
