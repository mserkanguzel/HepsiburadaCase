package com.android.hepsiburadacase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import com.android.hepsiburadacase.service.ItunesApiService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    val books = MutableLiveData<BooksModel>()
    private val itunesApiService = ItunesApiService()
    fun refreshData(string: String, entity: String, limit: String) {
        getData(string, entity, limit)
    }

    private fun getData(string: String, entity: String, limit: String) {

        viewModelScope.launch {
            val response = itunesApiService.getBooksData(string, entity, limit)
            if (response.isSuccessful) {
                val data = response.body()
                books.value = data
            }
        }
    }
}