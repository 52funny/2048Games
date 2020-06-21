package top.funny52.game2048.dao

import me.liuwj.ktorm.database.Database
import java.sql.Connection
import java.sql.DriverManager
import kotlin.concurrent.thread

object DataBase {
    private val connection = DriverManager.getConnection("jdbc:mysql://192.168.1.3:3306/game2048?useSSL=false&user=root&password=admin")
    val database = Database.connect {
        object : Connection by connection {
            override fun close() {
            }
        }
    }

    init {
        Runtime.getRuntime().addShutdownHook(
            thread(start = false) {
                connection.close()
                println("The DataBase Connection Closed")
            }
        )
    }
}