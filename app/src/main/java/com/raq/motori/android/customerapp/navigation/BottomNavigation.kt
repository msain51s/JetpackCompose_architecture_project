package com.raq.motori.android.customerapp.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.raq.motori.android.customerapp.screens.temporary.Constants
import com.raq.motori.android.customerapp.ui.theme.Purple80

/**
 * Created by Manoj Sain on 19/01/24.
 */


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = Purple80
    ) {

        // observe the backstack
        val navBackStackEntry = navController.currentBackStackEntryAsState().value

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.label,
                        tint = Color.White)
                },

                // label
                label = {
                    Text(
                        text = navItem.label,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}