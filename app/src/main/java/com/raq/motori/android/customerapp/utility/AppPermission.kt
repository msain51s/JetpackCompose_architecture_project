package com.raq.motori.android.customerapp.utility

import android.Manifest
import com.raq.motori.android.customerapp.R

/**
 * Created by Manoj Sain on 23/01/24.
 */
sealed class AppPermission(
    val permissionName: String, val requestCode: Int, val deniedMessageId: Int, val explanationMessageId: Int
) {
    companion object {
        val permissions: List<AppPermission> by lazy {
            listOf(
                WRITE_EXTERNAL_STORAGE
            )
        }
    }

    object WRITE_EXTERNAL_STORAGE : AppPermission(
        Manifest.permission.WRITE_EXTERNAL_STORAGE, 42,
        R.string.permission_required_text, R.string.permission_required_text
    )
}