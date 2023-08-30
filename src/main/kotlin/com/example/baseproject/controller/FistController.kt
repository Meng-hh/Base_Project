package com.example.baseproject.controller

import com.example.baseproject.CommonEvent
import com.example.baseproject.Events
import com.example.baseproject.annotations.MyEventListener
import com.example.baseproject.annotations.TestAnno
import com.example.baseproject.config.aop.ReplaceMethodByAop
import com.example.baseproject.entity.baseinterface.Person
import com.example.baseproject.interceptor.GeneralRequestInterceptor
import com.example.baseproject.service.FirstService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@MyEventListener
@Controller
@RequestMapping("/first")
class FistController {

    private var log: Logger = LoggerFactory.getLogger(GeneralRequestInterceptor::class.java)

    @Autowired
    private lateinit var events: Events

    private final var entities: List<Person> = emptyList()
        @Autowired
        private set(values) {
            field = values.asSequence()
                .filter { it.javaClass.isAnnotationPresent(TestAnno::class.java) }
                .toList()
        }

    private lateinit var firstService : FirstService

    @Autowired
    private fun setFirstService(firstService: FirstService) {
        this.firstService = firstService
    }

    @ResponseBody
    @RequestMapping("/print")
    fun printEntities(): String {
        val joinToString = entities.joinToString("" , prefix = "", postfix = ""){it.toString() + "\n"}
        //return "list size is ${entities.size},first element is ${if (entities.isNotEmpty()) entities.first().toString() else "null"}"
        println(joinToString)
        return joinToString
    }

    @ResponseBody
    @RequestMapping("/hello")
    fun sayHello(): String {
        return "Hello"
    }

    @ResponseBody
    @RequestMapping("/linkedMultiMap")
    fun testLinkedMultiMap(): String {
       val map = LinkedMultiValueMap<String,String>()
        map.add("学生","小李")
        map.add("学生","小王")
        map.add("学生","小黄")
        map.add("学生","小绿")
        map.add("教师","王校长")
        map.add("教师","张老师")
        map["公职人员"] = "刘处长"
        map["公职人员"] = "孙所长"
        val strings = map["学生"]
        return map.values.joinToString(",","","")
    }

    @ResponseBody
    @RequestMapping("/message")
    @TestAnno
    fun getMessage(): String {
        var message : String = try {
            firstService.getMessage(1)
        }catch (e : Exception) {
            e.message?: "this is controller have a error"
        }
        return  message
    }

    @ResponseBody
    @RequestMapping("/publish")
    fun publish() {
        log.info("正在执行Controller中的内容。。。。")
        log.info("发布事件。。。。")
        events.publish(CommonEvent(this))
    }

    @MyEventListener
    fun responseListeners(event: CommonEvent) {
        log.info("正在执行监听器中的内容。。。。")
    }

}