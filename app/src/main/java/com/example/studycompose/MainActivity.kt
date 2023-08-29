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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.studycompose.ui.theme.ComposeAppTheme

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
                                    onClick = {
                                        navController.navigate(screen.name)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (screen.name == Screen.Home.name) Icons.Filled.Home else Icons.Filled.List,
                                            contentDescription = null
                                        )
                                    })
                            }
                        }
                    }
                ) {
                    NavHost(navController, startDestination = Screen.Home.name) {
                        composable(Screen.Home.name) { HomeScreen() }
                        composable(Screen.Library.name) { LibraryScreen() }
                    }
                }
            }
        }
    }
}

enum class Screen() {
    Home,
    Library
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyApp(
    modifier: Modifier = Modifier,
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            LibraryScreen()
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun MyAppPreview() {
    ComposeAppTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}
