package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.AppsModel
import com.android.hepsiburadacase.model.BooksModel
import com.android.hepsiburadacase.model.MovieAndMusicModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ItunesAPI {

    // Ex Url -> https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET("/search")
    suspend fun getMovie(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit: String
    ): Response<MovieAndMusicModel>

    @GET("/search")
    suspend fun getMusic(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit: String
    ): Response<MovieAndMusicModel>

    @GET("/search")

    suspend fun getBooks(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit: String
    ): Response<BooksModel>

    @GET("/search")
    suspend fun getApps(
        @Query("term") string: String,
        @Query("entity") entity: String,
        @Query("limit") limit: String
    ): Response<AppsModel>

}