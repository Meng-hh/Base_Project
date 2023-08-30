package com.example.baseproject.utils.configs

import com.example.baseproject.entity.Student
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Random

@Configuration
class BeanFactory {

    @Bean
    fun buildStudents():List<Student> {
        val students = mutableListOf<Student>()
        for (i in 0..10) {
            val student =Student().apply {
                this.no = i
                this.grade = Random().nextInt(10)
                this.height = (180 - i).toString()
                this.weight = "50"
            }
            students.add(student)
        }
        return students
    }
}