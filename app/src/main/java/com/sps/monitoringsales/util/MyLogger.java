package com.sps.monitoringsales.util;

import android.util.Log;

/**
 * Created by sigit on 15/03/2018.
 */

public class MyLogger {

    private static String LOG = "kopi";

    public static void logPesan(String pesan) {
        Log.d(LOG, pesan);
    }
}
