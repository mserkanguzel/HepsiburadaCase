package com.android.hepsiburadacase.service

import com.android.hepsiburadacase.model.MovieAndMusicModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ItunesAPI {

   // Base Url -> https://itunes.apple.com/search?term=jack+johnson&entity=musicVideo
    @GET("/search?term=matrix&entity=movie")
    fun getMovie(): Single<MovieAndMusicModel>
    @GET("/search")
    fun getMusic(@Query("term") string: String,
                 @Query("entity") entity : String
                 ): Single<MovieAndMusicModel>

}