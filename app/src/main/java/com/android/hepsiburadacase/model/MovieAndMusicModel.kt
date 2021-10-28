package com.android.hepsiburadacase.model

import com.google.gson.annotations.SerializedName

// MovieModel has 2 elements, movieModelResults is list of true result in json.
data class MovieAndMusicModel(
    val resultCount: Int, // first element is int
    @SerializedName("results")
    val movieAndMusicModelResults: List<MovieAndMusicModelResult> // second element is list
)