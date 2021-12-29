package com.shindefirm.shopapp.web_service

import com.shindefirm.shopapp.util.AppUtils.isInternetAvailable
import com.shindefirm.shopapp.util.Logger.setLog
import com.shindefirm.shopapp.web_service.VolleySingleton.Companion.getInstance
import com.shindefirm.shopapp.util.Logger.showToast
import com.shindefirm.shopapp.web_service.VolleySingleton
import com.shindefirm.shopapp.listeners.VolleyCallBack
import com.shindefirm.shopapp.web_service.MethodOrdinal
import com.shindefirm.shopapp.util.AppUtils
import com.shindefirm.shopapp.R
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import com.shindefirm.shopapp.modal.ResponseWrapperModel
import com.google.gson.Gson
import com.shindefirm.shopapp.web_service.VolleyMultipartRequest
import com.android.volley.toolbox.HttpHeaderParser
import org.json.JSONException
import com.shindefirm.shopapp.web_service.VolleyMultipartRequest.DataPart
import com.android.volley.toolbox.Volley
import com.android.volley.toolbox.StringRequest
import kotlin.Throws
import androidx.annotation.RequiresApi
import android.os.Build
import android.util.Log
import com.android.volley.*
import com.shindefirm.shopapp.util.Constants
import com.shindefirm.shopapp.util.Logger
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.util.HashMap

class VolleyWebServices {
    private val urlValidate = "&=*+-_.,:!?()/~%"
    private var requestQueue: VolleySingleton? = null
    var listener: VolleyCallBack? = null
    fun getJSONRequest(
        context: Context,
        showProgressDialog: Boolean,
        methodUrl: String?,
        methodOrdinal: MethodOrdinal
    ) {
        if (!isInternetAvailable(context)) {
            listener!!.getError(
                context.resources.getString(R.string.noInternetConnectionAvailable),
                methodOrdinal
            )
            /* Toast.makeText(context, context.getString(R.string.noInternetConnectionAvailable),
                    Toast.LENGTH_SHORT).show();*/
            //  Logger.showToast(context, context.getString(R.string.str_internet_connection), true);
            return
        }
        val dialog = ProgressDialog(context)
        if (showProgressDialog) {
            dialog.setMessage(context.getString(R.string.str_loading))
            dialog.setCancelable(false)
            dialog.show()
        } else {
            dialog.dismiss()
        }
        val url = Uri.encode(methodUrl, urlValidate)
        setLog("Get Request URL: $url", Logger.LogEnum.DEBUG)
        requestQueue = getInstance(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { completeObject: JSONObject ->
                if (showProgressDialog || dialog.isShowing) {
                    dialog.dismiss()
                }
                try {
                    setLog(
                        "Response from server :"
                                + methodOrdinal.name
                                + ": " + completeObject.toString(),
                        Logger.LogEnum.INFORMATION
                    )
                    listener!!.getResponse(completeObject.toString(), methodOrdinal)
                } catch (e: Exception) {
                    e.printStackTrace()
                    listener!!.getError(e.message, methodOrdinal)
                    setLog(
                        "Response has error :"
                                + methodOrdinal.name
                                + ": " + e.message,
                        Logger.LogEnum.ERROR
                    )
                }
            }, label@
            Response.ErrorListener { volleyError: VolleyError ->
                if (showProgressDialog || dialog.isShowing) {
                    dialog.dismiss()
                }
                setLog(
                    "Response has error :"
                            + methodOrdinal.name
                            + ": " + volleyError.toString(),
                    Logger.LogEnum.ERROR
                )
                if (volleyError is ServerError ||
                    volleyError is TimeoutError
                ) {
                    val responseWrapperModel = ResponseWrapperModel()
                    responseWrapperModel.dataState = "1"
                    responseWrapperModel.ordinalName = methodOrdinal.name
                    responseWrapperModel.errorMessage = volleyError.message
                    listener!!.getError(Gson().toJson(responseWrapperModel), methodOrdinal)

                }
                listener!!.getError(volleyError.message, methodOrdinal)
            })
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            Constants.REQUEST_TIME_OUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue!!.addToRequestQueue(jsonObjectRequest)
    }

