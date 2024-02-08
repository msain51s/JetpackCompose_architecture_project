package com.raq.motori.android.customerapp.base.presentation

import android.content.Context
import androidx.activity.ComponentActivity
import com.raq.motori.android.customerapp.utility.LocaleService
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Manoj Sain on 06/02/24.
 */
@AndroidEntryPoint
open class BaseActivity : ComponentActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        newBase?.let { LocaleService.updateBaseContextLocale(it, setLanguage()) }
    }

    open fun setLanguage(langCode: String = "en"): String {
        return langCode
    }
}