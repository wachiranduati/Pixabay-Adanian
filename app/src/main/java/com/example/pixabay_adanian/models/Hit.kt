package com.example.pixabay_adanian.models

import android.os.Parcelable
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "pixabay_table")
@Parcelize
data class Hit(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    @PrimaryKey
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val views: Int,
    val webformatHeight: Int,
    val webformatURL: String,
    val webformatWidth: Int
) : Parcelable{
    var searchkey = ""
}