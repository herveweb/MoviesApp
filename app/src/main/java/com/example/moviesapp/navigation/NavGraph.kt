package com.example.moviesapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviesapp.screens.moviedetails.MovieDetailsScreen
import com.example.moviesapp.screens.movieslist.MovieListScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = Modifier.padding(contentPadding)
    ) {
        composable(
            route = NavigationDestinations.MovieList.route,
            arguments = NavigationDestinations.MovieList.arguments
        ) { backStackEntry ->
            MovieListScreen(
                viewModel = hiltViewModel(),
                navigateToMovieDetails = { movieId -> navHostController.navigate(movieId) }
            )
        }
        composable(
            route = NavigationDestinations.MovieDetails.route,
            arguments = NavigationDestinations.MovieDetails.arguments
        ){ backStackEntry ->
            //val movieId = backStackEntry.arguments?.getString("movieId")
            MovieDetailsScreen(hiltViewModel())
        }
    }
}

object NavigationDestinations {
    val MovieList = Destination(
        route = "MovieList",
        arguments = listOf(
            navArgument("title") {
                type = NavType.StringType
                defaultValue = "Movie List"
            },
            navArgument("movieId") { type = NavType.StringType },
        )
    )
    val MovieDetails = Destination(
        route = "MovieDetails/{movieId}",
        arguments = listOf(
            navArgument("title") {
                type = NavType.StringType
                defaultValue = "Movie Details"
            }
        )
    )
}

data class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>
)