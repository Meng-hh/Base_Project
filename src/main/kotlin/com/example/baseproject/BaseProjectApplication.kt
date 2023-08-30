package com.example.baseproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy
class BaseProjectApplication

fun main(args: Array<String>) {
    runApplication<BaseProjectApplication>(*args)
}
