package com.shindefirm.shopapp.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseWrapperModel {
    //{"data":"0","data1": [{"ID":12,"Msg":"Data Saved Successfully"}] }
    @SerializedName("data")
    private String dataState;
    @SerializedName("data1")
    private ArrayList<ProfileResponse> responseList;

    // This fields are internally used.
    private String ordinalName;
    private String errorMessage;

    public ResponseWrapperModel() {
        this.dataState = "";
        this.responseList = new ArrayList<>();
        this.ordinalName = "";
        this.errorMessage = "";
    }

    public ResponseWrapperModel(String dataState,
                                ArrayList<ProfileResponse> responseList) {
        this.dataState = dataState;
        this.responseList = responseList;
    }

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public ArrayList<ProfileResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(ArrayList<ProfileResponse> responseList) {
        this.responseList = responseList;
    }

    public String getOrdinalName() {
        return ordinalName;
    }

    public void setOrdinalName(String ordinalName) {
        this.ordinalName = ordinalName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
