package com.aman.videolist;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class containing utility methods
 */
public class Utility {

    /**
     * Check if network is connected or not
     * @param context Caller Context
     * @return true if network is connected, false otherwise
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
