package com.example.baseproject.annotations

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION,AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class TestAnno()
