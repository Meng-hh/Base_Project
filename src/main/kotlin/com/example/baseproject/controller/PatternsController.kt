package com.example.baseproject.controller


import com.example.baseproject.designpatterns.adapter.MediaAdapter
import com.example.baseproject.designpatterns.bridge.*
import com.example.baseproject.designpatterns.builder.BaoZi
import com.example.baseproject.designpatterns.builder.DouJiang
import com.example.baseproject.designpatterns.builder.Milk
import com.example.baseproject.designpatterns.builder.Waiter
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pattern")
class PatternsController {

    @RequestMapping("adapter")
    @ResponseBody
    fun runAdapter(){
        adapter()
    }

    @RequestMapping("bridge")
    @ResponseBody
    fun runBridge(){
        bridge()
    }

    @RequestMapping("builder")
    @ResponseBody
    fun runBuilder(){
        builder()
    }

    private fun adapter(){
        var mediaAdapter = MediaAdapter("MP4")
        mediaAdapter.play("mp4","movie.mp4")
        mediaAdapter = MediaAdapter("VLC")
        mediaAdapter.play("VLC","song.mp3")
    }

    private fun bridge() {
        Circle(Red()).draw()
        Circle(Yellow()).draw()
        Circle(Blue()).draw()
        Triangle(Red()).draw()
        Triangle(Yellow()).draw()
        Triangle(Blue()).draw()
    }

    private fun builder () {
        val foods = Waiter()
            .order1(BaoZi())
            .order2(Milk())
            .order3(DouJiang())
            .build()
        println(foods.toString())
    }
}