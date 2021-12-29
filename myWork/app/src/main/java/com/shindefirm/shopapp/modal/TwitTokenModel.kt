package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.TwitTokenModel

class TwitTokenModel : Parcelable {
    @SerializedName("TokenName")
    var tokenName: String?

    @SerializedName("TokenValue")
    var tokenValue: String

    constructor(tokenName: String?, tokenValue: String) {
        this.tokenName = tokenName
        this.tokenValue = tokenValue
    }

    protected constructor(`in`: Parcel) {
        tokenName = `in`.readString()
        tokenValue = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(tokenName)
        dest.writeString(tokenValue)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return tokenValue
    }

    companion object {
        val CREATOR: Creator<TwitTokenModel> = object : Creator<TwitTokenModel?> {
            override fun createFromParcel(`in`: Parcel): TwitTokenModel? {
                return TwitTokenModel(`in`)
            }

            override fun newArray(size: Int): Array<TwitTokenModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}