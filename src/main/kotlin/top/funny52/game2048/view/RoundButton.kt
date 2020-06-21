package top.funny52.game2048.view

import java.awt.Color
import java.awt.Graphics
import javax.swing.JButton

class RoundButton(text: String, private val radius: Int): JButton(text) {
    override fun paintComponent(g: Graphics?) {
        if (getModel().isArmed) {
            g!!.color = Color.LIGHT_GRAY
        } else {
            g!!.color = this.background
        }
        g.fillRoundRect(0, 0, this.width - 1, this.height - 1, radius, radius)
        super.paintComponent(g)
    }
}