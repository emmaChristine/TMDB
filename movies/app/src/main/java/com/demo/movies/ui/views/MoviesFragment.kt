package com.demo.movies.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.movies.R
import com.demo.movies.data.dto.Movie
import com.demo.movies.ui.adapter.MoviesAdapter
import com.demo.movies.ui.adapter.MoviesAdapterPaging
import com.demo.movies.ui.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.empty_movies_list.*
import kotlinx.android.synthetic.main.fragment_movies.*
import timber.log.Timber
import javax.inject.Inject


/**
 *  MoviesFragment displays a list of popular movies on TMDB.
 *  For the sake of simplicity we only display first page of results.
 *
 *  Real apps should use Paging library with paginated adapter.
 *
 */
class MoviesFragment : Fragment() {

    companion object {
        const val MOVIE_ID_DATA_KEY = "com.demo.movies.movie.datakey"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var moviesViewModel: MovieViewModel

    private lateinit var moviesAdapter: MoviesAdapter

    // TODO to use paginated adapter
    private lateinit var moviesAdapterPaginated: MoviesAdapterPaging

    private val moviesObserver = Observer<MutableList<Movie>> { moviesList ->
        moviesList?.let {
            if (moviesList.isNotEmpty()) {
                loading_indicator.visibility = View.GONE
                empty_movies_layout.visibility = View.GONE

                moviesAdapter.setData(moviesList)
                moviesViewModel.storePopularMovies(moviesList)
            }
            else {
                // no movies in repository
                Timber.d("No movies available.")
                loading_indicator.visibility = View.GONE
                empty_movies_layout.visibility = View.VISIBLE
            }
        }
    }

    // region Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movies, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            moviesViewModel = ViewModelProvider(it, viewModelFactory)[MovieViewModel::class.java]

            observeAdapter()
            loadMoviesList()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter(activity!!)

        initView()
    }


    // endregion Fragment

    // region render

    private fun initView() {
        movies_list.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        movies_list.adapter = moviesAdapter
    }

    private fun loadMoviesList() {
        loading_indicator.visibility = View.VISIBLE

        // Load first page
        // TODO Paginated adapter should be used
        moviesViewModel.fetchPopularMoviesPaginated(1)
    }
    // endregion render


    // region logic

    private fun observeAdapter() {
        moviesViewModel.popularMoviesLiveData.observe(this, moviesObserver)
    }


    // endregion logic
}