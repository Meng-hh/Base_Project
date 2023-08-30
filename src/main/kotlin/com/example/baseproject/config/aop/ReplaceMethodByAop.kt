package com.example.baseproject.config.aop


import com.example.baseproject.annotations.TestAnno
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
@TestAnno
class ReplaceMethodByAop {

    @Around("execution(* com.example.baseproject.controller.FistController.sayHello(..))")
    fun replaceMethod():Any {
        return "Hello World"
    }
}