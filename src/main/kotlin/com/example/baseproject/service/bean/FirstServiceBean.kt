package com.example.baseproject.service.bean

import com.example.baseproject.service.FirstService
import org.springframework.stereotype.Service

@Service
class FirstServiceBean : FirstService {

    override fun getMessage(order: Int): String {
        return try {
            if (order < 10) {
                throw RuntimeException("序号不能小于10!!")
            } else {
                "this is a message"
            }
        } catch (e: Exception) {
            print(e.message)
            "this method have a exception!!"
        }
    }

}