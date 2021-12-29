package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryModel implements Parcelable {
    @SerializedName("Id")
    private String id;
    @SerializedName("Category")
    private String category;

    public CategoryModel() {
        id = "";
        category = "";
    }

    public CategoryModel(String id, String category) {
        this.id = id;
        this.category = category;
    }

    protected CategoryModel(Parcel in) {
        id = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}