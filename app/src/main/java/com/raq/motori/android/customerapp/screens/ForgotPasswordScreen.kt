package com.raq.motori.android.customerapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Created by Manoj Sain on 10/01/24.
 */

@Composable
fun ForgotPasswordScreen(onClick:() -> Unit){
   Text(
       text = "Forgot Password",
       modifier = Modifier.clickable {
           onClick()
       }
   )
}