package com.archetype.animus.entity

import kotlinx.serialization.Serializable

@Serializable
data class Isbn(
    private val value: String
) {
    init {
        require(value.length == 13) {
            "isbnは13桁で管理する"
        }
    }

    fun value() = value

    override fun toString() = value
}