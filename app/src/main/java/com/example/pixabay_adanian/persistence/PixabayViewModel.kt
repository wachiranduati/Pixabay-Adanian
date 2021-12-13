package com.example.pixabay_adanian.persistence

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.pixabay_adanian.models.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PixabayViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : PixabayRepository

    init {
        val pixabayDao = PixabayDatabase.getDatabase(application, viewModelScope).pixabayDao()
        repository = PixabayRepository(pixabayDao)
    }

    fun addImage(hit: Hit) = viewModelScope.launch(
        Dispatchers.IO) {
        repository.addPixaImage(hit)
    }


    fun getSearches(key : String) : LiveData<List<Hit>> {
        return repository.retrieveSearches(key)
    }

}