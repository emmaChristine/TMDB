package com.demo.movies.ui.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.movies.AppConstants.TMDB_IMAGE_SERVICE_BASE_URL
import com.demo.movies.R
import com.demo.movies.data.dto.Movie
import com.demo.movies.ui.utils.loadFromUrl
import com.demo.movies.ui.views.MovieDetailsActivity
import com.demo.movies.ui.views.MoviesFragment
import kotlinx.android.synthetic.main.movie_item_row.view.*
import java.util.*

// Paged list adapter
internal class MoviesAdapterPaging(private val activity: FragmentActivity) : PagedListAdapter<Movie, MoviesAdapterPaging.MoviesViewHolder>(MOVIE_COMPARATOR) {

    private var movieList: List<Movie> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(this.activity).inflate(R.layout.movie_item_row, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentMovie = movieList[position]
        holder.setData(currentMovie)
    }

    fun setData(movieList: List<Movie>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                newItem == oldItem
        }
    }


        inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun setData(movie: Movie) {
            itemView.movie_image_view.loadFromUrl(TMDB_IMAGE_SERVICE_BASE_URL.plus(movie.poster_path))

            itemView.setOnClickListener {
                showMovieDetails(movie)
            }
        }
    }

    fun showMovieDetails(movie: Movie) {
        @Suppress("UNCHECKED_CAST") val intent = Intent(activity, MovieDetailsActivity::class.java)
            .putExtra(MoviesFragment.MOVIE_ID_DATA_KEY, movie.id)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)

    }
}