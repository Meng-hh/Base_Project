package com.example.baseproject.annotations.handler

import com.example.baseproject.BaseEvent
import com.example.baseproject.Events
import com.example.baseproject.annotations.MyEventListener
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import java.awt.Event
import java.lang.reflect.Method

@Component
class MyEventListenerHandler : Events,ApplicationContextAware{

    private lateinit var context : ApplicationContext

    private  var listenerMethods : MutableList<Method> = mutableListOf()

    override fun publish(baseEvent: BaseEvent) {
         if (listenerMethods.isNullOrEmpty()) pillListenerMethods()
        listenerMethods.forEach { listenerMethod ->
            //1.先获取监听器方法的参数的event的类型，根据这个类型去匹配
            val validate = listenerMethod.parameterTypes.filter { BaseEvent::class.java.isAssignableFrom(it) }
            if (validate.isNotEmpty()) {
                val instance = listenerMethod.declaringClass.newInstance()
                listenerMethod.invoke(instance,baseEvent)
            }
        }
    }

    fun pillListenerMethods() {
        this.context.getBeansWithAnnotation(MyEventListener::class.java)
            .values.forEach { clazz->
                clazz::class.java.declaredMethods.forEach { method ->
                    if (AnnotationUtils.findAnnotation(method,MyEventListener::class.java) !=null ) {
                        listenerMethods.add(method)
                    }
                }
            }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.context = applicationContext
    }
}