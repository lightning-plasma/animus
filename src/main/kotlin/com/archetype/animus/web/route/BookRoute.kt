package com.archetype.animus.web.route

import com.archetype.animus.entity.Book
import com.archetype.animus.entity.Isbn
import com.archetype.animus.error.NotFoundException
import com.archetype.animus.presenter.BookPresenter
import com.archetype.animus.presenter.BooksPresenter
import com.archetype.animus.repository.BookRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.buildAndAwait
import org.springframework.web.reactive.function.server.coRouter
import org.springframework.web.reactive.function.server.renderAndAwait

// router sample
// @Configuration(proxyBeanMethods = false)
class BookRoute {

    @Bean
    fun router(
        repository: BookRepository
    ) = coRouter {
        val handler = BookHandler(repository)
        GET("/animus/books", handler::list)
        GET("/animus/books/{isbn}", handler::show)

        onError<NotFoundException> { _, _ ->
            notFound().buildAndAwait()
        }
    }
}

class BookHandler(
    private val repository: BookRepository
) {
    suspend fun list(req: ServerRequest): ServerResponse {
        val bookFlow =
            repository.findAll().map { Book(Isbn(it.isbn), it.title, it.author, it.price) }
        return ok().contentType(MediaType.TEXT_HTML).renderAndAwait(
            "index",
            mapOf("booksPresenter" to BooksPresenter(bookFlow.toList(mutableListOf())))
        )
    }

    suspend fun show(req: ServerRequest): ServerResponse {
        val isbn = req.pathVariable("isbn")
        val book = repository.findById(isbn)?.let {
            Book(Isbn(it.isbn), it.title, it.author, it.price)
        } ?: throw NotFoundException("Book not found. isbn=$isbn")

        return ok().contentType(MediaType.TEXT_HTML).renderAndAwait(
            "show",
            mapOf("bookPresenter" to BookPresenter(book))
        )
    }
}