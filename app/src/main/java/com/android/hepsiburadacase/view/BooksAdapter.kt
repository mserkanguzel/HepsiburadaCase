package com.android.hepsiburadacase.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.hepsiburadacase.R
import com.android.hepsiburadacase.databinding.ItemListBinding
import com.android.hepsiburadacase.model.BooksModelResult
import com.android.hepsiburadacase.utils.dateTimeParsing
import com.android.hepsiburadacase.utils.downloadImage
import com.android.hepsiburadacase.utils.placeHolderBuilder

class BooksAdapter(val booksList: ArrayList<BooksModelResult>) :
    RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {
    class BooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemListBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return BooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        with(holder) {
            binding.name.text = booksList[position].collectionName
            binding.date.text = booksList[position].releaseDate?.let { dateTimeParsing(it) }
            binding.price.text = booksList[position].collectionPrice.toString()
            binding.imageView.downloadImage(
                booksList[position].artworkUrl100,
                placeHolderBuilder(itemView.context)
            )
            itemView.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                    moviesAndMusicc = null,
                    apps = null,
                    books = booksList[position]
                )
                Navigation.findNavController(it).navigate(action)
            }
            // set on click listener eklenecek
        }
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateBooksList(newList: List<BooksModelResult>) {
        booksList.clear()
        booksList.addAll(newList)
        notifyDataSetChanged()
    }
}