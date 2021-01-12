package com.archetype.animus.presenter

import com.archetype.animus.entity.Book

class BookPresenter(
    private val book: Book
) {
    fun getIsbn() = book.isbn.value()

    fun getTitle() = book.title

    fun getPrice() = book.formattedPrice

    fun getAuthor() = book.author
}