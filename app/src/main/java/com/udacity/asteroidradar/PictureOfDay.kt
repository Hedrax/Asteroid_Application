package com.udacity.asteroidradar
import com.squareup.moshi.Json

data class PictureOfDay(
    @Json(name = "url")
    val url:String,
    @Json(name = "title")
    val title:String,
    @Json(name = "explanation")
    val description:String
)