package com.android.hepsiburadacase.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hepsiburadacase.model.MovieAndMusicModel
import com.android.hepsiburadacase.service.ItunesApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    val movie = MutableLiveData<MovieAndMusicModel>()
    private val itunesApiService = ItunesApiService()
   fun refreshData(string: String, entity: String,limit : String) {
        getData(string, entity,limit)
    }

    private fun getData (string: String, entity: String,limit : String) {
        viewModelScope.launch {
            val response = itunesApiService.getMovieData(string, entity,limit)
            if(response.isSuccessful) {
                val data = response.body()
                movie.value = data
            }
        }
    }
    }
