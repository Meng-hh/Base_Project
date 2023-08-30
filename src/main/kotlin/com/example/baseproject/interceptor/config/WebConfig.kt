package com.example.baseproject.interceptor.config

import com.example.baseproject.interceptor.GeneralRequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    @Autowired
    private lateinit var generalRequestInterceptor : GeneralRequestInterceptor

    //将拦截器注册
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(generalRequestInterceptor)
    }
}