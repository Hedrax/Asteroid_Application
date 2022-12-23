package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.moshi.MoshiConverterFactory

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

interface AsteroidApi {
    @GET("neo/rest/v1/feed")
    suspend fun getProperties(
        @Query("start_date")
        start_data:String,
        @Query("end_date")
        end_date:String,
        @Query("api_key")
        api_key:String
    ):String
    @GET("planetary/apod")
    suspend fun getPic(
        @Query("api_key")
        api_key:String
    ):PictureOfDay
}

object Network{
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val service = retrofit.create(AsteroidApi::class.java)
}