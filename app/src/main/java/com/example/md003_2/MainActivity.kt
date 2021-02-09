package com.example.md003_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.md003_2.data.api.ApiHelper
import com.example.md003_2.data.api.RetrofitBuilder
import com.example.md003_2.data.model.Movie
import com.example.md003_2.ui.main.adapter.MainAdapter
import com.example.md003_2.ui.main.adapter.OnMovieClickListener
import com.example.md003_2.ui.main.viewmodel.MainViewModel
import com.example.md003_2.ui.main.viewmodel.ViewModelFactory
import com.example.md003_2.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMovieClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie_titles = listOf("Sully", "1917", "Tenet", "The Prestige", "Moana", "New York", "Soul", "The Family Man", "Annabelle", "Die Hard", "War", "The Imitation Game")

        setupViewModel()
        setupUI()
        val iterator = movie_titles.iterator()
        iterator.forEach {
            setupObservers(it)
        }
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf(), this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers(movie: String) {
        viewModel.getMovie(movie).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        resource.data?.let { movies -> retrieveList(movies) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(movies: Movie) {
        adapter.apply {
            addMovies(movies)
            notifyDataSetChanged()
        }
    }

    override fun onItemClick(movie: Movie, position: Int) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("plot", movie.plot)
        intent.putExtra("poster", movie.poster)
        intent.putExtra("rating", movie.ratings[0].value)
        startActivity(intent)
    }
}