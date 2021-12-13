package com.example.pixabay_adanian.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pixabay_adanian.models.Hit

@Dao
interface PixabayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(newHit: Hit)

    @Query("SELECT * FROM pixabay_table WHERE searchkey = :key ")
    fun searchAll(key: String) : LiveData<List<Hit>>
}