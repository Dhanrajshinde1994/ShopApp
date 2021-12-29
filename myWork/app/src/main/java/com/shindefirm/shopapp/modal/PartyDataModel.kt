package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.PartyDataModel

class PartyDataModel : Parcelable {
    //    "Id": 1,
    //            "PartyName": "Nationalist Congress Party",
    //            "PartyShortCode": "NCP"
    @SerializedName("Id")
    var id: String?

    @SerializedName("PartyName")
    var partyName: String

    @SerializedName("PartyShortCode")
    var partyShortCode: String?

    constructor() {
        id = ""
        partyName = ""
        partyShortCode = ""
    }

    constructor(id: String?, partyName: String, partyShortCode: String?) {
        this.id = id
        this.partyName = partyName
        this.partyShortCode = partyShortCode
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        partyName = `in`.readString()!!
        partyShortCode = `in`.readString()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(partyName)
        dest.writeString(partyShortCode)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun toString(): String {
        return partyName
    }

    companion object {
        @JvmField
        val CREATOR: Creator<PartyDataModel?> = object : Creator<PartyDataModel?> {
            override fun createFromParcel(`in`: Parcel): PartyDataModel? {
                return PartyDataModel(`in`)
            }

            override fun newArray(size: Int): Array<PartyDataModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}