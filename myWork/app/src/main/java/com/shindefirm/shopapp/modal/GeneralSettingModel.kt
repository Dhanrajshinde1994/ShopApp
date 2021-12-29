package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.GeneralSettingModel

class GeneralSettingModel : Parcelable {
    //Response: {"data":"0","data1": [{"IsAppBlock":1,"AppId":1,"UserId":1}] }
    @SerializedName("IsAppBlock")
    var isAppBlock: Int

    @SerializedName("AppId")
    var appId: Int

    @SerializedName("UserId")
    var userId: String?

    @SerializedName("ReceiverId")
    var receiverId: String?

    constructor() {
        isAppBlock = 0
        appId = 0
        userId = ""
        receiverId = ""
    }

    constructor(isAppBlock: Int, appId: Int, userId: String?, receiverId: String?) {
        this.isAppBlock = isAppBlock
        this.appId = appId
        this.userId = userId
        this.receiverId = receiverId
    }

    protected constructor(`in`: Parcel) {
        isAppBlock = `in`.readInt()
        appId = `in`.readInt()
        userId = `in`.readString()
        receiverId = `in`.readString()
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(isAppBlock)
        dest.writeInt(appId)
        dest.writeString(userId)
        dest.writeString(receiverId)
    }

    companion object {
        @JvmField
        val CREATOR: Creator<GeneralSettingModel?> = object : Creator<GeneralSettingModel?> {
            override fun createFromParcel(`in`: Parcel): GeneralSettingModel? {
                return GeneralSettingModel(`in`)
            }

            override fun newArray(size: Int): Array<GeneralSettingModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}