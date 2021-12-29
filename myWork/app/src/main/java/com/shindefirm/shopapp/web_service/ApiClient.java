package com.shindefirm.shopapp.web_service;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.shindefirm.shopapp.modal.GeneralDataModel;
import com.shindefirm.shopapp.util.Constants;
import com.shindefirm.shopapp.util.Logger;
import com.shindefirm.shopapp.util.SharedPreferencesUtil;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.shindefirm.shopapp.util.Constants.MAIN_URL;


////////// RESTADAPTER////////////////
public class ApiClient {

    private static SharedPreferencesUtil spUtil;
    private static Retrofit retrofit = null;
    public static OkHttpClient client;
    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static GeneralDataModel generalDataModel=new GeneralDataModel();


    public static Retrofit getClient(Context activity) {

        try {
            spUtil = new SharedPreferencesUtil(activity);
            String generalDataModelString = spUtil
                    .fetchSharedPreferenesString
                            (GeneralDataModel.class.getName(), "");
            if (generalDataModelString.isEmpty()) {
               // generalDataModel = null;
            } else {
                Type generalDataType = new TypeToken<GeneralDataModel>() {
                }.getType();
                generalDataModel = gson.fromJson
                        (generalDataModelString, generalDataType);
            }
            try {
                if (generalDataModel != null && !generalDataModel.getTwitTokenModelArrayList().isEmpty() ) {
                    Logger.setLog("Retrofit : "+generalDataModel.toString(), Logger.LogEnum.INFORMATION);

                    if (!generalDataModel.getTwitTokenModelArrayList().get(0).getTokenValue().isEmpty() && generalDataModel.getTwitTokenModelArrayList().get(0).getTokenValue() != null) {
                        client = new OkHttpClient.Builder()
                                .addInterceptor(new Interceptor() {
                                    @Override
                                    public Response intercept(Chain chain) throws IOException {
                                        Request newRequest = chain.request().newBuilder()
                                                .header("Authorization", "Bearer " + generalDataModel.getTwitTokenModelArrayList().get(0).getTokenValue())
                                                .build();

                                        return chain.proceed(newRequest);
                                    }
                                })
                                .build();
                    } else {
                        client=getDefaultClient();
                    }
                } else {
                    client=getDefaultClient();
                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
                client=getDefaultClient();

            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            client=getDefaultClient();

        }

        if (retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }
        return retrofit;
    }

    public static OkHttpClient getDefaultClient()
    {
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .header("Authorization", "Bearer " + Constants.BearerToken)
                                .build();

                        return chain.proceed(newRequest);
                    }
                })
                .build();

                return client;
    }
}

