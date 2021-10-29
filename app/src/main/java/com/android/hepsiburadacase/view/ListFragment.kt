package com.android.hepsiburadacase.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.android.hepsiburadacase.databinding.FragmentListBinding
import com.android.hepsiburadacase.utils.setBackgroundDefault
import com.android.hepsiburadacase.viewmodel.BooksViewModel
import com.android.hepsiburadacase.viewmodel.MovieListViewModel
import com.android.hepsiburadacase.viewmodel.MusicListViewModel


class ListFragment : Fragment() {
    private var selectedItem = -1 // ilk girdiğinde hiç bir kategoriye tıklanmamış olsun
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val movieListViewModel: MovieListViewModel by viewModels()
    private val musicListViewModel: MusicListViewModel by viewModels()
    private val booksViewModel: BooksViewModel by viewModels()
    private val rcylerViewMovieAndMusic = MovieAndMusicListAdapter(arrayListOf())
    private val rcylerViewBooks = BooksAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    p0?.let {
                        checkList(it)
                    }
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    p0?.let {
                        checkList(it)
                    }
                    return false
                }

            })

            btnMusic.setOnClickListener {
                selectedItem = 1
                it.setBackgroundColor(Color.RED)
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
            }
            binding.btnMovies.setOnClickListener {
                selectedItem = 0
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
            }
            btnBooks.setOnClickListener {
                selectedItem = 2
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
            }
            binding.btnApps.setOnClickListener {
                selectedItem = 3
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                recyclerView.adapter = null
            }

        }
    }

    private fun checkList(p0: String) {


        if (selectedItem == 1 && p0.length >= 2) {

            musicListViewModel.refreshData(p0, "musicTrack")
            observeMusicLiveData()
            binding.recyclerView.adapter = rcylerViewMovieAndMusic
        }

        if (selectedItem == 0 && p0.length >= 2) {
            movieListViewModel.refreshData(p0, "movie")
            observeMovieLiveData()
            binding.recyclerView.adapter = rcylerViewMovieAndMusic
        }
        if(selectedItem == 2 && p0.length >= 2) {
            booksViewModel.refreshData(p0,"audiobook")
            observeBooksLiveData()
            binding.recyclerView.adapter = rcylerViewBooks
        }

        if (p0.length < 2) {
            binding.recyclerView.adapter = null
        }

    }


    private fun observeBooksLiveData() {
        booksViewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.booksModelResults?.let { it1 -> rcylerViewBooks.updateBooksList(it1) }
            }
        })
    }

    private fun observeMusicLiveData() {
        musicListViewModel.music.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.movieAndMusicModelResults?.let { it1 ->
                    rcylerViewMovieAndMusic.updateMovieList(
                        it1
                    )
                }
            }
        })
    }

    private fun observeMovieLiveData() {
        movieListViewModel.movie.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.movieAndMusicModelResults?.let { it1 ->
                    rcylerViewMovieAndMusic.updateMovieList(
                        it1
                    )
                }
            }
        })
    }
}