package com.example.baseproject.entity

import com.example.baseproject.annotations.TestAnno
import com.example.baseproject.entity.baseinterface.Person

@TestAnno
class Student : Person {

     var no: Int? = null

    var grade : Int? = null

    override var weight: String?= null
    override var age: Int?
        get() = (grade?:0)+7
        set(value) {}

    override var height: String?=null
    override var isHealth: Boolean?
        get() = ((grade?:0)>=1)
        set(value) {}

    override fun toString(): String {
        return "my no is ${no}, age is ${age}, height is $height"
    }
}