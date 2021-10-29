package com.android.hepsiburadacase.model

import com.google.gson.annotations.SerializedName

data class AppsModel(
    val resultCount: Int?,
    @SerializedName("results")
    val appsModelResults: List<AppsModelResult>?
)