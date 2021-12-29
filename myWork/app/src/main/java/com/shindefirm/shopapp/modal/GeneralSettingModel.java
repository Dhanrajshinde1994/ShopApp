package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GeneralSettingModel implements Parcelable {
    //Response: {"data":"0","data1": [{"IsAppBlock":1,"AppId":1,"UserId":1}] }
    @SerializedName("IsAppBlock")
    private int isAppBlock;
    @SerializedName("AppId")
    private int appId;
    @SerializedName("UserId")
    private String userId;
    @SerializedName("ReceiverId")
    private String receiverId;

    public GeneralSettingModel() {
        isAppBlock = 0;
        appId = 0;
        userId = "";
        receiverId = "";
    }

    public GeneralSettingModel(int isAppBlock, int appId, String userId, String receiverId) {
        this.isAppBlock = isAppBlock;
        this.appId = appId;
        this.userId = userId;
        this.receiverId = receiverId;
    }

    protected GeneralSettingModel(Parcel in) {
        isAppBlock = in.readInt();
        appId = in.readInt();
        userId = in.readString();
        receiverId = in.readString();
    }

    public static final Creator<GeneralSettingModel> CREATOR = new Creator<GeneralSettingModel>() {
        @Override
        public GeneralSettingModel createFromParcel(Parcel in) {
            return new GeneralSettingModel(in);
        }

        @Override
        public GeneralSettingModel[] newArray(int size) {
            return new GeneralSettingModel[size];
        }
    };

    public int getIsAppBlock() {
        return isAppBlock;
    }

    public void setIsAppBlock(int isAppBlock) {
        this.isAppBlock = isAppBlock;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(isAppBlock);
        dest.writeInt(appId);
        dest.writeString(userId);
        dest.writeString(receiverId);
    }
}
