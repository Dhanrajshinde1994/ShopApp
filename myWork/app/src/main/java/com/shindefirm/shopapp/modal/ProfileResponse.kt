package com.shindefirm.shopapp.modal

import com.google.gson.annotations.SerializedName

class ProfileResponse(
    @field:SerializedName(
        value = "Id",
        alternate = ["ID"]
    ) var id: String, @field:SerializedName("Msg") var message: String, @field:SerializedName(
        "ProfilePhoto"
    ) var profilePhoto: String
) {

    override fun toString(): String {
        return message
    }
}