package com.raq.motori.android.customerapp.utility.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Manoj Sain on 06/02/24.
 */

@Composable
fun RMToggleButton(onClick: (langCode:String) -> Unit) {
    var checked by remember { mutableStateOf(true) }
    var languageText by remember { mutableStateOf("English") }
    var langCode by remember { mutableStateOf("en") }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            modifier = Modifier.height(15.dp),
            checked = checked,
            onCheckedChange = {
                checked = it
                if (languageText == "English") {
                    languageText = "Arabic"
                    langCode = "en"
                }
                else {
                    languageText = "English"
                    langCode = "ar"
                }
                onClick(langCode)
            }
        )

        RMTextSmall(
            modifier = Modifier.padding(5.dp),
            text = languageText)
    }
}

@Preview
@Composable
fun TogglePreview(){
    RMToggleButton {

    }
}