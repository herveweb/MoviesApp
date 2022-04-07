package com.example.moviesapp.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.moviesapp.R
import com.example.moviesapp.models.Movie

/**
 * Created by Herve Tchoufong
 * herveweb.com
 */

class MovieListAdapter(private val dataSet: List<Movie>, private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    companion object {
        const val thumbnailBaseUrl = "https://image.tmdb.org/t/p/w185"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.poster_image)
        val titleView: TextView = view.findViewById(R.id.title)
        val descriptionView: TextView = view.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.movie_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        val imageUrl = thumbnailBaseUrl + movie.image
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(viewHolder.imageView.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.imageView)
        }
        viewHolder.titleView.text = movie.title
        viewHolder.descriptionView.text = movie.description
        viewHolder.itemView.setOnClickListener { onClick(movie) }
    }

    override fun getItemCount() = dataSet.size
}
