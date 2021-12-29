package com.shindefirm.shopapp.web_service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.shindefirm.shopapp.R;
import com.shindefirm.shopapp.listeners.VolleyCallBack;
import com.shindefirm.shopapp.modal.ResponseWrapperModel;
import com.shindefirm.shopapp.util.AppUtils;
import com.shindefirm.shopapp.util.Constants;
import com.shindefirm.shopapp.util.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.shindefirm.shopapp.web_service.VolleySingleton.getInstance;

public class VolleyWebServices {
    private final String urlValidate = "&=*+-_.,:!?()/~%";
    private VolleySingleton requestQueue;
    private VolleyCallBack listener;

    public VolleyCallBack getListener() {
        return listener;
    }

    public void setListener(VolleyCallBack listener) {
        this.listener = listener;
    }

    public void getJSONRequest(final Context context,
                               final boolean showProgressDialog,
                               final String methodUrl,
                               final MethodOrdinal methodOrdinal) {
        if (!AppUtils.isInternetAvailable(context)) {
            listener.getError(context.getResources().getString
                    (R.string.noInternetConnectionAvailable), methodOrdinal);
           /* Toast.makeText(context, context.getString(R.string.noInternetConnectionAvailable),
                    Toast.LENGTH_SHORT).show();*/
          //  Logger.showToast(context, context.getString(R.string.str_internet_connection), true);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        if (showProgressDialog) {
            dialog.setMessage(context.getString(R.string.str_loading));
            dialog.setCancelable(false);
            dialog.show();
        } else {
            dialog.dismiss();
        }

        final String url = Uri.encode(methodUrl, urlValidate);
        Logger.setLog("Get Request URL: " + url, Logger.LogEnum.DEBUG);
        requestQueue = getInstance(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                completeObject -> {
                    if (showProgressDialog || dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    try {
                        Logger.setLog("Response from server :"
                                        + methodOrdinal.name()
                                        + ": " + completeObject.toString(),
                                Logger.LogEnum.INFORMATION);
                        listener.getResponse(completeObject.toString(), methodOrdinal);

                    } catch (Exception e) {
                        e.printStackTrace();
                        listener.getError(e.getMessage(), methodOrdinal);
                        Logger.setLog("Response has error :"
                                        + methodOrdinal.name()
                                        + ": " + e.getMessage(),
                                Logger.LogEnum.ERROR);
                    }
                },
                volleyError -> {
                    if (showProgressDialog || dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    Logger.setLog("Response has error :"
                                    + methodOrdinal.name()
                                    + ": " + volleyError.toString(),
                            Logger.LogEnum.ERROR);

                    if (volleyError instanceof ServerError ||
                            volleyError instanceof TimeoutError) {
                        ResponseWrapperModel responseWrapperModel = new
                                ResponseWrapperModel();
                        responseWrapperModel.setDataState("1");
                        responseWrapperModel.setOrdinalName(methodOrdinal.name());
                        responseWrapperModel.setErrorMessage(volleyError.getMessage());

                        listener.getError(new Gson().toJson(responseWrapperModel), methodOrdinal);
                        return;
                    }

                    listener.getError(volleyError.getMessage(), methodOrdinal);
                });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.addToRequestQueue(jsonObjectRequest);
    }

    public void postMultipartRequest(final Context context,
                                     final boolean isShowProgressBar,
                                     final String methodUrl,
                                     final MethodOrdinal methodOrdinal,
                                     final byte[] fileData,
                                     final Map<String, String> params,
                                     final String fileName) {
        if (!AppUtils.isInternetAvailable(context)) {
            String errorMessage = context.getString(R.string.noInternetConnectionAvailable);
            Logger.setLog("Request has error: " +
                            methodOrdinal.name()
                            + ": "
                            + errorMessage,
                    Logger.LogEnum.ERROR);

            Logger.showToast(context, context.getString(R.string.str_internet_connection), true);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.str_loading));
        dialog.setCancelable(false);
        if (isShowProgressBar)
            dialog.show();

        final String url = Uri.encode((Constants.MAIN_URL + methodUrl), urlValidate);
        Logger.setLog("URL: " + url,
                Logger.LogEnum.INFORMATION);
        Logger.setLog("Param: " + params.toString(),
                Logger.LogEnum.INFORMATION);
        Logger.setLog("file size: " + fileData.length,
                Logger.LogEnum.INFORMATION);
        Logger.setLog("file name: " + fileName,
                Logger.LogEnum.INFORMATION);

        requestQueue = getInstance(context);
        VolleyMultipartRequest jsonObjectRequest = new VolleyMultipartRequest(
                Request.Method.POST,
                url,
                completeObject -> {
                    if (dialog != null && dialog.isShowing())
                        dialog.dismiss();
                    String responseMessage = "";
                    if (completeObject instanceof NetworkResponse) {
                        responseMessage = new String(completeObject.data);
                    } else {
                        responseMessage = completeObject.toString();
                    }

                    listener.getResponse(responseMessage, methodOrdinal);
                    Logger.setLog("Response from server: " +
                                    methodOrdinal.name()
                                    + ": "
                                    + responseMessage,
                            Logger.LogEnum.INFORMATION);
                },
                volleyError -> {
                    if (dialog.isShowing())
                        dialog.dismiss();

                    // As of f605da3 the following should work
                    NetworkResponse response = volleyError.networkResponse;
                    if (volleyError instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            Log.e(Constants.LOG_TAG, res);
                            // Now you can use any deserializer to make sense of data
                            JSONObject jsonErrorObject = new JSONObject(res);
                            Log.e(Constants.LOG_TAG, "Response has error: " +
                                    methodOrdinal.name()
                                    + ": "
                                    + res);
                            listener.getResponse(jsonErrorObject, methodOrdinal);
                        } catch (UnsupportedEncodingException | JSONException e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        }

                        return;
                    }

                    Log.e(Constants.LOG_TAG, "Response has error: " +
                            methodOrdinal.name()
                            + ": "
                            + volleyError.getMessage());

                    if (volleyError instanceof ServerError ||
                            volleyError instanceof TimeoutError) {
                        ResponseWrapperModel responseWrapperModel = new
                                ResponseWrapperModel();
                        responseWrapperModel.setDataState("1");
                        responseWrapperModel.setOrdinalName(methodOrdinal.name());
                        responseWrapperModel.setErrorMessage(volleyError.getMessage());

                        listener.getError(new Gson().toJson(responseWrapperModel), methodOrdinal);
                        return;
                    }

                    listener.getResponse(volleyError, methodOrdinal);

                }) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put(fileName,
                        new DataPart(fileName,
                                fileData,
                                "doc/pdf"));

                return params;
            }

        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy
                (Constants.REQUEST_TIME_OUT,
                        0,//DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.addToRequestQueue(jsonObjectRequest);
    }

