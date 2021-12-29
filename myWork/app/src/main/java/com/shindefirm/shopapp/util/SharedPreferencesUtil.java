package com.shindefirm.shopapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String SHARED_PREF_NAME = "NCP_Youth";
    public static final String SET_LANGUAGE = "LANGUAGE";

    public static final String KEY_MOBILENO = "mobileNo";
    public static final String KEY_USERID = "userId";
    public static final String KEY_NAME = "name";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_MNAME = "mname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DISTRICT_ID = "districtId";
    public static final String KEY_KK_DISTRICT_ID = "kk_districtId";
    public static final String KEY_TALUKA_ID = "talukaId";
    public static final String KEY_KK_TALUKA_ID = "kk_talukaId";
    public static final String KEY_VILLAGE_ID = "villageId";
    public static final String KEY_KK_VILLAGE_ID = "kk_villageId";
    public static final String KEY_CITY_ID = "cityId";
    public static final String KEY_KK_CITY_ID = "kk_cityId";
    public static final String KEY_STATE_ID = "stateId";
    public static final String KEY_DISTRICT = "district";
    public static final String KEY_TALUKA = "taluka";
    public static final String KEY_VILLAGE = "village";
    public static final String KEY_STATE = "state";
    public static final String KEY_LOGIN_STATUS = "loginStatus";
    public static final String KEY_APP_ID = "AppId";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_PROFILE_PHOTO = "profilePhoto";
    public static final String KEY_FCM_TOKEN = "fcmToken";
    public static final String KEY_ACTIVITY_ID = "C_ActivityId";
    public static final String KEY_LOGIN_STATUS_STATE = "loginState";
    public static final String KEY_IS_NEW = "isNew";
    public static final String KEY_CONSTITUENCY_ID = "constituency";
    public static final String KEY_CONSTITUENCY_NAME = "constituencyName";
    public static final String KEY_RURAL_URBAN= "ruralUrban";
    public static final String KEY_GENDER= "gender";
    public static final String KEY_CITY_NAME = "cityName";
    public static final String LOCAL_PROF_IMG = "localProfImg";
    private final Context context;

    public SharedPreferencesUtil(Context context) {
        this.context = context;
    }

    public void saveSharedPreferencesString(String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
    }

    public String fetchSharedPreferenesString(String key, String defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        String value = pref.getString(key, defaultValue);
       Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
        return value;
    }

    public void saveSharedPreferencesLong(String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
    }

    public long fetchSharedPreferenesLong(String key, long defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        long value = pref.getLong(key, defaultValue);
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
        return value;
    }

    public boolean fetchSharedPreferenesBoolean(String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        boolean value = pref.getBoolean(key, defaultValue);
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
        return value;
    }

    public void saveSharedPreferencesBoolean(String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
    }

    public int fetchSharedPreferenesInteger(String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        int value = pref.getInt(key, defaultValue);
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
        return value;
    }

    public void saveSharedPreferencesInteger(String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
        Logger.setLog("Key: " + key + "- Value:" + value, Logger.LogEnum.INFORMATION);
    }

    public void clearPreferences() {
        SharedPreferences settings = context.getSharedPreferences(SHARED_PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
        saveSharedPreferencesBoolean(KEY_LOGIN_STATUS, false);
        saveSharedPreferencesString(KEY_IS_NEW,"");
        saveSharedPreferencesString(KEY_LOGIN_STATUS_STATE,"");
     //   saveSharedPreferencesString(SET_LANGUAGE,"");
        Logger.setLog("Shared preferences values get cleared.", Logger.LogEnum.INFORMATION);
    }
}
