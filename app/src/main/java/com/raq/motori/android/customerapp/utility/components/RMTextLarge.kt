package com.raq.motori.android.customerapp.utility.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * Created by Manoj Sain on 23/01/24.
 */

@Composable
fun RMTextLarge(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge
) {
    /**
     * textStyle for customization purpose and could be pass from where it is calling
     */
//    fontSize: TextUnit = 18.sp,
//    fontFamily: FontFamily = FontFamily(
//    Font(R.font.lexend_tera_black, FontWeight.Black)
    Text(
        modifier= modifier,
        text = text,
        color = color,
        style = textStyle
    )
}