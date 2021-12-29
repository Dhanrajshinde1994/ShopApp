package com.shindefirm.shopapp.util

import android.content.Context
import android.util.Log
import com.shindefirm.shopapp.util.AppUtils.isValidString
import android.widget.Toast
import com.shindefirm.shopapp.util.Logger.LogEnum
import com.shindefirm.shopapp.util.AppUtils

object Logger {
    @JvmStatic
    fun showToast(
        context: Context,
        message: String,
        isShortTime: Boolean
    ) {
        Toast.makeText(context, message, if (isShortTime) Toast.LENGTH_SHORT else Toast.LENGTH_LONG)
            .show()
        setLog(message, LogEnum.INFORMATION)
    }

    @JvmStatic
    fun setLog(message: String, logEnum: LogEnum) {
        var message = message
        if (!isValidString(message)) {
            message = "error"
        }
        when (logEnum) {
            LogEnum.DEBUG -> Log.d(Constants.LOG_TAG, message)
            LogEnum.ERROR -> Log.e(Constants.LOG_TAG, message)
            LogEnum.VERBOSE -> Log.v(Constants.LOG_TAG, message)
            LogEnum.INFORMATION -> Log.i(Constants.LOG_TAG, message)
            LogEnum.WARNING -> Log.w(Constants.LOG_TAG, message)
        }
    }

    enum class LogEnum {
        DEBUG, INFORMATION, ERROR, VERBOSE, WARNING
    }
}