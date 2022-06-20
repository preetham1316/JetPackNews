package com.android.jetpacknews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.jetpacknews.feature.home.presentation.viewmodel.HomeScreenViewModel
import com.android.jetpacknews.feature.home.ui.HomeScreen
import com.android.jetpacknews.navigation.Screen
import com.android.jetpacknews.ui.theme.JetPackNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(Screen.HomeScreen.route) {
                            val viewModel = hiltViewModel<HomeScreenViewModel>()
                            HomeScreen(viewModel = viewModel)
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()
            )
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