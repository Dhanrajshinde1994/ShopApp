package com.shindefirm.shopapp.util

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

object Validator {
    fun mobileNumberValidation(mobileNumber: String): Boolean {
        if (mobileNumber.isEmpty()) {
            return true
        }
        val pattern = Pattern.compile("^[1-9][0-9]{9}$")
        return !pattern.matcher(mobileNumber).matches()
    }

    fun personNameValidation(personName: String): Boolean {
        if (personName.isEmpty()) {
            return true
        }
        return if (personName.length >= 70) {
            true
        } else false

        /* Pattern pattern = Pattern.compile("^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$");
        return !pattern.matcher(personName).matches();*/
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && !Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}