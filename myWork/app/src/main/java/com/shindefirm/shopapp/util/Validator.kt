package com.shindefirm.shopapp.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;

public class Validator {
    public static boolean mobileNumberValidation(String mobileNumber){
        if(mobileNumber.isEmpty()){
            return true;
        }

        Pattern pattern = Pattern.compile("^[1-9][0-9]{9}$");
        return !pattern.matcher(mobileNumber).matches();
    }

    public static boolean personNameValidation(String personName){
        if(personName.isEmpty()){
            return true;
        }

        if(personName.length() >= 70){
            return true;
        }

       /* Pattern pattern = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
        return !pattern.matcher(personName).matches();*/
        return false;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) &&  !Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
