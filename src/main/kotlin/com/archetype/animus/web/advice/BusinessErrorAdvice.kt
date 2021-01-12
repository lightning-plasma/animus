package com.archetype.animus.web.advice

import com.archetype.animus.error.NotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.reactive.result.view.Rendering

@ControllerAdvice("com.archetype.animus.web.controller")
class BusinessErrorAdvice {
    @ExceptionHandler(value = [NotFoundException::class])
    suspend fun handleNotFound(ex: Throwable): Rendering {
        return Rendering
            .redirectTo("/animus/books")
            .build()
    }
}