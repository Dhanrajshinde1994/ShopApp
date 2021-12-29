package com.shindefirm.shopapp.modal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import android.os.Parcelable.Creator
import com.shindefirm.shopapp.modal.ProgramModel

class ProgramModel : Parcelable {
    @SerializedName("Id")
    var id: String?

    @SerializedName("ProgramTitle")
    var programTitle: String

    @SerializedName("ProgramDescription")
    var programDescription: String?

    constructor() {
        id = ""
        programTitle = ""
        programDescription = ""
    }

    constructor(id: String?, programTitle: String, programDescription: String?) {
        this.id = id
        this.programTitle = programTitle
        this.programDescription = programDescription
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readString()
        programTitle = `in`.readString()!!
        programDescription = `in`.readString()
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(programTitle)
        dest.writeString(programDescription)
    }

    override fun toString(): String {
        return programTitle
    }

    companion object {
        @JvmField
        val CREATOR: Creator<ProgramModel?> = object : Creator<ProgramModel?> {
            override fun createFromParcel(`in`: Parcel): ProgramModel? {
                return ProgramModel(`in`)
            }

            override fun newArray(size: Int): Array<ProgramModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}