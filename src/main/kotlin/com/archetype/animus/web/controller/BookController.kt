package com.archetype.animus.web.controller

import com.archetype.animus.entity.Book
import com.archetype.animus.entity.Isbn
import com.archetype.animus.error.NotFoundException
import com.archetype.animus.presenter.BookPresenter
import com.archetype.animus.presenter.BooksPresenter
import com.archetype.animus.repository.BookRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.result.view.Rendering

// controller sample
@Controller
@RequestMapping("/animus")
class BookController(
    private val repository: BookRepository,
) {
    @GetMapping("books")
    suspend fun list(): Rendering {
        val bookFlow =
            repository.findAll().map { Book(Isbn(it.isbn), it.title, it.author, it.price) }

        return Rendering
            .view("index")
            .modelAttribute("booksPresenter", BooksPresenter(bookFlow.toList(mutableListOf())))
            .build()
    }

    @GetMapping("books/{isbn}")
    suspend fun show(@PathVariable isbn: String): Rendering {
        val book = repository.findById(isbn)?.let { Book(Isbn(it.isbn), it.title, it.author, it.price) }
            ?: throw NotFoundException("Book not found. isbn=$isbn")


        return Rendering
            .view("show")
            .modelAttribute("bookPresenter", BookPresenter(book))
            .build()
    }
}