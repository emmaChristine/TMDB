package com.demo.movies.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.demo.movies.R
import com.demo.movies.ui.adapter.MoviesAdapter
import com.demo.movies.ui.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.empty_movies_list.*
import kotlinx.android.synthetic.main.fragment_movies.*
import timber.log.Timber
import javax.inject.Inject


/**.
 * Local list of favourite movies
 *
 */
class MyMoviesFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var moviesViewModel: MovieViewModel

    private lateinit var moviesAdapter: MoviesAdapter

    // region Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeAdapter()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        moviesViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MovieViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_my_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesAdapter = MoviesAdapter(activity!!)

        initView()
    }

    override fun onResume() {
        super.onResume()

        loadMoviesList()
    }
    // endregion Fragment

    // region render

    private fun initView() {
        movies_list.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        movies_list.adapter = moviesAdapter
    }

    private fun loadMoviesList() {
        moviesViewModel.fetchMyMovies()
    }
    // endregion render


    // region logic

    private fun observeAdapter() {

        moviesViewModel.myMoviesLiveData.observe(this, Observer { moviesList ->
            if (moviesList.isNotEmpty()) {
                empty_movies_layout.visibility = View.GONE
                moviesAdapter.setData(moviesList)
            }
            else {
                // no movies in repository
                Timber.d("No favourite movies available.")
                empty_movies_layout.visibility = View.VISIBLE
            }

        })
    }

    // endregion logic

}