package com.example.md003_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.movie_row.*
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieDetailsActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        Glide.with(imageViewCover.context)
            .load(getIntent().getStringExtra("poster"))
            .into(imageViewCover)
        textViewMovieName.text = getIntent().getStringExtra("title")
        textViewMovieDescription.text = getIntent().getStringExtra("plot")
        textViewRating.text = getIntent().getStringExtra("rating")

        textViewTrailer.setOnClickListener(){
            Toast.makeText(this, "Trailer", Toast.LENGTH_SHORT).show()
        }
    }
}