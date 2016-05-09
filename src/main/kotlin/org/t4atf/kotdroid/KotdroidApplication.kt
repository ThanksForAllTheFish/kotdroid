package org.t4atf.kotdroid

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KotdroidApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotdroidApplication::class.java, *args)
}
