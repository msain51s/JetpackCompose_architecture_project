package com.raq.motori.android.customerapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


/**
 * Created by Manoj Sain on 10/01/24.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(onClick:()->Unit){
        Text(
            text = "Registration",
            modifier = Modifier.clickable {
                onClick()
            }
        )
}