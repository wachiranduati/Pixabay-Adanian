package com.example.pixabay_adanian.persistence

import androidx.lifecycle.LiveData
import com.example.pixabay_adanian.models.Hit

class PixabayRepository(private val pixabayDao: PixabayDao){

    fun retrieveSearches(key: String) : LiveData<List<Hit>>{
        return pixabayDao.searchAll(key)
    }

    suspend fun addPixaImage(hit: Hit){
        pixabayDao.Insert(hit)
    }

}