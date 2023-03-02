package com.github.cozy06.library

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
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

    fun filepath(): String {
        val path: String = Paths.get("").toAbsolutePath().toString()
        val src: File = File("$path/src")
        return when(src.isDirectory) {
            true -> "$path/src/main/kotlin/com/github/cozy06"
            false -> path
        }
    }
}