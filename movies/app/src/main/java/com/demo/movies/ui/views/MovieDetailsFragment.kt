package com.demo.movies.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.movies.AppConstants
import com.demo.movies.R
import com.demo.movies.ui.utils.loadFromUrl
import com.demo.movies.ui.utils.showSnackbar
import com.demo.movies.ui.views.MoviesFragment.Companion.MOVIE_ID_DATA_KEY
import com.demo.movies.viewmodels.MovieDetailsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_details.*
import timber.log.Timber
import javax.inject.Inject


/**
 * MovieDetailsFragment displays information about any selected movie.
 */
class MovieDetailsFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    private var movieId: Int = 0

    // region Fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { activity ->
            movieDetailsViewModel = ViewModelProvider(activity, viewModelFactory)
                .get(MovieDetailsViewModel::class.java)

            activity.intent?.extras?.let {
                movieId = it.getInt(MOVIE_ID_DATA_KEY)
            }

            observeMovieDetails()
            loadMovieDetails(movieId)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_save_movie.setOnClickListener{ view ->

            view.showSnackbar("Movie saved", R.color.colorPrimaryLight, Snackbar.LENGTH_LONG).show()

            movieDetailsViewModel.markMovieAsfavourite(activity!!.intent.extras!!.getInt(MOVIE_ID_DATA_KEY))
        }
    }

    // endregion Fragment

    // region Logic

    private fun observeMovieDetails(){
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

    private fun loadMovieDetails(movieId: Int) {

        movieDetailsViewModel.fetchMovieDetails(movieId)
        // TODO improvement: use Movie parcelable movie object instead
    }

    // endregion logic
}