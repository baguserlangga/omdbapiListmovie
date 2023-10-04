package com.example.brightonomdapimovie.service

import com.example.brightonomdapimovie.model.ResponseDetailMovie
import com.example.brightonomdapimovie.model.ResponseMovieListSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MoviesApi {
    @GET("s=the&apikey=fad1c149")
    fun getListMovie(@QueryMap params: Map<String, String>): Call<ResponseMovieListSearch>
    @GET("?apikey=fad1c149&")
    fun getDetailMovie(@Query("t") id: String?): Call<ResponseDetailMovie>
}