    public void postRequest(
            final Context context,
            final boolean showProgressDialog,
            final String methodUrl,
            final MethodOrdinal methodOrdinal,
            final Map<String, String> postParams) {

        if (!AppUtils.isInternetAvailable(context)) {
            String errorMessage = context.getString
                    (R.string.noInternetConnectionAvailable);
            Logger.showToast(context, context.getString(R.string.str_internet_connection), true);

            Log.e(Constants.LOG_TAG, "Response has error :"
                    + methodOrdinal.name()
                    + ": " + errorMessage);
            listener.getError(context.getString(R.string.noInternetConnectionAvailable),
                    methodOrdinal);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.str_loading));
        dialog.setCancelable(false);
        if (showProgressDialog) {
            dialog.show();
        }

        Log.i(Constants.LOG_TAG, "Request URL : " + methodUrl);
        Log.i(Constants.LOG_TAG, "Request Parameter(s) : " + postParams);
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                methodUrl,
                stringResponse -> {
                    if (showProgressDialog || dialog.isShowing())
                        dialog.dismiss();

                    Log.i(Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name()
                            + ": "
                            + stringResponse);
                    listener.getResponse(stringResponse, methodOrdinal);

                },
                volleyError -> {
                    if (showProgressDialog || dialog.isShowing())
                        dialog.dismiss();

                    NetworkResponse response = volleyError.networkResponse;
                    if (volleyError instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            // Now you can use any deserializer to make sense of data
//                            JSONObject obj = new JSONObject(res);
                            Log.e(Constants.LOG_TAG, res);
                            listener.getResponse(res, methodOrdinal);
                        } catch (UnsupportedEncodingException e1) {
                            // Couldn't properly decode data to string
                            e1.printStackTrace();
                        }
//                        catch (JSONException e2) {
//                            // returned data is not JSONObject?
//                            e2.printStackTrace();
//                        }
                    }

                    Log.e(Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name()
                            + ": "
                            + volleyError.getMessage());
                    if (volleyError instanceof ServerError ||
                            volleyError instanceof TimeoutError) {
                        ResponseWrapperModel responseWrapperModel = new
                                ResponseWrapperModel();
                        responseWrapperModel.setDataState("1");
                        responseWrapperModel.setOrdinalName(methodOrdinal.name());
                        responseWrapperModel.setErrorMessage(volleyError.getMessage());

                        listener.getError(new Gson().toJson(responseWrapperModel), methodOrdinal);
                        return;
                    }

