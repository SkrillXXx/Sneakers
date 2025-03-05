package com.example.sneakers.CleanArchitecture.data

data class Sneakers(
    val id: String,
    var seller: String,
    var name: String,
    var price: String,
    var isFavorite: Boolean = false
)