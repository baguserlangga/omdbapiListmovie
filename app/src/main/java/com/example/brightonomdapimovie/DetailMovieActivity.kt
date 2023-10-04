package com.example.brightonomdapimovie

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.brightonomdapimovie.databinding.ActivityDetailMovieBinding
import com.example.brightonomdapimovie.model.ResponseDetailMovie
import com.example.brightonomdapimovie.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMovieActivity : AppCompatActivity() {
    lateinit var binding:ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        getDetailMovie("tt0032390")


        setContentView(binding.root)
    }
    fun getDetailMovie(idmovie :String) {
        RetrofitInstance.api.getDetailMovie(idmovie.toString()).enqueue(object  :
            Callback<ResponseDetailMovie> {
            override fun onResponse(call: Call<ResponseDetailMovie>, response: Response<ResponseDetailMovie>) {
                if (response.body()!=null){

                    binding.textViewTitleMovies.text = response.body()!!.Title
                    binding.textViewTitleMovies.text = response.body()!!.Title
                    Glide.with(binding.imageViewPoster)
                        .load("http://img.omdbapi.com/?apikey=fad1c149" +response.body()!!.Poster)
                        .into(binding.imageViewPoster)
                    Log.d("asdasd", "onResponse: " +response.body()!!.Response)
                }
                else{
                    Toast.makeText(this@DetailMovieActivity, "something wrong null result", Toast.LENGTH_SHORT).show()

                    Log.d("TAGihan", response.code().toString())

                }
            }
            override fun onFailure(call: Call<ResponseDetailMovie>, t: Throwable) {
                Toast.makeText(this@DetailMovieActivity, "something wrong", Toast.LENGTH_SHORT).show()
                Log.d("TAGihan", t.message.toString())
            }
        })
    }


}