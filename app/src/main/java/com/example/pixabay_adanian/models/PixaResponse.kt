package com.example.pixabay_adanian.models

data class PixaResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)