package com.raq.motori.android.customerapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Manoj Sain on 10/01/24.
 */

@Composable
fun LoginScreen(userName:String,onClick:() -> Unit){
    Text(
        text = "Login - $userName",
        modifier = Modifier.clickable {
            onClick()
        }
    )
}