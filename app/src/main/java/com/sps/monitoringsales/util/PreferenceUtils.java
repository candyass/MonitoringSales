package com.sps.monitoringsales.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;

/**
 * Created by sigit on 25/04/2018.
 */

public class PreferenceUtils {

    private static final String PREF_NAME = "com.monitoringsales.preferences";

    private static final String KEY_PREF_BOOLEAN = "com.monitoringsales.preferenceUtils.key.boolean";

    private static PreferenceUtils singleton;

    private SharedPreferences mPreferences;

    public static PreferenceUtils getInstance(Context context) {
        if(singleton == null) {
            singleton = new PreferenceUtils(context);
        }
        return singleton;
    }

    private PreferenceUtils(Context context) {
        mPreferences =  context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveBoolean(boolean value) {
        mPreferences.edit().putBoolean(KEY_PREF_BOOLEAN, value).apply();
    }

    public boolean getBoolean() {
        return mPreferences.getBoolean(KEY_PREF_BOOLEAN, false);
    }
}
