package com.example.moviesapp.screens.movieslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.moviesapp.models.local.Movie
import com.example.moviesapp.navigation.NavigationDestinations

@Composable
fun MovieListScreen(viewModel: MovieListViewModel, navigateToMovieDetails: (String) -> Unit) {
    val viewState by viewModel.uiState.collectAsState()
    val lifecycleState = LocalLifecycleOwner.current.lifecycle.currentState
    when(viewState.navigateTo){
        is NavigationDestination.MovieDetails -> {
            val movieId = (viewState.navigateTo as NavigationDestination.MovieDetails).movieId
            navigateToMovieDetails(NavigationDestinations.MovieDetails.route.replace("{movieId}", movieId))
            viewModel.resetNavigation()
        }
        else -> {
            if (lifecycleState == androidx.lifecycle.Lifecycle.State.RESUMED) {
                MovieListView(
                    movieList = viewState.movieList,
                    onListItemClick = viewModel::onListItemClick
                )
            }
        }
    }
}

@Composable
fun MovieListView(
    movieList: List<Movie>,
    onListItemClick: (String) -> Unit
) {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        items(movieList) {
            MovieListItemView(
                movieId = "${it.id}",
                imageUrl = it.image.orEmpty(),
                title = it.title.orEmpty(),
                description = it.description.orEmpty(),
                onListItemClick = onListItemClick
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItemView(
    movieId: String,
    imageUrl: String,
    title: String,
    description: String,
    onListItemClick: (String) -> Unit
) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable(onClick = {onListItemClick(movieId)})
    ) {
        GlideImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier
                .padding(5.dp)
                .width(80.dp)
                .fillMaxHeight(),
        )
        Column(Modifier.wrapContentHeight()) {
            Text(text = title,
                 style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp))
            Text(
                text = description,
                style = TextStyle(color = Color.White),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    Divider(
        thickness = 2.dp,
        color = Color.Gray
    )
}