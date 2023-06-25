package com.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviesapp.navigation.AppNavHost
import com.example.moviesapp.navigation.NavigationDestinations
import com.example.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainView() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){
    MoviesAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navHostController = rememberNavController()
            val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
            val title = currentBackStackEntry?.arguments?.getString("title")
            /*LaunchedEffect(navHostController) {
                navHostController.currentBackStackEntryFlow.collect { backStackEntry ->
                    backStackEntry.destination.label
                }
            }*/
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = title.toString()) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
                    )
                }
            ) { contentPadding ->
                AppNavHost(
                    navHostController = navHostController,
                    startDestination = NavigationDestinations.MovieList.route,
                    contentPadding = contentPadding
                )
            }
        }
    }
}