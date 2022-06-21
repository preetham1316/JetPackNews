package com.android.jetpacknews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel
import com.android.jetpacknews.feature.home.ui.HomeScreen
import com.android.jetpacknews.feature.splash.presentation.viewmodel.SplashViewModel
import com.android.jetpacknews.feature.splash.ui.SplashScreen
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.navigation.ScreenNavigator
import com.android.jetpacknews.navigation.ScreenRouter
import com.android.jetpacknews.ui.theme.JetPackNewsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        // Keep the splash screen visible for this Activity
        splashScreen.setKeepOnScreenCondition { false }
        actionBar?.hide()
        setContent {
            JetPackNewsNavGraph()
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun JetPackNewsNavGraph() {
        val navController = rememberNavController()
        JetPackNewsTheme {
            // A surface container using the 'background' color from the theme
            Scaffold(
                content = {
                    PrimaryNavigator(navController)
                },
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Composable
    private fun PrimaryNavigator(navController: NavHostController) {
        ScreenRouter(navController, screenNavigator)
        NavHost(
            navController = navController,
            startDestination = Screen.Splash().route
        ) {
            composable(Screen.Splash().route) {
                val viewModel = hiltViewModel<SplashViewModel>()
                SplashScreen(viewModel)
            }
            composable(Screen.Home().route) {
                val viewModel = hiltViewModel<HomeScreenViewModel>()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackNewsTheme {
        Greeting("Android")
    }
}