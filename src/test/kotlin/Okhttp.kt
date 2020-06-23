import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import top.funny52.game2048.function.Request
import java.io.IOException

fun main() {
//    val client = OkHttpClient()
//    val form = FormBody.Builder().add("data", "300").build()
//    val request = Request.Builder().url("http://localhost:8080/index/a")
//        .post(form)
//        .build()
//    client.newCall(request).enqueue(object: Callback {
//        override fun onFailure(call: Call, e: IOException) {
//            println("error of bad request")
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            response.use {
//                println(it.body!!.string())
//            }
//        }
//    })
//    println("123")
    GlobalScope.launch {
        println(Request("a").send(300))
    }
    Thread.sleep(1000L)

}