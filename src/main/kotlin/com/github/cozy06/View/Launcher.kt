package com.github.cozy06.View

import com.github.cozy06.library.AdditionalFeatures
import java.awt.Color
import java.awt.Graphics
import java.awt.Image
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame


class Launcher : JFrame(), KeyListener {
    private val imageTool: Toolkit = Toolkit.getDefaultToolkit()
    private val background: Image? = imageTool.getImage("${AdditionalFeatures().filepath()}/resource/img/background.png")
    private var offscreenImage: Image? = null
    private var offscreenGraphics: Graphics? = null

    init {
        // 프레임의 대한 설정.
        title = "JFrame 테스트" // 프레임 제목 설정.
        setSize(960, 540) // 프레임의 크기 설정.
        setLocationRelativeTo(null)
        isResizable = false // 프레임의 크기 변경 못하게 설정.
        isVisible = true // 프레임 보이기;
        defaultCloseOperation = DISPOSE_ON_CLOSE // 프레임의 x버튼 누르면 종료;
        addKeyListener(this)
    }

    private val selectY: List<Int> = listOf(240, 340, 440)
    private var selector: Int = 0

    override fun paint(g: Graphics) {
        if (offscreenImage == null) {
            offscreenImage = createImage(width, height)
            offscreenGraphics = offscreenImage?.graphics
        }

        offscreenGraphics?.drawImage(background, 0, 0, this)

        offscreenGraphics?.color = Color(0,217,255)
        offscreenGraphics?.font = offscreenGraphics?.font?.deriveFont(70.0f)
        offscreenGraphics?.drawString("TITLE", 385, 175)

        offscreenGraphics?.color = Color(255,255,255)
        offscreenGraphics?.font = offscreenGraphics?.font?.deriveFont(32.0f)
        offscreenGraphics?.drawString("start", 443, 275)
        offscreenGraphics?.drawString("option", 430, 375)
        offscreenGraphics?.drawString("exit", 451, 475)
        offscreenGraphics?.drawRect(420, selectY[selector], 120, 50)
        g.drawImage(offscreenImage, 0, 0, null)
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_UP -> if(selector != 0) {selector -= 1}
            KeyEvent.VK_DOWN -> if(selector != 2) {selector += 1}
            KeyEvent.VK_ENTER -> when(selector) {
                0 -> {
                    dispose()
                    GameStart()
                }
                1 -> AdditionalFeatures().playSound("${AdditionalFeatures().filepath()}/resource/sound/Sound.wav")
                2 -> dispose()
            }
        }
        repaint()
    }


    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}

}