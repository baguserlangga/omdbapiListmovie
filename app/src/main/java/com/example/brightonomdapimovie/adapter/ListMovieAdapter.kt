package com.example.brightonomdapimovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brightonomdapimovie.databinding.ItemListMovieBinding
import com.example.brightonomdapimovie.model.MovieListModel

class ListMovieAdapter(val context: Context, val result : ArrayList <MovieListModel>): RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMovieBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item  = result[position]

        holder.binding.textViewTitle.text = item.Title
        holder.binding.textViewYear.text = item.Year
//        holder.binding.textViewYear.setOnClickListener{
////            val intent = Intent(context, MovieByGenreActivity::class.java)
////            intent.putExtra("genreId", item.id.toString())
////            context.startActivity(intent)
//
//
//        }

    }

    override fun getItemCount(): Int {
        return result.size
    }
}