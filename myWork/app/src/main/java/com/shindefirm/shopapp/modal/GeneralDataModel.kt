package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shindefirm.shopapp.modal.GeneralSettingModel
import com.shindefirm.shopapp.modal.CategoryModel
import com.shindefirm.shopapp.modal.PartyDataModel
import com.shindefirm.shopapp.modal.SliderImageModel
import com.shindefirm.shopapp.modal.ProgramModel
import com.shindefirm.shopapp.modal.TwitTokenModel
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.GeneralDataModel
import java.util.ArrayList

class GeneralDataModel : Parcelable {
    @SerializedName("data")
    var dataState: String?

    @SerializedName("data1")
    var generalSettingList: ArrayList<GeneralSettingModel?>?

    @SerializedName("data2")
    var categoryList: ArrayList<CategoryModel?>?

    @SerializedName("data3")
    var partyDataList: ArrayList<PartyDataModel?>?

    //    @SerializedName("data4")
    var sliderImageList: ArrayList<SliderImageModel?>?

    @SerializedName("data4")
    var programModelList: ArrayList<ProgramModel?>?

    @SerializedName("data5")
    var twitTokenModelArrayList: ArrayList<TwitTokenModel>?

    constructor() {
        dataState = ""
        generalSettingList = ArrayList()
        categoryList = ArrayList()
        partyDataList = ArrayList()
        sliderImageList = ArrayList()
        programModelList = ArrayList()
        twitTokenModelArrayList = ArrayList()
    }

    constructor(
        dataState: String?, generalSettingList: ArrayList<GeneralSettingModel?>?,
        categoryList: ArrayList<CategoryModel?>?,
        partyDataList: ArrayList<PartyDataModel?>?,
        sliderImageList: ArrayList<SliderImageModel?>?,
        programModelList: ArrayList<ProgramModel?>?,
        twitTokenModelArrayList: ArrayList<TwitTokenModel>?
    ) {
        this.dataState = dataState
        this.generalSettingList = generalSettingList
        this.categoryList = categoryList
        this.partyDataList = partyDataList
        this.sliderImageList = sliderImageList
        this.programModelList = programModelList
        this.twitTokenModelArrayList = twitTokenModelArrayList
    }

    protected constructor(`in`: Parcel) {
        dataState = `in`.readString()
        generalSettingList = `in`.createTypedArrayList(GeneralSettingModel.CREATOR)
        categoryList = `in`.createTypedArrayList(CategoryModel.CREATOR)
        partyDataList = `in`.createTypedArrayList(PartyDataModel.CREATOR)
        sliderImageList = `in`.createTypedArrayList(SliderImageModel.CREATOR)
        programModelList = `in`.createTypedArrayList(ProgramModel.CREATOR)
        twitTokenModelArrayList = `in`.createTypedArrayList(TwitTokenModel.CREATOR)
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(dataState)
        dest.writeTypedList(generalSettingList)
        dest.writeTypedList(categoryList)
        dest.writeTypedList(partyDataList)
        dest.writeTypedList(sliderImageList)
        dest.writeTypedList(programModelList)
        dest.writeTypedList(twitTokenModelArrayList)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return twitTokenModelArrayList!![0].tokenValue
    }

    companion object {
        @JvmField
        val CREATOR: Creator<GeneralDataModel?> = object : Creator<GeneralDataModel?> {
            override fun createFromParcel(`in`: Parcel): GeneralDataModel? {
                return GeneralDataModel(`in`)
            }

            override fun newArray(size: Int): Array<GeneralDataModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}