package com.raq.motori.android.customerapp.screens.temporary

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings

/**
 * Created by Manoj Sain on 19/01/24.
 */
object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Services",
            icon = Icons.Filled.Settings,
            route = "services"
        ),
        BottomNavItem(
            label = "Media",
            icon = Icons.Filled.PlayArrow,
            route = "media_center"
        )
        ,
        BottomNavItem(
            label = "About Us",
            icon = Icons.Filled.Person,
            route = "about_us"
        )
    )
}