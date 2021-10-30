package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ItunesAPI {

    // Ex Url -> https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET("/search")
    fun getMovie(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit : String
    ): Single<MovieAndMusicModel>

    @GET("/search")
    fun getMusic(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit : String
    ): Single<MovieAndMusicModel>
    @GET("/search")

    fun getBooks(
        @Query("term") string : String,
        @Query("entity") entity: String,
        @Query("limit") limit : String
            ) : Single<BooksModel>
    @GET("/search")
    fun getApps(
        @Query("term") string : String,
        @Query("entity") entity: String,
        @Query("limit") limit : String
        ) : Single<AppsModel>

}