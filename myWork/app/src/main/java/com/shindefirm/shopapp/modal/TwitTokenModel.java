package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TwitTokenModel implements Parcelable {
    @SerializedName("TokenName")
    private String tokenName;
    @SerializedName("TokenValue")
    private String tokenValue;

    public TwitTokenModel(String tokenName, String tokenValue) {
        this.tokenName = tokenName;
        this.tokenValue = tokenValue;
    }

    protected TwitTokenModel(Parcel in) {
        tokenName = in.readString();
        tokenValue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tokenName);
        dest.writeString(tokenValue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TwitTokenModel> CREATOR = new Creator<TwitTokenModel>() {
        @Override
        public TwitTokenModel createFromParcel(Parcel in) {
            return new TwitTokenModel(in);
        }

        @Override
        public TwitTokenModel[] newArray(int size) {
            return new TwitTokenModel[size];
        }
    };

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Override
    public String toString() {
        return tokenValue;
    }
}
