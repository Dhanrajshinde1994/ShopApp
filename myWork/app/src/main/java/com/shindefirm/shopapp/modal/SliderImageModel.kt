package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class SliderImageModel implements Parcelable {
    @SerializedName("SliderImage")
    private String sliderImagePath;

    public SliderImageModel() {
        sliderImagePath = "";
    }

    public SliderImageModel(String sliderImagePath) {
        this.sliderImagePath = sliderImagePath;
    }

    protected SliderImageModel(Parcel in) {
        sliderImagePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sliderImagePath);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<SliderImageModel> CREATOR = new Creator<SliderImageModel>() {
        @Override
        public SliderImageModel createFromParcel(Parcel in) {
            return new SliderImageModel(in);
        }

        @Override
        public SliderImageModel[] newArray(int size) {
            return new SliderImageModel[size];
        }
    };

    public String getSliderImagePath() {
        return sliderImagePath;
    }

    public void setSliderImagePath(String sliderImagePath) {
        this.sliderImagePath = sliderImagePath;
    }

    @Override
    public String toString() {
        return sliderImagePath;
    }
}