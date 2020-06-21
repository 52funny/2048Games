package top.funny52.game2048.model

import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.varchar


object History : Table<Nothing>("history") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val grade = int("grade")
}

data class DataHistory(val name: String, val grade: Int)