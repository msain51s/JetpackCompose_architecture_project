package com.raq.motori.android.customerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raq.motori.android.customerapp.home.presentation.HomeScreen

/**
 * Created by Manoj Sain on 10/01/24.
 */

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HOME.name) {
        composable(route = AppScreens.HOME.name) {
            HomeScreen {
              //  navController.navigate(AppScreens.LOGIN.name)
            }
        }
    }
}