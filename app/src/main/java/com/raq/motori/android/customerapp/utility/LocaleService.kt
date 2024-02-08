package com.raq.motori.android.customerapp.utility

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

/**
 * Created by Manoj Sain on 06/02/24.
 */
object LocaleService {

    fun updateBaseContextLocale(context: Context,langCode:String): Context {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResourcesLocale(context, locale)
            return updateResourcesLocaleLegacy(context, locale)
        }
        return updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResourcesLocale(
        context: Context,
        locale: Locale
    ): Context? {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
     //FixMe:   updateAppTheme(context)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLocaleLegacy(
        context: Context,
        locale: Locale
    ): Context {
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        context.createConfigurationContext(configuration)
        //FixMe:    updateAppTheme(context)
        return context
    }
}