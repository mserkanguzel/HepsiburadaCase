package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItunesApiService {

    private val BASE_URL = "https://itunes.apple.com"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ItunesAPI::class.java)


    suspend fun getMovieData(
        string: String,
        entity: String,
        limit: String
    ): Response<MovieAndMusicModel> {
        return api.getMovie(string, entity, limit)
    }

    suspend fun getMusicData(
        string: String,
        entity: String,
        limit: String
    ): Response<MovieAndMusicModel> {
        return api.getMusic(string, entity, limit)
    }

    suspend fun getBooksData(string: String, entity: String, limit: String): Response<BooksModel> {
        return api.getBooks(string, entity, limit)
    }

    suspend fun getAppsData(string: String, entity: String, limit: String): Response<AppsModel> {
        return api.getApps(string, entity, limit)
    }


}