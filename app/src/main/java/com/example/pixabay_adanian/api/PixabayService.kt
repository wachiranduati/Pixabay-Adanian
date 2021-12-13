package com.example.pixabay_adanian.api

import com.example.pixabay_adanian.models.PixaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
//    https://pixabay.com/api/?key=24793770-af8b352b7a57d7870bd091393&q=dog&image_type=photo&pretty=true
    @GET("api/?image_type=photo")
    suspend fun searchResults(@Query("key") api: String, @Query("q") search: String) : PixaResponse
}