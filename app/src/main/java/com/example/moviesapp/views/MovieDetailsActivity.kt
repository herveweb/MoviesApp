package com.example.moviesapp.views

import android.R
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviesapp.databinding.ActivityMovieDetailsBinding
import com.example.moviesapp.models.Movie
import com.example.moviesapp.viewmodels.MovieDetailsViewModel
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Herve Tchoufong
 * herveweb.com
 */
@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {
    companion object {
        const val thumbnailBaseUrl = "https://image.tmdb.org/t/p/w185"
        const val backDropImageBaseUrl = "https://image.tmdb.org/t/p/w500"
        const val MOVIE = "movie"
    }

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val movieJson = intent.getStringExtra(MOVIE)
        val movie: Movie? = Moshi.Builder().build().adapter(Movie::class.java).fromJson(movieJson)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (movie?.id != null) {
            updateUi(movie)
            lifecycleScope.launch {
                viewModel.getMovieDetails(movie.id)
                viewModel.uiState
                    .collect {
                        if (!it.isLoadingMovie && !it.movie.title.isNullOrEmpty()) {
                            updateUi(it.movie)
                        }
                    }
            }
        }
    }

    private fun updateUi(movie: Movie?) {
        binding.title.text = movie?.title
        binding.description.text = movie?.description
        val revenue = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            .format(movie?.revenue)
        binding.revenue.text = "$revenue USD"
        if (!movie?.releaseDate.isNullOrEmpty()) {
            val date =
                SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(movie?.releaseDate)
            binding.releaseDate.text =
                "${SimpleDateFormat("MMM d, yyyy").format(date)}"
        }
        if (!movie?.image.isNullOrEmpty()) {
            Glide.with(binding.posterImage.context)
                .load(thumbnailBaseUrl + movie?.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.posterImage)
        }
        if (!movie?.backDropImage.isNullOrEmpty()) {
            Glide.with(binding.backDropImage.context)
                .load(backDropImageBaseUrl + movie?.backDropImage)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.backDropImage)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}