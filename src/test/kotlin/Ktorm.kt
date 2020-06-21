import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.int
import me.liuwj.ktorm.schema.varchar

fun main() {


    mutableListOf(2, 2, 0, 4).filter { it > 0 }.plus(listOf(0, 0, 0, 0)).also(::println)
    var a = mutableListOf(0, 2, 4, 4)
    val b = a.toTypedArray()
    a = mutableListOf(0, 2, 4, 4)

    println(a.toTypedArray().contentEquals(b))
//    val connection = DriverManager.getConnection("jdbc:mysql://192.168.1.3/ktorm?useSSL=false&user=root&password=admin")
//    Runtime.getRuntime().addShutdownHook(
//        thread(start = false) {
//            connection.close()
//            println("The Data Close")
//        }
//    )
//    val database = Database.connect {
//        object : Connection by connection {
//            override fun close() {
////                println("Close the database")
//            }
//        }
//    }
//
//    database.useTransaction {
//        database.insert(Student) {
//            it.name to "admin"
//            it.location to "Hong Kong"
//        }
//        assert(database.sequenceOf(Student).count() == 3) {
//            "Failed"
//        }
//    }
//
//    for (row in database.from(Student).select()) {
//        println(row[Student.name] + row[Student.id] + row[Student.location])
//    }
//    val query = database.from(Student).select().where { (Student.name eq "wwq") or  (Student.location eq "Hong Kong") }
//    query.map { it -> Stu(it[Student.name] ?: "", it[Student.location] ?: "") }
//        .sortedByDescending { it.name }.forEach(::println)
}

data class Stu(val name: String, val location: String)


object Student : Table<Nothing>("stu") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}