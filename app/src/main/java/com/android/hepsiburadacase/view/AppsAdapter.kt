package com.android.hepsiburadacase.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.hepsiburadacase.R
import com.android.hepsiburadacase.databinding.ItemListBinding
import com.android.hepsiburadacase.model.AppsModelResult
import com.android.hepsiburadacase.model.BooksModelResult
import com.android.hepsiburadacase.utils.dateTimeParsing
import com.android.hepsiburadacase.utils.downloadImage
import com.android.hepsiburadacase.utils.placeHolderBuilder

class AppsAdapter(val appsList : ArrayList<AppsModelResult>) : RecyclerView.Adapter<AppsAdapter.AppsViewHolder>() {
    class AppsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list,parent,false)
        return AppsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppsViewHolder, position: Int) {
        with(holder){
            binding.name.text = appsList[position].trackName
            binding.date.text = appsList[position].releaseDate?.let { dateTimeParsing(it) }
            binding.price.text = appsList[position].formattedPrice.toString()
            binding.imageView.downloadImage(appsList[position].artworkUrl100, placeHolderBuilder(itemView.context))
            // set on click listener eklenecek
            itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(moviesAndMusicc = null,apps = appsList[position],books = null)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return appsList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateAppsList(newList: List<AppsModelResult>){
        appsList.clear()
        appsList.addAll(newList)
        notifyDataSetChanged()
    }
}