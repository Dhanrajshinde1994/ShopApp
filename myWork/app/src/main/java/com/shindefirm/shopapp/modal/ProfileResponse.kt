package com.shindefirm.shopapp.modal;

import com.google.gson.annotations.SerializedName;


public class ProfileResponse {
    @SerializedName(value = "Id",alternate = {"ID"})
    private String id;
    @SerializedName("Msg")
    private String message;
    @SerializedName("ProfilePhoto")
    private String profilePhoto;

    public ProfileResponse(String id, String message, String profilePhoto) {
        this.id = id;
        this.message = message;
        this.profilePhoto = profilePhoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    @Override
    public String toString() {
        return message;
    }
}
