package com.raq.motori.android.customerapp.utility.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder

/**
 * Created by Manoj Sain on 22/01/24.
 */

@Composable
fun ImageBuilder(image: Int,isSvgImage:Boolean = true) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if(isSvgImage)
            add(SvgDecoder.Factory())
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(image, imageLoader),
        contentDescription = null,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    )
}