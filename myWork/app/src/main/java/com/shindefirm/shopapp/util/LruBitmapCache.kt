package com.shindefirm.shopapp.util

import kotlin.jvm.JvmOverloads
import com.shindefirm.shopapp.util.LruBitmapCache
import android.graphics.Bitmap
import androidx.collection.LruCache
import com.android.volley.toolbox.ImageLoader

class LruBitmapCache @JvmOverloads constructor(sizeInKiloBytes: Int = defaultLruCacheSize) :
    LruCache<String?, Bitmap>(sizeInKiloBytes), ImageLoader.ImageCache {
    protected fun sizeOf(key: String?, value: Bitmap): Int {
        return value.rowBytes * value.height / 1024
    }

    override fun getBitmap(url: String): Bitmap? {
        return get(url)
    }

    override fun putBitmap(url: String, bitmap: Bitmap) {
        put(url, bitmap)
    }

    companion object {
        val defaultLruCacheSize: Int
            get() {
                val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
                return maxMemory / 8
            }
    }
}