                    listener.getResponse(volleyError, methodOrdinal);
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                return postParams;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headerMap = new HashMap<>();
                headerMap.put("Content-Type", "application/x-www-form-urlencoded");
                return headerMap;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIME_OUT,
                0, //DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    public void postRequestJSON(
            final Context context,
            final boolean showProgressDialog,
            final String methodUrl,
            final MethodOrdinal methodOrdinal,
            final String requestBodyInJson) {

        if (!AppUtils.isInternetAvailable(context)) {
            String errorMessage = context.getString
                    (R.string.noInternetConnectionAvailable);
            Logger.showToast(context, context.getString(R.string.str_internet_connection), true);
            Log.e(Constants.LOG_TAG, "Response has error :"
                    + methodOrdinal.name()
                    + ": " + errorMessage);
            listener.getError(context.getString(R.string.noInternetConnectionAvailable),
                    methodOrdinal);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.str_loading));
        dialog.setCancelable(false);
        if (showProgressDialog) {
            dialog.show();
        }

        final String url = Constants.MAIN_URL + methodUrl;

        Log.i(Constants.LOG_TAG, "Request URL : " + url);
        Log.d(Constants.LOG_TAG, "Request body: " + requestBodyInJson);
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final StringRequest postRequest = new StringRequest(
                Request.Method.POST,
                url,
                stringResponse -> {
                    if (showProgressDialog || dialog.isShowing())
                        dialog.dismiss();

                    Log.i(Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name()
                            + ": "
                            + stringResponse);
                    listener.getResponse(stringResponse, methodOrdinal);

                },
                volleyError -> {
                    if (showProgressDialog || dialog.isShowing())
                        dialog.dismiss();

                    Log.e(Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name()
                            + ": "
                            + volleyError.getMessage());
                    listener.getResponse(volleyError, methodOrdinal);
                }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBodyInJson == null ? null : requestBodyInJson.getBytes(StandardCharsets.UTF_8);
            }

//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                    // can get more details such as response.headers
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }


        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    public void postRequestInJSONFormat(
            final Context context,
            final boolean showProgressDialog,
            final String methodUrl,
            final MethodOrdinal methodOrdinal,
            final JSONObject requestBodyInJson) {

        if (!AppUtils.isInternetAvailable(context)) {
            String errorMessage = context.getString
                    (R.string.noInternetConnectionAvailable);
            Logger.showToast(context, context.getString(R.string.str_internet_connection), true);
            Log.e(Constants.LOG_TAG, "Response has error :"
                    + methodOrdinal.name()
                    + ": " + errorMessage);
            listener.getError(context.getString(R.string.noInternetConnectionAvailable),
                    methodOrdinal);
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(context.getString(R.string.str_loading));
        dialog.setCancelable(false);
        if (showProgressDialog) {
            dialog.show();
        }

        final String url = Constants.MAIN_URL + methodUrl;

        Log.i(Constants.LOG_TAG, "Request URL : " + url);
        Log.d(Constants.LOG_TAG, "Request body: " + requestBodyInJson);
        requestQueue = getInstance(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                requestBodyInJson,
                completeObject -> {
                    if (showProgressDialog || dialog.isShowing()) {
                        dialog.dismiss();
                    }

                    try {
                        Log.i(Constants.LOG_TAG, "Response from server :"
                                + methodOrdinal.name()
                                + ": " + completeObject.toString());
                        listener.getResponse(completeObject.toString(), methodOrdinal);

                    } catch (Exception e) {
                        e.printStackTrace();
                        listener.getError(e.getMessage(), methodOrdinal);
                        Log.e(Constants.LOG_TAG, "Response has error :"
                                + methodOrdinal.name()
                                + ": " + e.getMessage());
                    }
                },
                volleyError -> {
                    if (showProgressDialog || dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Log.e(Constants.LOG_TAG, "Response has error :"
                            + methodOrdinal.name()
                            + ": " + volleyError.getMessage());

                    if (volleyError instanceof ServerError ||
                            volleyError instanceof TimeoutError) {
                        ResponseWrapperModel responseWrapperModel = new
                                ResponseWrapperModel();
                        responseWrapperModel.setDataState("1");
                        responseWrapperModel.setOrdinalName(methodOrdinal.name());
                        responseWrapperModel.setErrorMessage(volleyError.getMessage());

                        listener.getError(new Gson().toJson(responseWrapperModel), methodOrdinal);
                        return;
                    }


                    listener.getError(volleyError.getMessage(), methodOrdinal);
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("Content-Type", "application/json");
                return param;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                Constants.REQUEST_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.addToRequestQueue(jsonObjectRequest);
    }
}