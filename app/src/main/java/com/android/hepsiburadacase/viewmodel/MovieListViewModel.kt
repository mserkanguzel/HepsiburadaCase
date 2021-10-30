package com.android.hepsiburadacase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import com.android.hepsiburadacase.service.ItunesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieListViewModel : ViewModel() {
    val movie = MutableLiveData<MovieAndMusicModel>()
    private val itunesApiService = ItunesApiService()
    private val disposable = CompositeDisposable()
    fun refreshData(string: String, entity: String,limit : String) {
        getData(string, entity,limit)
    }

    private fun getData(string: String, entity: String,limit : String) {

        disposable.add(
            itunesApiService.getMovieData(string, entity,limit)
                .subscribeOn(Schedulers.newThread()) // async bir biçimde single objesine kayıt oluyoruz, arka plandaki thredi gösteriyoruz
                .observeOn(AndroidSchedulers.mainThread())// observe kullanıcıya gösterilecek yerde yapılıyor
                .subscribeWith(object : DisposableSingleObserver<MovieAndMusicModel>() {
                    override fun onSuccess(t: MovieAndMusicModel) {
                        movie.value = t

                    }

                    override fun onError(e: Throwable) {
                    }
                })
        )
    }
}