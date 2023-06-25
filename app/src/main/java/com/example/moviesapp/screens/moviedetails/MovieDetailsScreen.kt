package com.example.moviesapp.screens.moviedetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel) {
    val viewState by viewModel.uiState.collectAsState()
    /*when(viewState.navigateTo){
        is NavigationDestination.MovieDetails -> navHostController.navigate("MovieDetails")
    }*/
    MovieDetailsView(

    )
}

@Composable
fun MovieDetailsView() {
    Text(text = "Details", style = TextStyle(color = Color.Black))
}