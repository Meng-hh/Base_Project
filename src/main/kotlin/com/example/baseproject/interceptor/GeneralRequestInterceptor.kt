package com.example.baseproject.interceptor

import com.example.baseproject.utils.RedisUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class GeneralRequestInterceptor: HandlerInterceptor {
    private var log: Logger = LoggerFactory.getLogger(GeneralRequestInterceptor::class.java)

    @Autowired
    private lateinit var redisUtil: RedisUtil

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler is HandlerMethod) {
            log.info("执行请求前拦截。拦截到的方法为：${request.requestURL}")
           val key = "${request.remoteAddr} - ${request.method} - ${request.requestURL}"
            log.info("key is : $key")
            if (redisUtil[key] == null) {
                redisUtil[key, 1] = 2
                log.info("首次调用 ${request.requestURL} 接口")
            }else {
                log.error("当前接口正在执行中，请勿重复提交")
                response.outputStream.let {os ->
                    val message ="100,请勿重复提交请求！".toByteArray()
                    os.write(message)
                    os.flush()
                    os.close()
                }
                return false
            }
        }
        return true
    }
}