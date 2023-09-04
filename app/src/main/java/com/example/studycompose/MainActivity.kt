package com.example.studycompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studycompose.ui.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeAppTheme {
                Scaffold(
                    bottomBar = {
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
                ) {
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) {
                            val viewModel = hiltViewModel<MainViewModel>()
                            HomeScreen(viewModel = viewModel)
                        }
                        composable(Screen.Library.name) { LibraryScreen() }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyApp(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
    ) {
        LibraryScreen()
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun MyAppPreview() {
    ComposeAppTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}
