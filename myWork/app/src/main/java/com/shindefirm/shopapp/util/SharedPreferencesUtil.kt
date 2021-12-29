package com.shindefirm.shopapp.util

import android.content.Context
import com.shindefirm.shopapp.util.Logger.setLog
import android.content.SharedPreferences
import com.shindefirm.shopapp.util.SharedPreferencesUtil

class SharedPreferencesUtil(private val context: Context) {
    fun saveSharedPreferencesString(key: String, value: String) {
        val settings = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
    }

    fun fetchSharedPreferenesString(key: String, defaultValue: String?): String? {
        val pref = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val value = pref.getString(key, defaultValue)
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
        return value
    }

    fun saveSharedPreferencesLong(key: String, value: Long) {
        val settings = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = settings.edit()
        editor.putLong(key, value)
        editor.apply()
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
    }

    fun fetchSharedPreferenesLong(key: String, defaultValue: Long): Long {
        val pref = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val value = pref.getLong(key, defaultValue)
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
        return value
    }

    fun fetchSharedPreferenesBoolean(key: String, defaultValue: Boolean): Boolean {
        val pref = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val value = pref.getBoolean(key, defaultValue)
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
        return value
    }

    fun saveSharedPreferencesBoolean(key: String, value: Boolean) {
        val settings = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
    }

    fun fetchSharedPreferenesInteger(key: String, defaultValue: Int): Int {
        val pref = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val value = pref.getInt(key, defaultValue)
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
        return value
    }

    fun saveSharedPreferencesInteger(key: String, value: Int) {
        val settings = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
        setLog("Key: $key- Value:$value", Logger.LogEnum.INFORMATION)
    }

    fun clearPreferences() {
        val settings = context.getSharedPreferences(SHARED_PREF_NAME, 0)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
        saveSharedPreferencesBoolean(KEY_LOGIN_STATUS, false)
        saveSharedPreferencesString(KEY_IS_NEW, "")
        saveSharedPreferencesString(KEY_LOGIN_STATUS_STATE, "")
        //   saveSharedPreferencesString(SET_LANGUAGE,"");
        setLog("Shared preferences values get cleared.", Logger.LogEnum.INFORMATION)
    }

    companion object {
        private const val SHARED_PREF_NAME = "NCP_Youth"
        const val SET_LANGUAGE = "LANGUAGE"
        const val KEY_MOBILENO = "mobileNo"
        const val KEY_USERID = "userId"
        const val KEY_NAME = "name"
        const val KEY_FNAME = "fname"
        const val KEY_MNAME = "mname"
        const val KEY_LNAME = "lname"
        const val KEY_EMAIL = "email"
        const val KEY_DISTRICT_ID = "districtId"
        const val KEY_KK_DISTRICT_ID = "kk_districtId"
        const val KEY_TALUKA_ID = "talukaId"
        const val KEY_KK_TALUKA_ID = "kk_talukaId"
        const val KEY_VILLAGE_ID = "villageId"
        const val KEY_KK_VILLAGE_ID = "kk_villageId"
        const val KEY_CITY_ID = "cityId"
        const val KEY_KK_CITY_ID = "kk_cityId"
        const val KEY_STATE_ID = "stateId"
        const val KEY_DISTRICT = "district"
        const val KEY_TALUKA = "taluka"
        const val KEY_VILLAGE = "village"
        const val KEY_STATE = "state"
        const val KEY_LOGIN_STATUS = "loginStatus"
        const val KEY_APP_ID = "AppId"
        const val KEY_ADDRESS = "address"
        const val KEY_PROFILE_PHOTO = "profilePhoto"
        const val KEY_FCM_TOKEN = "fcmToken"
        const val KEY_ACTIVITY_ID = "C_ActivityId"
        const val KEY_LOGIN_STATUS_STATE = "loginState"
        const val KEY_IS_NEW = "isNew"
        const val KEY_CONSTITUENCY_ID = "constituency"
        const val KEY_CONSTITUENCY_NAME = "constituencyName"
        const val KEY_RURAL_URBAN = "ruralUrban"
        const val KEY_GENDER = "gender"
        const val KEY_CITY_NAME = "cityName"
        const val LOCAL_PROF_IMG = "localProfImg"
    }
}