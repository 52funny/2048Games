package top.funny52.game2048.view

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import top.funny52.game2048.controller.getBestGrade
import top.funny52.game2048.controller.insertGrade
import top.funny52.game2048.dao.DataBase
import top.funny52.game2048.function.*
import top.funny52.game2048.model.Colors
import java.awt.Color
import java.awt.Graphics
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JLabel
import javax.swing.JPanel

class Game : JPanel() {

    var block: MutableList<MutableList<Int>> = mutableListOf(
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0)
    )
    // game move history
    var moveHistory =  mutableListOf<MutableList<MutableList<Int>>>()

    companion object {
        // This is Challenger Name
        var Name: String? = null
    }

    // now grade
    internal var grade: Int = 0
        set(value) {
            field = value
            this.scoreReact.center = value
            if (field > bestGrade) {
                bestGrade = field
            }
        }

    // best grade
    private var bestGrade: Int = DataBase.getBestGrade()
        set(value) {
            field = value
            this.bestReact.center = field
        }


    private val scoreReact = RoundReact("SCORE", 0, 8)
    private val bestReact = RoundReact("BEST", 0, 8)


    init {
        this.background = Colors.FOREGROUND.rgb
        this.layout = null
        this.setSize(513, 708)
        this.isFocusable = true



        this.add(JLabel("", JLabel.CENTER).apply {
            this.text = "<html><div style='color:#736B63;font-size:48px;'><b>2048</b></div></html>"
            this.setBounds(10, 10, 160, 60)
        })
        this.add(JLabel("", JLabel.LEFT).apply {
            this.text = "<html><div style='color:#736B63;font-size:13px;'><b>Play 2048 Game</b></div></html>"
            this.setBounds(10, 100, 160, 30)
        })
        this.add(JLabel("", JLabel.LEFT).apply {
            this.text =
                "<html><div style='color:#736B63;font-size:13px;'>Hope You Will Enjoy <b>2048 Game</b></div></html>"
            this.setBounds(10, 130, 300, 30)
        })
        this.add(RoundButton("New Game", 8).apply {
//            this.isOpaque = true
            this.isFocusable = false
            this.background = Color(0x8A7764)
            this.foreground = Color(0xE5DCD3)
            this.isBorderPainted = false
            this.setBounds(368, 100, 140, 60)
            this.addActionListener {
                resetGame()
            }
        })
        this.add(RoundButton("Back", 8).apply {
            this.isFocusable = false
            this.background = Color(0x8A7764)
            this.foreground = Color(0xE5DCD3)
            this.isBorderPainted = false
            this.setBounds(285, 100, 75, 60)
            this.addActionListener {
                fallBack()
            }
        })

        this.add(this.scoreReact.apply {
            this.setBounds(308, 10, 80, 75)
            this.center = grade
        })
        this.add(this.bestReact.apply {
            this.setBounds(398, 10, 110, 75)
            this.center = bestGrade
        })


        this.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent?) {
                if (e!!.keyCode in setOf(
                        KeyEvent.VK_LEFT,
                        KeyEvent.VK_RIGHT,
                        KeyEvent.VK_UP,
                        KeyEvent.VK_DOWN,
                        KeyEvent.VK_ESCAPE
                    )
                ) {
                    if (hasEmptyCell()) {
                        goAhead()
                        println(moveHistory)
                    }
                    if (!(hasFail() || hasWin())) {
                        var tempGrade = 0
                        when (e.keyCode) {
                            KeyEvent.VK_LEFT -> tempGrade += left()
                            KeyEvent.VK_RIGHT -> tempGrade += right()
                            KeyEvent.VK_UP -> tempGrade += up()
                            KeyEvent.VK_DOWN -> tempGrade += down()
                            KeyEvent.VK_ESCAPE -> resetGame()
                        }
                        if (hasEmptyCell()) grade += tempGrade
                        // refresh UI
                        repaint()
                        if (!hasFail()) {
                            GlobalScope.launch {
                                delay(300L)
                                if (hasEmptyCell()) {
                                    generateCell()
                                    repaint()
                                }
                            }
                        }
                        testLog()
                    } else {
                        if (e.keyCode == KeyEvent.VK_ESCAPE) {
                            DataBase.insertGrade(
                                Name ?: "挑战者💗", grade
                            )
                            resetGame()
                        }
                    }
                }
            }

        })
    }

    /*
     * Paint The 2048 Border And 2048 Number
     */
    override fun paint(g: Graphics?) {
        super.paint(g)
        g!!.let {
            it.color = Colors.BACKGROUND.rgb
            it.fillRoundRect(5, 175, 503, 503, 10, 10)
        }
        for (i in 0 until 4) {
            for (j in 0 until 4) {
                drawBlock(g, j, i)
            }
        }
    }
}

