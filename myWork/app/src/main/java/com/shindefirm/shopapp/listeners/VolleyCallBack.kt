package com.shindefirm.shopapp.listeners;


import com.shindefirm.shopapp.web_service.MethodOrdinal;

public interface VolleyCallBack {
    void getResponse(Object responseObject, MethodOrdinal responseOrdinal);

    void getError(String error, MethodOrdinal responseOrdinal);

//    public void displayFailedResponse(String failedMessage, int responseOrdinal);
}
