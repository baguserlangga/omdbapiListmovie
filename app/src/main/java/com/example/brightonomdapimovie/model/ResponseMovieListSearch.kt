package com.example.brightonomdapimovie.model

import com.google.gson.annotations.SerializedName

class ResponseMovieListSearch(
    @SerializedName("Search"       ) var MovieListModel       : ArrayList<MovieListModel> = arrayListOf(),
    @SerializedName("totalResults" ) var totalResults : String?           = null,
    @SerializedName("Response"     ) var Response     : String?           = null
)
