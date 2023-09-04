package com.example.studycompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
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
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
        Screen.values().forEach { screen ->
            NavigationBarItem(
                selected = true,
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomNavHost() {
    val navHostController = rememberNavController()
    Scaffold {innerPadding->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navHostController,
            startDestination = Screen.Home.name
        ) {
            composable(Screen.Home.name) {
                val viewModel = hiltViewModel<MainViewModel>()
                HomeScreen(viewModel = viewModel, navHostController = navHostController)
            }
            composable(Screen.Library.name) {
                LibraryScreen(
                    navHostController = navHostController
                )
            }
        }
    }
}

enum class Screen() {
    Home,
    Library
}
