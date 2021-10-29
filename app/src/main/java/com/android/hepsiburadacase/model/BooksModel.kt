package com.android.hepsiburadacase.model

import com.google.gson.annotations.SerializedName

data class BooksModel(
    val resultCount: Int?,
    @SerializedName("results")
    val booksModelResults: List<BooksModelResult>?
)