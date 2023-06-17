package com.example.moviesapp.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ActivityMovieListBinding
import com.example.moviesapp.models.Movie
import com.example.moviesapp.viewmodels.MovieListViewModel
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {
    companion object {
        const val MOVIE = "movie"
    }
    private lateinit var binding: ActivityMovieListBinding
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter
    private var movieList = arrayListOf<Movie>()
    private val linearLayoutManager = LinearLayoutManager(this)
    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        movieListAdapter = MovieListAdapter(movieList) { movie -> adapterOnClick(movie) }
        binding.recyclerView.adapter = movieListAdapter

        binding.recyclerView.layoutManager = linearLayoutManager
        lifecycleScope.launch {
            viewModel.uiState
                .collect {
                    binding.progressBar.isVisible = it.isLoadingMovies
                    if(!it.isLoadingMovies && !it.errorMessage.isNullOrEmpty()){
                        Toast.makeText(applicationContext, it.errorMessage, Toast.LENGTH_LONG)
                        viewModel.clearErrorMessages()
                    }else if(!it.isLoadingMovies && it.movies.isNotEmpty()) {
                        movieList.addAll(it.movies)
                        movieListAdapter.notifyDataSetChanged()
                    }
                }
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    if(!viewModel.uiState.value.isLoadingMovies) {
                        lifecycleScope.launch {
                            viewModel.getMovies()
                        }
                    }
                }
            }

        })
    }
    private fun adapterOnClick(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity()::class.java)
        intent.putExtra(MOVIE, Moshi.Builder().build().adapter(Movie::class.java).toJson(movie))
        startActivity(intent)
    }
}