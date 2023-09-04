package com.example.studycompose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun CustomBottomNavigation(
    navController: NavHostController
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Screen.values().forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.name,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                ),
                onClick = {
                    navController.navigate(screen.name)
                },
                icon = {
                    Icon(
                        imageVector = if (screen.name == Screen.Home.name) Icons.Filled.Home else Icons.Filled.List,
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        text = screen.name,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            )
        }
    }
}

@Composable
fun NavHost(
    navController: NavHostController
) {
    NavHost(navController, startDestination = Screen.Home.name) {
        composable(Screen.Home.name) {
            val viewModel = hiltViewModel<MainViewModel>()
            HomeScreen(viewModel = viewModel)
        }
        composable(Screen.Library.name) { LibraryScreen() }
    }
}

enum class Screen() {
    Home,
    Library
}
