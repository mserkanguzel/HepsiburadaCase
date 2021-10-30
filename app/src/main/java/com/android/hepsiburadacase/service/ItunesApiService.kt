package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ItunesApiService {

    private val BASE_URL = "https://itunes.apple.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ItunesAPI::class.java)


    fun getMovieData(string: String, entity: String,limit : String): Single<MovieAndMusicModel> {
        return api.getMovie(string, entity,limit)
    }

    fun getMusicData(string: String, entity: String,limit :String): Single<MovieAndMusicModel> {
        return api.getMusic(string, entity,limit)
    }
    fun getBooksData(string: String, entity: String,limit : String) : Single<BooksModel> {
        return api.getBooks(string,entity,limit)
    }
    fun getAppsData(string: String, entity: String,limit : String) : Single<AppsModel> {
        return api.getApps(string,entity,limit)
    }


}