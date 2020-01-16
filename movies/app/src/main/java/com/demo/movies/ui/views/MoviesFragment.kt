package com.demo.movies.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.movies.R
import com.demo.movies.ui.adapter.MoviesAdapter
import com.demo.movies.ui.adapter.MoviesAdapterPaging
import com.demo.movies.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.empty_movies_list.*
import kotlinx.android.synthetic.main.fragment_movies.*
import timber.log.Timber

class MoviesFragment : Fragment() {

    companion object {
        const val MOVIE_ID_DATA_KEY = "com.demo.movies.movie.datakey"
    }

    // Obtain ViewModel
    private lateinit var moviesViewModel: MovieViewModel


    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var moviesAdapterPaginated: MoviesAdapterPaging

    // region Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        moviesViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        observeAdapter()

        // Paginated adapter is not yet finalized
        //observePaginatedAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter(activity!!)

        initView()
        loadMoviesList()
    }

    // endregion Fragment

    // region render

    private fun initView() {
        movies_list.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        movies_list.adapter = moviesAdapter

        // Paginated adapter is not yet finalized
        //movies_list.adapter = moviesAdapterPaginated
    }

    private fun loadMoviesList() {
        loading_indicator.visibility = View.VISIBLE

        // Paginated adapter is not yet finalized
        moviesViewModel.fetchPopularMoviesPaginated(1)
    }
    // endregion render


    // region logic

    private fun observeAdapter() {
        moviesViewModel.popularMoviesLiveData.observe(this, Observer { moviesList ->
            if (moviesList.isNotEmpty()) {
                loading_indicator.visibility = View.GONE
                empty_movies_layout.visibility = View.GONE

                moviesAdapter.setData(moviesList)
                moviesViewModel.storePopularMovies(moviesList)
            }
            else {
                // no movies in repository
                Timber.d("No movies available.")
                empty_movies_layout.visibility = View.VISIBLE
            }
        })
    }

    private fun observePaginatedAdapter() {
    }

    // endregion logic
}