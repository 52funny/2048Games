package top.funny52.game2048.function

import top.funny52.game2048.view.Game

/*
 * Game Left Move
 */
fun Game.left(): Int {
    var tempGrade = 0
    for (index in this.block.indices) {
        this.block[index] = this.block[index].filter { it > 0 }.toMutableList().let {
            for (i in 0 until (it.size - 1)) {
                if (it[i] != 0 && it[i + 1] != 0 && it[i] == it[i + 1]) {
                    it[i] += it[i + 1]
                    tempGrade += it[i]
                    it[i + 1] = 0
                }
            }
            val temp = it.filter { its -> its > 0 }.toMutableList()
            while (temp.size < 4) {
                temp.add(0)
            }
            temp
        }
    }
    return tempGrade
}

/*
 * Game Right Move
 */
fun Game.right(): Int {
    var tempGrade = 0
    for (index in this.block.indices) {
        this.block[index] = this.block[index].filter { it > 0 }.toMutableList().let {
            for (i in it.size - 1 downTo 1) {
                if (it[i - 1] != 0 && it[i] != 0 && it[i - 1] == it[i]) {
                    it[i] += it[i - 1]
                    tempGrade += it[i]
                    it[i - 1] = 0
                }
            }
            val temp = it.filter { its -> its > 0 }.toMutableList()
            while (temp.size < 4) {
                temp.add(0, 0)
            }
            temp
        }
    }
    return tempGrade
}

/*
 * Game Down Move
 */
fun Game.down(): Int {
    this.block = rotate(this.block)
    val tempGrade = right()
    this.block = rotate(this.block)
    return tempGrade

}

/*
 * Game Up Move
 */
fun Game.up(): Int {
    this.block = rotate(this.block)
    val tempGrade = left()
    this.block = rotate(this.block)
    return tempGrade
}

/*
    Rotate The Array
 */
fun rotate(list: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
    val tempList: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0 until list.size) {
        val inList: MutableList<Int> = mutableListOf()
        for (j in 0 until list.size) {
            inList.add(list[j][i])
        }
        tempList.add(inList)
    }
    return tempList
}

fun Game.hasEmptyCell(): Boolean = this.block.any { it -> it.any { it == 0 } }

/*
    true : Fail
    false: Not Fail
 */
fun Game.hasFail(): Boolean {
    val tempList = this.block
    if (!hasEmptyCell()) {
        left()
        if (!hasEmptyCell()) {
            this.block = tempList
            right()
            if (!hasEmptyCell()) {
                this.block = tempList
                up()
                if (!hasEmptyCell()) {
                    this.block = tempList
                    down()
                    if (!hasEmptyCell()) {
                        this.block = tempList
                        return true
                    }
                    this.block = tempList
                }
                this.block = tempList
            }
            this.block = tempList
        }
        this.block = tempList
    }
    return false
}

/*
    true win
    false not win
 */
fun Game.hasWin() = this.block.any { it.any { its -> its == 2048 } }

fun Game.generateCell() {
    val location = randLocation()
    this.block[location.first][location.second] = randNumber()
}

/*
 * Reset The Game
 */
fun Game.resetGame() {
    this.block = mutableListOf(
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0),
        mutableListOf(0, 0, 0, 0)
    )
    generateCell()
    generateCell()
    this.grade = 0
    this.moveHistory = mutableListOf()
    this.repaint()
}

/*
 *  Fallback the moveHistory
 */
fun Game.fallBack() {
    if (this.moveHistory.size == 0) return
    this.block = this.moveHistory.let { it[it.size - 1].first }
    this.grade = this.moveHistory.let { it[it.size - 1].second }
    this.moveHistory.apply { this.removeAt(this.size - 1) }
    this.repaint()
}

/*
 *  Recording the moveHistory
 */
fun Game.goAhead() {
    if (this.moveHistory.size == 5) {
        this.moveHistory.apply { this.removeAt(0); this.add(block.toMutableList() to grade) }
    } else {
        this.moveHistory.add(this.block.toMutableList() to this.grade)
    }
}

/*
 * Print The Game Block Array
 */
fun Game.testLog() {
    for (i in block) {
        for (j in i) {
            print("$j ")
        }
        println()
    }
}
