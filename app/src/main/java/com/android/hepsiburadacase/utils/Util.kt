package com.android.hepsiburadacase.utils

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.android.hepsiburadacase.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadImage(url: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.mipmap.ic_launcher_round) // glideddaki seçenekler
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeHolderBuilder(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
fun dateTimeParsing(date : String) : String {
    val fullDateTime = date.split("T")
    val dayMonthYear = fullDateTime[0]
    val lastSplit = dayMonthYear.split('-')
    val year = lastSplit[0];
    val month = lastSplit[1];
    val day = lastSplit[2];
    return "$month/$day/$year"
}

fun Button.setBackgroundDefault() {
    setBackgroundColor(Color.parseColor("#FF6200EE"))
}

