package com.archetype.animus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import reactor.blockhound.BlockHound

@SpringBootApplication
@EnableR2dbcRepositories
class AnimusApplication

fun main(args: Array<String>) {
    // https://github.com/reactor/BlockHound/issues/135
    // https://github.com/Kotlin/kotlinx.coroutines/issues/2190
    BlockHound.install()
    runApplication<AnimusApplication>(*args)
}
