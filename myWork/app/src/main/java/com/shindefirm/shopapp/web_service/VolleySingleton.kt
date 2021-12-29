package com.shindefirm.shopapp.web_service

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import com.shindefirm.shopapp.web_service.VolleySingleton
import kotlin.jvm.Synchronized
import android.graphics.Bitmap
import androidx.collection.LruCache
import com.android.volley.Request
import com.shindefirm.shopapp.util.LruBitmapCache

class VolleySingleton private constructor(private var mContext: Context) {
    private var mRequestQueue: RequestQueue?

    /**
     * Get image loader.
     *
     * @return ImageLoader
     */
    val imageLoader: ImageLoader// getApplicationContext() is key, it keeps you from leaking the
    // Activity or BroadcastReceiver if someone passes one in.
    /**
     * Get current request queue.
     *
     * @return RequestQueue
     */
    val requestQueue: RequestQueue?
        get() {
            if (mRequestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                mRequestQueue = Volley.newRequestQueue(mContext.applicationContext)
            }
            return mRequestQueue
        }

    /**
     * Add new request depend on type like string, json object, json array request.
     *
     * @param req new request
     * @param <T> request type
    </T> */
    fun <T> addToRequestQueue(req: Request<T>?) {
        requestQueue!!.add(req)
    }

    companion object {
        private var mInstance: VolleySingleton? = null

        /**
         * Singleton construct design pattern.
         *
         * @param context parent context
         * @return single instance of VolleySingleton
         */
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): VolleySingleton? {
            if (mInstance == null) {
                mInstance = VolleySingleton(context)
            }
            return mInstance
        }
    }

    /**
     * Private constructor, only initialization from getInstance.
     *
     * @param context parent context
     */
    init {
        mRequestQueue = requestQueue
        imageLoader = ImageLoader(mRequestQueue,
            object : ImageLoader.ImageCache {
                private val cache: LruBitmapCache = LruBitmapCache(100)
                override fun getBitmap(url: String): Bitmap? {
                    return cache[url]
                }

                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            })
    }
}