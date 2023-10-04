package com.example.brightonomdapimovie

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brightonomdapimovie.adapter.ListMovieAdapter
import com.example.brightonomdapimovie.databinding.ActivityMainBinding
import com.example.brightonomdapimovie.model.MovieListModel
import com.example.brightonomdapimovie.model.ResponseMovieListSearch
import com.example.brightonomdapimovie.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val listMovies = arrayListOf<MovieListModel>()
    var idmovie :Int?=null
    //    val  lm = LinearLayoutManager(this)
    var page = 1
    private lateinit var movieAdapter : ListMovieAdapter

    var loading = true
    var pastItemsVisible: Int = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    lateinit var lm  : LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        prepareRecyclerView()
        getMovieList(page)

        val sharedPreference =  getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        sharedPreference.edit().putString(getString(R.string.shrared_pref_searchMovie),"the").commit()
        var search = sharedPreference.getString(getString(R.string.shrared_pref_searchMovie),"")
        Log.d("TAGihan", "onCreate: " +search)
        setContentView(binding.root)
    }
    private fun prepareRecyclerView() {
        lm = LinearLayoutManager(this@MainActivity)

        movieAdapter = ListMovieAdapter(this@MainActivity,listMovies)

        binding.mRecyclerView.setHasFixedSize(true)
        binding.mRecyclerView.layoutManager = lm

        binding.mRecyclerView.adapter = movieAdapter


        binding.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = lm.getChildCount()
                    totalItemCount = lm.getItemCount()
                    pastItemsVisible = lm.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastItemsVisible >= totalItemCount) {
                            loading = false
                            Log.v("...", "Last Item !")
                            loadmore()
                            // Do pagination.. i.e. fetch new data
                            loading = true
                        }
                    }
                }
            }
        })


    }
    fun loadmore()
    {
        page+=1
        getMovieList(page)
    }
    fun getMovieList(page:Int) {
        val params: MutableMap<String, String> = HashMap()

        params["page"] = page.toString()

        RetrofitInstance.api.getListMovie(params).enqueue(object  :
            Callback<ResponseMovieListSearch> {
            override fun onResponse(call: Call<ResponseMovieListSearch>, response: Response<ResponseMovieListSearch>) {
                if (response.body()!=null){
                    listMovies.addAll(response.body()!!.MovieListModel)
                    Log.d(ContentValues.TAG, "inionResponse: "  + response.body()!!.totalResults)
                    movieAdapter.notifyDataSetChanged()
                }
                else{
                    Log.d("TAGihan", response.code().toString())

                }
            }
            override fun onFailure(call: Call<ResponseMovieListSearch>, t: Throwable) {

                Log.d("TAGihan", t.message.toString())
            }
        })
    }

}