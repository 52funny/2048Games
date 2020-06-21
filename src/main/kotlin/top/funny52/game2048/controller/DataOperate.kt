package top.funny52.game2048.controller

import me.liuwj.ktorm.dsl.*
import top.funny52.game2048.dao.DataBase
import top.funny52.game2048.model.DataHistory
import top.funny52.game2048.model.History

/*
 * Get 2048Game The Play Grade History Desc From Database
 */
fun DataBase.getHistory(n: Int): List<DataHistory> {
    return this.database.from(History).select().map {
        DataHistory(
            it[History.name] ?: "",
            it[History.grade] ?: 0
        )
    }.sortedByDescending { it.grade }.let {
        it.subList(0, if (n > it.size) it.size else n)
    }
}
/*
 * Get 2048Game The Best Grade From Database
 */
fun DataBase.getBestGrade(): Int {
    return this.database.from(History).select().map { it[History.grade] ?: 0 }.max() ?: 0
}

/*
 * Insert The Play Grade to Database
 */
fun DataBase.insertGrade(name: String, grade: Int) {
    this.database.insert(History) {
        it.grade to grade
        it.name to name
    }
}