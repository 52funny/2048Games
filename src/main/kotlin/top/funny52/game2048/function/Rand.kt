package top.funny52.game2048.function

import top.funny52.game2048.view.Game
import kotlin.random.Random

/*
 *   Randomly generate 2 and 4
 */
fun Game.randNumber(): Int = if (Math.random() < 0.9) 2 else 4

/*
 *   Randomly generate coordinate
 */
fun Game.randLocation(): Pair<Int, Int> {
    val tempPair = arrayListOf<Pair<Int, Int>>()
    for (i in 0 until 4) {
        for (j in 0 until 4) {
            if (this.block[i][j] == 0) {
                tempPair.add(i to j)
            }
        }
    }
    return tempPair[Random(System.currentTimeMillis()).nextInt(tempPair.size)].also {
        println("Randomly Coordinate: $it")
    }
}
