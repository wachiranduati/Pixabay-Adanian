package com.example.pixabay_adanian.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixabayApiRetriever {
    private val service : PixabayService

    companion object {
        const val BASE_URL = "https://pixabay.com/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(PixabayService::class.java)
    }

    suspend fun retrieveSearchResults() = service.searchResults("24793770-af8b352b7a57d7870bd091393", "dog")
}