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
import com.android.hepsiburadacase.viewmodel.MovieListViewModel
import com.android.hepsiburadacase.viewmodel.MusicListViewModel


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val movieListViewModel: MovieListViewModel by viewModels()
    private val musicListViewModel: MusicListViewModel by viewModels()

    private val rcylerView = MovieAndMusicListAdapter(arrayListOf())
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
            searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {

                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if(p0?.length!! >= 2) {

                        musicListViewModel.refreshData(p0,"musicTrack")
                        observeMusicLiveData()
                        recyclerView.adapter = rcylerView
                    }
                    if(p0.length < 2) {
                        recyclerView.adapter = null
                    }
                    return false
                }

            })

            btnMusic.setOnClickListener {
                it.setBackgroundColor(Color.RED)
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
               // musicListViewModel.refreshData()
                observeMusicLiveData()
                recyclerView.adapter = rcylerView
            }
            binding.btnMovies.setOnClickListener {
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                movieListViewModel.refreshData()
                observeMovieLiveData()
                recyclerView.adapter = rcylerView
            }
            btnBooks.setOnClickListener {
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null // tekrar basildiginda listenin sifirlanmasi icin
            }
            binding.btnApps.setOnClickListener {
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                recyclerView.adapter = null
            }
            recyclerView.layoutManager = GridLayoutManager(context, 2)

        }


    }

    private fun observeMusicLiveData() {
        musicListViewModel.music.observe(viewLifecycleOwner, Observer {
            it?.let {
                rcylerView.updateMovieList(it.movieAndMusicModelResults)
            }
        })
    }

    private fun observeMovieLiveData() {
        movieListViewModel.movie.observe(viewLifecycleOwner, Observer {
            it?.let {
                rcylerView.updateMovieList(it.movieAndMusicModelResults)
            }
        })
    }
}