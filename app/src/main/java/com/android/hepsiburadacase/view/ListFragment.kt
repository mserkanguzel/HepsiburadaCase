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
import androidx.recyclerview.widget.RecyclerView
import com.android.hepsiburadacase.databinding.FragmentListBinding
import com.android.hepsiburadacase.utils.setBackgroundDefault
import com.android.hepsiburadacase.viewmodel.AppsViewModel
import com.android.hepsiburadacase.viewmodel.BooksViewModel
import com.android.hepsiburadacase.viewmodel.MovieListViewModel
import com.android.hepsiburadacase.viewmodel.MusicListViewModel


// selectedItem =
// 0 -> Movies
// 1 -> Music
// 2 -> Books
// 3 -> Apps

class ListFragment : Fragment() {
    var limit = 20
    var string = ""
    private var selectedItem = -1 // ilk girdiğinde hiç bir kategoriye tıklanmamış olsun
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val movieListViewModel: MovieListViewModel by viewModels()
    private val musicListViewModel: MusicListViewModel by viewModels()
    private val booksViewModel: BooksViewModel by viewModels()
    private val appsViewModel: AppsViewModel by viewModels()
    private val rcylerViewMovieAndMusic = MovieAndMusicListAdapter(arrayListOf())
    private val rcylerViewBooks = BooksAdapter(arrayListOf())
    private val rcylerViewApps = AppsAdapter(arrayListOf())


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
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!binding.recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE){
                   addLoadMore()
                }
            }

        })
        binding.apply {
            progressBar.visibility = View.GONE
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
                        string = p0
                        checkList(it)
                    }
                    return false
                }
            })
            selectedItemCheck()
            btnMusic.setOnClickListener {
                selectedItem = 1
                it.setBackgroundColor(Color.RED)
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
                searchViewSearch.visibility = View.VISIBLE
                searchViewSearch.setQuery("", false)
            }
            binding.btnMovies.setOnClickListener {
                selectedItem = 0
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
                searchViewSearch.visibility = View.VISIBLE
                searchViewSearch.setQuery("", false)
            }
            btnBooks.setOnClickListener {
                selectedItem = 2
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnApps.setBackgroundDefault()
                recyclerView.adapter = null
                searchViewSearch.visibility = View.VISIBLE
                searchViewSearch.setQuery("", false)

            }
            binding.btnApps.setOnClickListener {
                selectedItem = 3
                it.setBackgroundColor(Color.RED)
                btnMusic.setBackgroundDefault()
                btnMovies.setBackgroundDefault()
                btnBooks.setBackgroundDefault()
                recyclerView.adapter = null
                searchViewSearch.visibility = View.VISIBLE
                searchViewSearch.setQuery("", false)

            }
        }
    }
    // selectedItem =
// 0 -> Movies
// 1 -> Music
// 2 -> Books
// 3 -> Apps
    private fun addLoadMore() {
        limit+=20
        when (selectedItem) {
            1 -> {
                musicListViewModel.refreshData(string, "musicTrack",limit.toString())
            }
            0 -> {
                movieListViewModel.refreshData(string,"movie",limit.toString())
            }
            3 -> {
                appsViewModel.refreshData(string,"software",limit.toString())
            }
            2 -> {
                booksViewModel.refreshData(string,"audiobook",limit.toString())
            }
        }
    }

    // back to detail page for button color..
    private fun selectedItemCheck() {
        binding.apply {
            when (selectedItem) {
                0 -> {
                    btnMovies.setBackgroundColor(Color.RED)
                    searchViewSearch.visibility = View.VISIBLE
                }
                1 -> {
                    btnMusic.setBackgroundColor(Color.RED)
                    searchViewSearch.visibility = View.VISIBLE
                }
                2 -> {
                    btnBooks.setBackgroundColor(Color.RED)
                    searchViewSearch.visibility = View.VISIBLE
                }
                3 -> {
                    btnApps.setBackgroundColor(Color.RED)
                    searchViewSearch.visibility = View.VISIBLE
                }
                else -> {
                    searchViewSearch.visibility = View.GONE
                    btnMovies.setBackgroundDefault()
                    btnBooks.setBackgroundDefault()
                    btnApps.setBackgroundDefault()
                    btnMusic.setBackgroundDefault()
                }
            }
        }

    }

    private fun checkList(p0: String) {
        if (selectedItem == 1 && p0.length >= 2) {
            limit = 20
            musicListViewModel.refreshData(p0, "musicTrack",limit.toString())
            observeMusicLiveData()

            binding.recyclerView.adapter = rcylerViewMovieAndMusic

        }

        if (selectedItem == 0 && p0.length >= 2) {
            limit = 20
            movieListViewModel.refreshData(p0, "movie",limit.toString())
            observeMovieLiveData()
            binding.recyclerView.adapter = rcylerViewMovieAndMusic
        }
        if (selectedItem == 2 && p0.length >= 2) {
            limit = 20
            booksViewModel.refreshData(p0, "audiobook",limit.toString())
            observeBooksLiveData()
            binding.recyclerView.adapter = rcylerViewBooks
        }
        if (selectedItem == 3 && p0.length >= 2) {
            limit = 20
            appsViewModel.refreshData(p0, "software",limit.toString())
            observeAppsLiveData()
            binding.recyclerView.adapter = rcylerViewApps
        }


        if (p0.length < 2) {
            binding.recyclerView.adapter = null
        }
    }

    private fun observeAppsLiveData() {
        appsViewModel.apps.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.appsModelResults?.let { it1 ->
                    rcylerViewApps.updateAppsList(it1) }
            }
        })
    }

    private fun observeBooksLiveData() {
        booksViewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.booksModelResults?.let { it1 ->
                    rcylerViewBooks.updateBooksList(it1)
                }
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