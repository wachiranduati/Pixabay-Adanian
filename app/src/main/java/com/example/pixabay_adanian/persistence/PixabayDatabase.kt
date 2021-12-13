package com.example.pixabay_adanian.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pixabay_adanian.models.Hit
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Hit::class], exportSchema = false, version = 1)
abstract class PixabayDatabase() : RoomDatabase(){

    abstract fun pixabayDao() : PixabayDao

    companion object{
        @Volatile
        private var INSTANCE : PixabayDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : PixabayDatabase {
            var tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PixabayDatabase::class.java,
                    "pixabay_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
