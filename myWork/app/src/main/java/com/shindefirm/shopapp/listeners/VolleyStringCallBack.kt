package com.shindefirm.shopapp.listeners

interface VolleyStringCallBack {
    fun getResponseString(dataString: String?, tag: String?)
    fun getErrorString(error: String?)
}