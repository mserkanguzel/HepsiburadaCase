package com.android.hepsiburadacase.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.hepsiburadacase.R
import com.android.hepsiburadacase.databinding.ItemListBinding
import com.android.hepsiburadacase.model.MovieAndMusicModelResult
import com.android.hepsiburadacase.utils.dateTimeParsing
import com.android.hepsiburadacase.utils.downloadImage
import com.android.hepsiburadacase.utils.placeHolderBuilder

class MovieAndMusicListAdapter(val movieAndMusicList: ArrayList<MovieAndMusicModelResult>) :
    RecyclerView.Adapter<MovieAndMusicListAdapter.MovieViewHolder>() {
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            if (movieAndMusicList[position].collectionName != null) {
                binding.name.text = movieAndMusicList[position].collectionName
            } else {
                binding.name.text = movieAndMusicList[position].trackName
            }
            binding.date.text =
                movieAndMusicList[position].releaseDate?.let { dateTimeParsing(it) } // letler null gelebilme durumuna karşı alınmış bir önlem
            binding.price.text = movieAndMusicList[position].collectionPrice.toString()
            binding.imageView.downloadImage(
                movieAndMusicList[position].artworkUrl100,
                placeHolderBuilder(itemView.context)
            )
            // set on click listener eklenecek
            itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                    movieAndMusicList[position],
                    apps = null,
                    books = null
                )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return movieAndMusicList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovieList(newList: List<MovieAndMusicModelResult>) {
        movieAndMusicList.clear()
        movieAndMusicList.addAll(newList)
        notifyDataSetChanged()
    }
}