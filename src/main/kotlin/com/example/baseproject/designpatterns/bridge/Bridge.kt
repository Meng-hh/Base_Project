package com.example.baseproject.designpatterns.bridge

//桥接模式

interface Color{ val backgroundColor : String}

class Blue : Color {
    override val backgroundColor: String = "Blue"
}

class Red : Color {
    override val backgroundColor: String = "Red"
}

class Yellow : Color {
    override val backgroundColor: String = "Yellow"
}

interface Shape {
    val shapeName : String
    fun draw()
}

class Circle(private val color: Color) : Shape {
    override val shapeName: String = "Circle"
    override fun draw() {
        println("use the pen of ${color.backgroundColor} draw  shape of $shapeName !")
    }
}

class Triangle(private val color: Color) : Shape {
    override val shapeName: String = "Triangle"
    override fun draw() {
        println("use the pen of ${color.backgroundColor} draw  shape of $shapeName !")
    }
}
