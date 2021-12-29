package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.SliderImageModel

class SliderImageModel : Parcelable {
    @SerializedName("SliderImage")
    var sliderImagePath: String

    constructor() {
        sliderImagePath = ""
    }

    constructor(sliderImagePath: String) {
        this.sliderImagePath = sliderImagePath
    }

    protected constructor(`in`: Parcel) {
        sliderImagePath = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(sliderImagePath)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun toString(): String {
        return sliderImagePath
    }

    companion object {
        @JvmField
        val CREATOR: Creator<SliderImageModel?> = object : Creator<SliderImageModel?> {
            override fun createFromParcel(`in`: Parcel): SliderImageModel? {
                return SliderImageModel(`in`)
            }

            override fun newArray(size: Int): Array<SliderImageModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}