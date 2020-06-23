package top.funny52.game2048.function

import top.funny52.game2048.model.Colors
import top.funny52.game2048.view.Game
import java.awt.*
/*
 * Draw block and border
 */
fun Game.drawBlock(g: Graphics?, x: Int, y: Int) {
    val offsetX = offsetX(x)
    val offsetY = offsetY(y)
    g as Graphics2D
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    g.color = when (this.block[y][x]) {
        0 -> Colors.COMMON
        2 -> Colors.B_2
        4 -> Colors.B_4
        8 -> Colors.B_8
        16 -> Colors.B_16
        32 -> Colors.B_32
        64 -> Colors.B_64
        128 -> Colors.B_128
        256 -> Colors.B_256
        512 -> Colors.B_512
        1024 -> Colors.B_1024
        else -> Colors.B_2048
    }.rgb
    g.fillRoundRect(offsetX, offsetY, 107, 107, 15, 15)
    g.color = Color.black
    val font = Font("Monaco", Font.BOLD, 36)
    g.font = font
    val s = block[y][x].toString()
    val fm = getFontMetrics(font)
    val w = fm.stringWidth(s)
    val h = -fm.getLineMetrics(s, g).baselineOffsets[2].toInt()
    if (this.block[y][x] != 0) {
        g.drawString(s, offsetX + (107 - w)/ 2, offsetY + 107 - (107 - h)/ 2 -2)
    }
    if (battleMode) {
        when(Game.Request.send(300)) {
            "200" -> drawWin(g)
            "400" -> drawFail(g)
        }
    }
    if (hasFail() || hasWin()) {
        if (hasFail()) {
            drawFail(g)
            if (battleMode) Game.Request.send(400)
        }
        if (hasWin()) {
            drawWin(g)
            if (battleMode) Game.Request.send(200)
        }
    }

}
/*
 * Draw win information
 */
fun Game.drawWin(g: Graphics2D) {
    g.color = Color(255, 255, 255, 20)
    g.fillRect(0, 0, width, height)
    g.color = Color(78, 139, 202)
    g.font = Font("Monaco", Font.BOLD, 48)
    g.drawString("You Win!", this.width /3 , this.height / 2)
    drawBottom(g)
}
/*
 * Draw fail information
 */
fun Game.drawFail(g: Graphics2D) {
    g.color = Color(255, 255, 255, 20)
    g.fillRect(0, 0, width, height)
    g.color = Color(78, 139, 202)
    g.font = Font("Monaco", Font.BOLD, 48)
    g.drawString("Game over!", this.width / 4, this.height / 2)
    g.drawString("You lose!", this.width / 4 + 15 , this.height / 2 + 60)
    drawBottom(g)
}
/*
 * Draw bottom information
 */
fun Game.drawBottom(g: Graphics2D) {
    g.font = Font("Monaco", Font.PLAIN, 20)
    g.color = Color(128, 128, 128, 128)
    g.drawString("Press ESC to play again", 80, height - 40)
    g.font = Font("Monaco", Font.PLAIN, 20)
    g.drawString("Score: $grade", this.width / 3 + 30, this.height * 2 / 3)
}

fun offsetX(x: Int): Int {
    return 5 + 15 * (x + 1) + x * 107
}

fun offsetY(y: Int): Int {
    return 175 + 15 * (y + 1) + y * 107
}