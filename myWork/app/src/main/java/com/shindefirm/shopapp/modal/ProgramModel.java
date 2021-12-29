package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProgramModel implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("ProgramTitle")
    private String programTitle;
    @SerializedName("ProgramDescription")
    private String programDescription;

    public ProgramModel() {
        this.id = "";
        this.programTitle = "";
        this.programDescription = "";
    }

    public ProgramModel(String id, String programTitle, String programDescription) {
        this.id = id;
        this.programTitle = programTitle;
        this.programDescription = programDescription;
    }

    protected ProgramModel(Parcel in) {
        id = in.readString();
        programTitle = in.readString();
        programDescription = in.readString();
    }

    public static final Creator<ProgramModel> CREATOR = new Creator<ProgramModel>() {
        @Override
        public ProgramModel createFromParcel(Parcel in) {
            return new ProgramModel(in);
        }

        @Override
        public ProgramModel[] newArray(int size) {
            return new ProgramModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(programTitle);
        dest.writeString(programDescription);
    }

    @Override
    public String toString() {
        return programTitle;
    }
}
