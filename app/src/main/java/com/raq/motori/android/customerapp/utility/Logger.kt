package com.raq.motori.android.customerapp.utility

import android.util.Log
import com.raq.motori.android.customerapp.BuildConfig

/**
 * Created by Manoj Sain on 31/01/24.
 */
object Logger {

    fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message)
        }
    }
}