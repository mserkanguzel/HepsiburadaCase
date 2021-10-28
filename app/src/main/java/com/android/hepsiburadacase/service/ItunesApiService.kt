package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.MovieAndMusicModel
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


    fun getMovieData() : Single<MovieAndMusicModel> {
        return api.getMovie()
    }
    fun getMusicData(string: String,entity : String): Single<MovieAndMusicModel> {
        return api.getMusic(string,entity)
    }

}