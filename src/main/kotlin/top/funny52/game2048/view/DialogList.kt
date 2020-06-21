package top.funny52.game2048.view

import java.awt.BorderLayout
import java.awt.Frame
import javax.swing.*

class DialogList(frame: Frame, title: String, list: Array<String>): JDialog(frame, title, true) {
    init {
        this.setSize(230, 300)
        this.layout = null
        this.setLocationRelativeTo(frame)
        this.isResizable = false
        this.add(JList(list).apply {
            this.setBounds(0, 0, this@DialogList.width, this@DialogList.height)
            this.setCellRenderer(DefaultListCellRenderer().apply { horizontalAlignment = SwingConstants.CENTER })
        }, BorderLayout.CENTER)
        this.isVisible = true
    }
}