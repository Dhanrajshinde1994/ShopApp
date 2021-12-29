package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PartyDataModel implements Parcelable {
    //    "Id": 1,
//            "PartyName": "Nationalist Congress Party",
//            "PartyShortCode": "NCP"
    @SerializedName("Id")
    private String id;
    @SerializedName("PartyName")
    private String partyName;
    @SerializedName("PartyShortCode")
    private String partyShortCode;

    public PartyDataModel() {
        this.id = "";
        this.partyName = "";
        this.partyShortCode = "";
    }

    public PartyDataModel(String id, String partyName, String partyShortCode) {
        this.id = id;
        this.partyName = partyName;
        this.partyShortCode = partyShortCode;
    }

    protected PartyDataModel(Parcel in) {
        id = in.readString();
        partyName = in.readString();
        partyShortCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(partyName);
        dest.writeString(partyShortCode);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<PartyDataModel> CREATOR = new Creator<PartyDataModel>() {
        @Override
        public PartyDataModel createFromParcel(Parcel in) {
            return new PartyDataModel(in);
        }

        @Override
        public PartyDataModel[] newArray(int size) {
            return new PartyDataModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getPartyShortCode() {
        return partyShortCode;
    }

    public void setPartyShortCode(String partyShortCode) {
        this.partyShortCode = partyShortCode;
    }

    @Override
    public String toString() {
        return partyName;
    }
}
