package com.raq.motori.android.customerapp.screens.temporary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.raq.motori.android.customerapp.home.presentation.HomeScreen

/**
 * Created by Manoj Sain on 19/01/24.
 */

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(padding),

        builder = {

            // route : Home
            composable("home") {
                HomeScreen {

                }
            }

            // route : search
            composable("services") {
                OurServices()
            }

            // route : profile
            composable("media_center") {
                MediaCenter()
            }

            composable("about_us") {
                AboutUs()
            }
        })

}