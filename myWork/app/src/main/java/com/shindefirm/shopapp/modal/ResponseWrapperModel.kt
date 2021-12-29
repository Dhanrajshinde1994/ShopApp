package com.shindefirm.shopapp.modal

import com.google.gson.annotations.SerializedName
import com.shindefirm.shopapp.modal.ProfileResponse
import java.util.ArrayList

class ResponseWrapperModel {
    //{"data":"0","data1": [{"ID":12,"Msg":"Data Saved Successfully"}] }
    @SerializedName("data")
    var dataState: String

    @SerializedName("data1")
    var responseList: ArrayList<ProfileResponse>

    // This fields are internally used.
    var ordinalName: String? = null
    var errorMessage: String? = null

    constructor() {
        dataState = ""
        responseList = ArrayList()
        ordinalName = ""
        errorMessage = ""
    }

    constructor(
        dataState: String,
        responseList: ArrayList<ProfileResponse>
    ) {
        this.dataState = dataState
        this.responseList = responseList
    }
}