package top.funny52.game2048.view

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import javax.swing.JLabel
import javax.swing.JPanel

class RoundReact(private val up: String, centers: Int, private val radius: Int) : JPanel() {
    private val upLabel = JLabel(up, JLabel.CENTER)
    private val centerLabel = JLabel(centers.toString(), JLabel.CENTER)
    var center: Int = centers
        set(value) {
            field = value
            this.centerLabel.text = "<html><div style='font-size:18px'>${this.center}</div></html>"
        }

    init {
        this.layout = BorderLayout().apply {
            this.vgap = 0
        }
        this.background = Color(0xB5A79B)
        this.upLabel.foreground = Color(0xE5DCD3)
        this.centerLabel.foreground = Color(0xF6F6F6)
        this.upLabel.text = "<html><div style='font-size:8px; margin-top:8px'>${this.up}</div></html>"
        this.centerLabel.text = "<html><div style='font-size:18px;'>${this.center}</div></html>"
        this.add(upLabel, BorderLayout.NORTH)
        this.add(centerLabel, BorderLayout.CENTER)
        this.add(JPanel().apply { this.isOpaque = false }, BorderLayout.SOUTH)
        this.isVisible = true
    }

    override fun paintComponent(g: Graphics?) {
        g!!.let {
            it.color = this.background
            it.fillRoundRect(0, 0, this.width, this.height, radius, radius)
        }
    }
}