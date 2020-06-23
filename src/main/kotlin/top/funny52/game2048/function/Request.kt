package top.funny52.game2048.function


import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class Request(id: String) {
    private val request = Request.Builder().url("http://localhost:8080/index/$id")
    private val reset = Request.Builder().url("http://localhost:8080/reset")
        .post(FormBody.Builder().build())

    companion object {
        private val client = OkHttpClient()
    }
    /*
     * Send data to backend
     * 200 win
     * 300 normal
     * 400 fail
     */
    fun send(data: Int): String {
        val s = StringBuffer()
        client.newCall(request.let {
            it.post(FormBody.Builder().add("data", data.toString()).build())
            it.build()
        }).execute().use {
            if (!it.isSuccessful) {
                println("error of bad request server")
                return@use
            }

            val ss: Data = Gson().fromJson(it.body!!.string())
            s.append(ss.code.toString())
        }
        return s.toString()
    }
    /*
     * Send reset order to backend
     */
    fun reset() {
        client.newCall(reset.build()).execute().use {
            if(!it.isSuccessful) {
                println("error of bad request reset")
                return@use
            }
            println(it.body!!.string())
        }
    }
}


data class Data(val code: Int?)