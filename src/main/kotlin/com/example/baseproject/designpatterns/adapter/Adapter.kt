package com.example.baseproject.designpatterns.adapter

//适配器模式


interface MainPlayer {
    fun play(audioType: String, fileName: String)
}

class MediaAdapter(audioType: String) : MainPlayer {
    private var playerAdapter: PlayerAdapter? = null

    init {
        when {
            audioType.equals("Vlc", true) -> {
                playerAdapter = VlcPlayer()
            }
            audioType.equals("Mp4", true) -> {
                playerAdapter = Mp4Player()
            }
        }
    }

    override fun play(audioType: String, fileName: String) {
        playerAdapter?.let {
            if (audioType.equals("Vlc", true))
                it.playAll(fileName)
            else
                it.playAll(fileName)
        }
    }
}


interface PlayerAdapter {
    fun playAll(fileName: String)
}


class VlcPlayer : PlayerAdapter {
    override fun playAll(fileName: String) {
        println("Vlc play $fileName ing ......")
    }
}

class Mp4Player : PlayerAdapter {
    override fun playAll(fileName: String) {
        println("Mp4 play $fileName ing ......")
    }
}