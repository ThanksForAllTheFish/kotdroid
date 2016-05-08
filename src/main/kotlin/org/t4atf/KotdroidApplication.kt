package org.t4atf

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotdroidApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotdroidApplication::class.java, *args)
}
