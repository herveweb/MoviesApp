package com.example.moviesapp.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviesapp.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreensContainer(
    navHostController: NavHostController,
    startDestination: String
) {
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
            startDestination = startDestination,
            contentPadding = contentPadding
        )
    }
}