    fun postMultipartRequest(
        context: Context,
        isShowProgressBar: Boolean,
        methodUrl: String,
        methodOrdinal: MethodOrdinal,
        fileData: ByteArray,
        params: Map<String, String>,
        fileName: String
    ) {
        if (!isInternetAvailable(context)) {
            val errorMessage = context.getString(R.string.noInternetConnectionAvailable)
            setLog(
                "Request has error: " +
                        methodOrdinal.name
                        + ": "
                        + errorMessage,
                Logger.LogEnum.ERROR
            )
            showToast(context, context.getString(R.string.str_internet_connection), true)
            return
        }
        val dialog = ProgressDialog(context)
        dialog.setMessage(context.getString(R.string.str_loading))
        dialog.setCancelable(false)
        if (isShowProgressBar) dialog.show()
        val url = Uri.encode(Constants.MAIN_URL + methodUrl, urlValidate)
        setLog(
            "URL: $url",
            Logger.LogEnum.INFORMATION
        )
        setLog(
            "Param: $params",
            Logger.LogEnum.INFORMATION
        )
        setLog(
            "file size: " + fileData.size,
            Logger.LogEnum.INFORMATION
        )
        setLog(
            "file name: $fileName",
            Logger.LogEnum.INFORMATION
        )
        requestQueue = getInstance(context)
        val jsonObjectRequest: VolleyMultipartRequest = object : VolleyMultipartRequest(
            Method.POST,
            url,
            Response.Listener { completeObject: NetworkResponse ->
                if (dialog != null && dialog.isShowing) dialog.dismiss()
                var responseMessage = ""
                responseMessage = if (completeObject is NetworkResponse) {
                    String(completeObject.data)
                } else {
                    completeObject.toString()
                }
                listener!!.getResponse(responseMessage, methodOrdinal)
                setLog(
                    "Response from server: " +
                            methodOrdinal.name
                            + ": "
                            + responseMessage,
                    Logger.LogEnum.INFORMATION
                )
            }, label@
            Response.ErrorListener { volleyError: VolleyError ->
                if (dialog.isShowing) dialog.dismiss()

                // As of f605da3 the following should work
                val response = volleyError.networkResponse
                if (volleyError is ServerError && response != null) {
                    try {
                        val res = String(
                            response.data,
                            charset(HttpHeaderParser.parseCharset(response.headers, "utf-8"))
                        )
                        Log.e(Constants.LOG_TAG, res)
                        // Now you can use any deserializer to make sense of data
                        val jsonErrorObject = JSONObject(res)
                        Log.e(
                            Constants.LOG_TAG, "Response has error: " +
                                    methodOrdinal.name
                                    + ": "
                                    + res
                        )
                        listener!!.getResponse(jsonErrorObject, methodOrdinal)
                    } catch (e1: UnsupportedEncodingException) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace()
                    } catch (e1: JSONException) {
                        e1.printStackTrace()
                    }

                }
                Log.e(
                    Constants.LOG_TAG, "Response has error: " +
                            methodOrdinal.name
                            + ": "
                            + volleyError.message
                )
                if (volleyError is ServerError ||
                    volleyError is TimeoutError
                ) {
                    val responseWrapperModel = ResponseWrapperModel()
                    responseWrapperModel.dataState = "1"
                    responseWrapperModel.ordinalName = methodOrdinal.name
                    responseWrapperModel.errorMessage = volleyError.message
                    listener!!.getError(Gson().toJson(responseWrapperModel), methodOrdinal)

                }
                listener!!.getResponse(volleyError, methodOrdinal)
            }) {
            override fun getParams(): Map<String, String>? {
                return params
            }

            override val byteData: Map<String, DataPart>
                protected get() {
                    val params: MutableMap<String, DataPart> = HashMap()
                    params[fileName] = DataPart(
                        fileName,
                        fileData,
                        "doc/pdf"
                    )
                    return params
                }
        }
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            Constants.REQUEST_TIME_OUT,
            0,  //DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue!!.addToRequestQueue(jsonObjectRequest)
    }

    fun postRequest(
        context: Context,
        showProgressDialog: Boolean,
        methodUrl: String,
        methodOrdinal: MethodOrdinal,
        postParams: Map<String, String>
    ) {
        if (!isInternetAvailable(context)) {
            val errorMessage = context.getString(R.string.noInternetConnectionAvailable)
            showToast(context, context.getString(R.string.str_internet_connection), true)
            Log.e(
                Constants.LOG_TAG, "Response has error :"
                        + methodOrdinal.name
                        + ": " + errorMessage
            )
            listener!!.getError(
                context.getString(R.string.noInternetConnectionAvailable),
                methodOrdinal
            )
            return
        }
        val dialog = ProgressDialog(context)
        dialog.setMessage(context.getString(R.string.str_loading))
        dialog.setCancelable(false)
        if (showProgressDialog) {
            dialog.show()
        }
        Log.i(Constants.LOG_TAG, "Request URL : $methodUrl")
        Log.i(Constants.LOG_TAG, "Request Parameter(s) : $postParams")
        val requestQueue = Volley.newRequestQueue(context)
        val postRequest: StringRequest = object : StringRequest(
            Method.POST,
            methodUrl,
            Response.Listener { stringResponse: String ->
                if (showProgressDialog || dialog.isShowing) dialog.dismiss()
                Log.i(
                    Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name
                            + ": "
                            + stringResponse
                )
                listener!!.getResponse(stringResponse, methodOrdinal)
            }, label@
            Response.ErrorListener { volleyError: VolleyError ->
                if (showProgressDialog || dialog.isShowing) dialog.dismiss()
                val response = volleyError.networkResponse
                if (volleyError is ServerError && response != null) {
                    try {
                        val res = String(
                            response.data,
                            charset(HttpHeaderParser.parseCharset(response.headers, "utf-8"))
                        )
                        // Now you can use any deserializer to make sense of data
//                            JSONObject obj = new JSONObject(res);
                        Log.e(Constants.LOG_TAG, res)
                        listener!!.getResponse(res, methodOrdinal)
                    } catch (e1: UnsupportedEncodingException) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace()
                    }
                    //                        catch (JSONException e2) {
//                            // returned data is not JSONObject?
//                            e2.printStackTrace();
//                        }
                }
                Log.e(
                    Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name
                            + ": "
                            + volleyError.message
                )
                if (volleyError is ServerError ||
                    volleyError is TimeoutError
                ) {
                    val responseWrapperModel = ResponseWrapperModel()
                    responseWrapperModel.dataState = "1"
                    responseWrapperModel.ordinalName = methodOrdinal.name
                    responseWrapperModel.errorMessage = volleyError.message
                    listener!!.getError(Gson().toJson(responseWrapperModel), methodOrdinal)

                }
                listener!!.getResponse(volleyError, methodOrdinal)
            }
        ) {
            override fun getParams(): Map<String, String>? {
                return postParams
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headerMap = HashMap<String, String>()
                headerMap["Content-Type"] = "application/x-www-form-urlencoded"
                return headerMap
            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(
            Constants.REQUEST_TIME_OUT,
            0,  //DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(postRequest)
    }



    fun postRequestJSON(
        context: Context,
        showProgressDialog: Boolean,
        methodUrl: String,
        methodOrdinal: MethodOrdinal,
        requestBodyInJson: String?
    ) {
        if (!isInternetAvailable(context)) {
            val errorMessage = context.getString(R.string.noInternetConnectionAvailable)
            showToast(context, context.getString(R.string.str_internet_connection), true)
            Log.e(
                Constants.LOG_TAG, "Response has error :"
                        + methodOrdinal.name
                        + ": " + errorMessage
            )
            listener!!.getError(
                context.getString(R.string.noInternetConnectionAvailable),
                methodOrdinal
            )
            return
        }
        val dialog = ProgressDialog(context)
        dialog.setMessage(context.getString(R.string.str_loading))
        dialog.setCancelable(false)
        if (showProgressDialog) {
            dialog.show()
        }
        val url = Constants.MAIN_URL + methodUrl
        Log.i(Constants.LOG_TAG, "Request URL : $url")
        Log.d(Constants.LOG_TAG, "Request body: $requestBodyInJson")
        val requestQueue = Volley.newRequestQueue(context)
        val postRequest: StringRequest = object : StringRequest(
            Method.POST,
            url,
            Response.Listener { stringResponse: String ->
                if (showProgressDialog || dialog.isShowing) dialog.dismiss()
                Log.i(
                    Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name
                            + ": "
                            + stringResponse
                )
                listener!!.getResponse(stringResponse, methodOrdinal)
            },
            Response.ErrorListener { volleyError: VolleyError ->
                if (showProgressDialog || dialog.isShowing) dialog.dismiss()
                Log.e(
                    Constants.LOG_TAG, "Response from server:- "
                            + methodOrdinal.name
                            + ": "
                            + volleyError.message
                )
                listener!!.getResponse(volleyError, methodOrdinal)
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray? {
                return requestBodyInJson?.toByteArray(StandardCharsets.UTF_8)
            } //            @Override
            //            protected Response<String> parseNetworkResponse(NetworkResponse response) {
            //                String responseString = "";
            //                if (response != null) {
            //                    responseString = String.valueOf(response.statusCode);
            //                    // can get more details such as response.headers
            //                }
            //                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            //            }
        }
        postRequest.retryPolicy = DefaultRetryPolicy(
            Constants.REQUEST_TIME_OUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue.add(postRequest)
    }

    fun postRequestInJSONFormat(
        context: Context,
        showProgressDialog: Boolean,
        methodUrl: String,
        methodOrdinal: MethodOrdinal,
        requestBodyInJson: JSONObject
    ) {
        if (!isInternetAvailable(context)) {
            val errorMessage = context.getString(R.string.noInternetConnectionAvailable)
            showToast(context, context.getString(R.string.str_internet_connection), true)
            Log.e(
                Constants.LOG_TAG, "Response has error :"
                        + methodOrdinal.name
                        + ": " + errorMessage
            )
            listener!!.getError(
                context.getString(R.string.noInternetConnectionAvailable),
                methodOrdinal
            )
            return
        }
        val dialog = ProgressDialog(context)
        dialog.setMessage(context.getString(R.string.str_loading))
        dialog.setCancelable(false)
        if (showProgressDialog) {
            dialog.show()
        }
        val url = Constants.MAIN_URL + methodUrl
        Log.i(Constants.LOG_TAG, "Request URL : $url")
        Log.d(Constants.LOG_TAG, "Request body: $requestBodyInJson")
        requestQueue = getInstance(context)
        val jsonObjectRequest: JsonObjectRequest = object : JsonObjectRequest(
            Method.POST,
            url,
            requestBodyInJson,
            Response.Listener { completeObject: JSONObject ->
                if (showProgressDialog || dialog.isShowing) {
                    dialog.dismiss()
                }
                try {
                    Log.i(
                        Constants.LOG_TAG, "Response from server :"
                                + methodOrdinal.name
                                + ": " + completeObject.toString()
                    )
                    listener!!.getResponse(completeObject.toString(), methodOrdinal)
                } catch (e: Exception) {
                    e.printStackTrace()
                    listener!!.getError(e.message, methodOrdinal)
                    Log.e(
                        Constants.LOG_TAG, "Response has error :"
                                + methodOrdinal.name
                                + ": " + e.message
                    )
                }
            }, label@
            Response.ErrorListener { volleyError: VolleyError ->
                if (showProgressDialog || dialog.isShowing) {
                    dialog.dismiss()
                }
                Log.e(
                    Constants.LOG_TAG, "Response has error :"
                            + methodOrdinal.name
                            + ": " + volleyError.message
                )
                if (volleyError is ServerError ||
                    volleyError is TimeoutError
                ) {
                    val responseWrapperModel = ResponseWrapperModel()
                    responseWrapperModel.dataState = "1"
                    responseWrapperModel.ordinalName = methodOrdinal.name
                    responseWrapperModel.errorMessage = volleyError.message
                    listener!!.getError(Gson().toJson(responseWrapperModel), methodOrdinal)

                }
                listener!!.getError(volleyError.message, methodOrdinal)
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val param = HashMap<String, String>()
                param["Content-Type"] = "application/json"
                return param
            }
        }
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            Constants.REQUEST_TIME_OUT,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        requestQueue!!.addToRequestQueue(jsonObjectRequest)
    }
}