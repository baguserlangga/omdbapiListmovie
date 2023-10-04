package com.example.brightonomdapimovie.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            // you can also use Level.BODY for even more log information
            level = HttpLoggingInterceptor.Level.BASIC
        })
    val api : MoviesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }
}