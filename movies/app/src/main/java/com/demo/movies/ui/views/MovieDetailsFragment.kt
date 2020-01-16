package com.demo.movies.ui.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.demo.movies.AppConstants
import com.demo.movies.R
import com.demo.movies.ui.utils.loadFromUrl
import com.demo.movies.ui.utils.showSnackbar
import com.demo.movies.ui.views.MoviesFragment.Companion.MOVIE_ID_DATA_KEY
import com.demo.movies.viewmodels.MovieDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_details.*
import timber.log.Timber

class MovieDetailsFragment: Fragment() {

    // Obtain ViewModel
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    // region Fragment

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.movieDetailsLiveData.observe(this, Observer { movie ->
            if (movie != null) {
                detail_image.loadFromUrl(AppConstants.TMDB_IMAGE_SERVICE_BASE_URL.plus(movie.poster_path))
                movieDate.text = movie.release_date
                movieRating.text = "%.2f".format(movie.vote_average)
                movieTitle.text = movie.title
            }
            else
                Timber.e("Movie details are empty!")
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // improvement: use Movie parcelable object instead
        if (savedInstanceState == null) {
           if (activity!!.intent.extras!!.containsKey(MOVIE_ID_DATA_KEY)) {
               movieDetailsViewModel.fetchMovieDetails(activity!!.intent.extras!!.getInt(MOVIE_ID_DATA_KEY))
           }
        }
        else {
            Timber.d(" MOVIE DETAILS already loaded")
        }

        fab_save_movie.setOnClickListener{ view ->

            view.showSnackbar("Movie saved", R.color.colorPrimaryLight, Snackbar.LENGTH_LONG).show()

            movieDetailsViewModel.markMovieAsfavourite(activity!!.intent.extras!!.getInt(MOVIE_ID_DATA_KEY))
        }
    }

    // endregion Fragment
}