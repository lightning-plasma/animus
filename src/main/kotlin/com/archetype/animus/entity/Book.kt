package com.archetype.animus.entity

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val isbn: Isbn,
    val title: String,
    val author: String,
    val price: Int,
) {
    val formattedPrice = "￥" + String.format("%,d", price)
}