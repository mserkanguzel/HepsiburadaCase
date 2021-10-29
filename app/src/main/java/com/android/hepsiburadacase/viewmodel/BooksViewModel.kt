package com.android.hepsiburadacase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import com.android.hepsiburadacase.service.ItunesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class BooksViewModel : ViewModel() {
    val books = MutableLiveData<BooksModel>()
    private val itunesApiService = ItunesApiService()
    private val disposable = CompositeDisposable()
    fun refreshData(string: String, entity: String) {
        getData(string, entity)
    }
    private fun getData(string: String, entity: String) {

        disposable.add(
            itunesApiService.getBooksData(string,entity)
                .subscribeOn(Schedulers.newThread()) // async bir biçimde single objesine kayıt oluyoruz, arka plandaki thredi gösteriyoruz
                .observeOn(AndroidSchedulers.mainThread())// observe kullanıcıya gösterilecek yerde yapılıyor
                .subscribeWith(object : DisposableSingleObserver<BooksModel>() {
                    override fun onSuccess(t: BooksModel) {
                        books.value = t
                    }

                    override fun onError(e: Throwable) {
                    }

                }

                )
        )
    }
}