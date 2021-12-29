package com.shindefirm.shopapp.listeners

import com.shindefirm.shopapp.web_service.MethodOrdinal

interface VolleyCallBack {
    fun getResponse(responseObject: Any?, responseOrdinal: MethodOrdinal?)
    fun getError(
        error: String?,
        responseOrdinal: MethodOrdinal?
    ) //    public void displayFailedResponse(String failedMessage, int responseOrdinal);
}