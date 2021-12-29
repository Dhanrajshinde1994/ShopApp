package com.shindefirm.shopapp.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GeneralDataModel implements Parcelable {
    @SerializedName("data")
    private String dataState;
    @SerializedName("data1")
    private ArrayList<GeneralSettingModel> generalSettingList;
    @SerializedName("data2")
    private ArrayList<CategoryModel> categoryList;
    @SerializedName("data3")
    private ArrayList<PartyDataModel> partyDataList;
//    @SerializedName("data4")
    private ArrayList<SliderImageModel> sliderImageList;
    @SerializedName("data4")
    private ArrayList<ProgramModel> programModelList;
    @SerializedName("data5")
    private ArrayList<TwitTokenModel> twitTokenModelArrayList;

    public GeneralDataModel() {
        dataState = "";
        generalSettingList = new ArrayList<>();
        categoryList = new ArrayList<>();
        partyDataList = new ArrayList<>();
        sliderImageList = new ArrayList<>();
        programModelList = new ArrayList<>();
        twitTokenModelArrayList=new ArrayList<>();
    }

    public GeneralDataModel(String dataState, ArrayList<GeneralSettingModel> generalSettingList,
                            ArrayList<CategoryModel> categoryList,
                            ArrayList<PartyDataModel> partyDataList,
                            ArrayList<SliderImageModel> sliderImageList,
                            ArrayList<ProgramModel> programModelList,
                            ArrayList<TwitTokenModel> twitTokenModelArrayList) {
        this.dataState = dataState;
        this.generalSettingList = generalSettingList;
        this.categoryList = categoryList;
        this.partyDataList = partyDataList;
        this.sliderImageList = sliderImageList;
        this.programModelList = programModelList;
        this.twitTokenModelArrayList = twitTokenModelArrayList;
    }

    protected GeneralDataModel(Parcel in) {
        dataState = in.readString();
        generalSettingList = in.createTypedArrayList(GeneralSettingModel.CREATOR);
        categoryList = in.createTypedArrayList(CategoryModel.CREATOR);
        partyDataList = in.createTypedArrayList(PartyDataModel.CREATOR);
        sliderImageList = in.createTypedArrayList(SliderImageModel.CREATOR);
        programModelList = in.createTypedArrayList(ProgramModel.CREATOR);
        twitTokenModelArrayList = in.createTypedArrayList(TwitTokenModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataState);
        dest.writeTypedList(generalSettingList);
        dest.writeTypedList(categoryList);
        dest.writeTypedList(partyDataList);
        dest.writeTypedList(sliderImageList);
        dest.writeTypedList(programModelList);
        dest.writeTypedList(twitTokenModelArrayList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GeneralDataModel> CREATOR = new Creator<GeneralDataModel>() {
        @Override
        public GeneralDataModel createFromParcel(Parcel in) {
            return new GeneralDataModel(in);
        }

        @Override
        public GeneralDataModel[] newArray(int size) {
            return new GeneralDataModel[size];
        }
    };

    public String getDataState() {
        return dataState;
    }

    public void setDataState(String dataState) {
        this.dataState = dataState;
    }

    public ArrayList<GeneralSettingModel> getGeneralSettingList() {
        return generalSettingList;
    }

    public void setGeneralSettingList(ArrayList<GeneralSettingModel> generalSettingList) {
        this.generalSettingList = generalSettingList;
    }

    public ArrayList<CategoryModel> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    public ArrayList<PartyDataModel> getPartyDataList() {
        return partyDataList;
    }

    public void setPartyDataList(ArrayList<PartyDataModel> partyDataList) {
        this.partyDataList = partyDataList;
    }

    public ArrayList<SliderImageModel> getSliderImageList() {
        return sliderImageList;
    }

    public void setSliderImageList(ArrayList<SliderImageModel> sliderImageList) {
        this.sliderImageList = sliderImageList;
    }

    public ArrayList<ProgramModel> getProgramModelList() {
        return programModelList;
    }

    public void setProgramModelList(ArrayList<ProgramModel> programModelList) {
        this.programModelList = programModelList;
    }

    public ArrayList<TwitTokenModel> getTwitTokenModelArrayList() {
        return twitTokenModelArrayList;
    }

    public void setTwitTokenModelArrayList(ArrayList<TwitTokenModel> twitTokenModelArrayList) {
        this.twitTokenModelArrayList = twitTokenModelArrayList;
    }

    @Override
    public String toString() {
        return twitTokenModelArrayList.get(0).getTokenValue();
    }
}
