package com.github.cozy06.resource

import java.io.File
import javax.sound.sampled.AudioSystem
import kotlin.concurrent.thread

class AdditionalFeatures {
    fun playSound(soundFile: String) {
        try {
            val audioStream = AudioSystem.getAudioInputStream(File(soundFile).absoluteFile)
            val clip = AudioSystem.getClip()
            clip.open(audioStream)
            clip.start()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun delay(millis: Long, function: () -> Unit, repeat: Int = 1) {
        thread(start = true) {
            for(i: Int in 1..repeat) {
                Thread.sleep(millis)
                function()
            }
        }
    }
}