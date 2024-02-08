package com.raq.motori.android.customerapp.utility.extention

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import com.raq.motori.android.customerapp.utility.AppPermission

/**
 * Created by Manoj Sain on 23/01/24.
 */

fun ComponentActivity.checkPermission(context: Context?, permission: AppPermission) = run {
    context?.let {
        (ActivityCompat.checkSelfPermission(it, permission.permissionName
        ) == PermissionChecker.PERMISSION_GRANTED)
    } ?: false
}

fun ComponentActivity.shouldRequestPermissionRationale(permission: AppPermission) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission.permissionName)

fun ComponentActivity.requestAllPermissions(permission: AppPermission) {
    ActivityCompat.requestPermissions(this, arrayOf(permission.permissionName), permission.requestCode)
}