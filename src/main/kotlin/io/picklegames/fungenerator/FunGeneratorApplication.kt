package io.picklegames.fungenerator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FunGeneratorApplication

fun main(args: Array<String>) {
    runApplication<FunGeneratorApplication>(*args)
}
