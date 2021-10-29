package com.android.hepsiburadacase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import com.android.hepsiburadacase.service.ItunesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AppsViewModel : ViewModel() {
    val apps = MutableLiveData<AppsModel>()
    private val itunesApiService = ItunesApiService()
    private val disposable = CompositeDisposable()
    fun refreshData(string: String, entity: String) {
        getData(string, entity)
    }
    private fun getData(string: String, entity: String) {

        disposable.add(
            itunesApiService.getAppsData(string,entity)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AppsModel>() {
                    override fun onSuccess(t: AppsModel) {
                        apps.value = t
                    }
                    override fun onError(e: Throwable) {
                    }
                }
                )
        )
    }
}