package com.shindefirm.shopapp.web_service

import android.content.Context
import com.shindefirm.shopapp.util.Logger.setLog
import com.shindefirm.shopapp.util.SharedPreferencesUtil
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shindefirm.shopapp.modal.GeneralDataModel
import com.shindefirm.shopapp.web_service.ApiClient
import com.google.gson.reflect.TypeToken
import com.shindefirm.shopapp.util.Constants
import com.shindefirm.shopapp.util.Logger
import okhttp3.Interceptor
import kotlin.Throws
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

////////// RESTADAPTER////////////////
object ApiClient {
    private var spUtil: SharedPreferencesUtil? = null
    private var retrofit: Retrofit? = null
    var client: OkHttpClient? = null
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private var generalDataModel: GeneralDataModel? = GeneralDataModel()
    fun getClient(activity: Context?): Retrofit? {
        try {
            spUtil = SharedPreferencesUtil(activity!!)
            val generalDataModelString = spUtil!!
                .fetchSharedPreferenesString(GeneralDataModel::class.java.name, "")
            if (generalDataModelString?.isEmpty() == true) {
                // generalDataModel = null;
            } else {
                val generalDataType = object : TypeToken<GeneralDataModel?>() {}.type
                generalDataModel = gson.fromJson(generalDataModelString, generalDataType)
            }
            try {
                if (generalDataModel != null && !generalDataModel!!.twitTokenModelArrayList!!.isEmpty()) {
                    setLog("Retrofit : " + generalDataModel.toString(), Logger.LogEnum.INFORMATION)
                    if (!generalDataModel!!.twitTokenModelArrayList!![0]!!.tokenValue.isEmpty() && generalDataModel!!.twitTokenModelArrayList!![0]!!.tokenValue != null) {
                        client = OkHttpClient.Builder()
                            .addInterceptor { chain ->
                                val newRequest = chain.request().newBuilder()
                                    .header(
                                        "Authorization",
                                        "Bearer " + generalDataModel!!.twitTokenModelArrayList!![0]!!.tokenValue
                                    )
                                    .build()
                                chain.proceed(newRequest)
                            }
                            .build()
                    } else {
                        client = defaultClient
                    }
                } else {
                    client = defaultClient
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                client = defaultClient
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            client = defaultClient
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }

    val defaultClient: OkHttpClient?
        get() {
            client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .header("Authorization", "Bearer " + Constants.BearerToken)
                        .build()
                    chain.proceed(newRequest)
                }
                .build()
            return client
        }
}