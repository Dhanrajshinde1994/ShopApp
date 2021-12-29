package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.CategoryModel

class CategoryModel : Parcelable {
    @SerializedName("Id")
    var id: String?

    @SerializedName("Category")
    var category: String

    constructor() {
        id = ""
        category = ""
    }

    constructor(id: String?, category: String) {
        this.id = id
        this.category = category
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        category = `in`.readString()!!
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(category)
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun toString(): String {
        return category
    }

    companion object {
        @JvmField
        val CREATOR: Creator<CategoryModel?> = object : Creator<CategoryModel?> {
            override fun createFromParcel(`in`: Parcel): CategoryModel? {
                return CategoryModel(`in`)
            }

            override fun newArray(size: Int): Array<CategoryModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}