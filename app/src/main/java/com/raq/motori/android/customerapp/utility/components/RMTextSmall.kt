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
fun RMTextSmall(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = textStyle
    )
}