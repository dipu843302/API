package com.example.api.MVVM

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.api.activity.PostDetailsActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class DataRepository {
    private val responseClassBuffer = MutableLiveData<StringBuffer>()
    val userBuffer: LiveData<StringBuffer> get() = responseClassBuffer

    fun openTheConnection() {
        val stringBuffer = StringBuffer()
        CoroutineScope(Dispatchers.IO).launch {
            var url: URL? = null
            try {
                url = URL("https://reqres.in/api/users?page=2")
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"

                //stream of data from the server
                val inputStream = urlConnection.inputStream

                // read the stream fo data received from the server
                val inputStreamReader = InputStreamReader(inputStream)

                //inputStreamReader.read will give one element at a time so data will have the first element
                var data = inputStreamReader.read()

                // StringBuffer class is used to build the json response int the string format
                // when data is -1 we reached the end of the response
                while (data != -1) {
                    // the data will be in byte format so we are cast it to char
                    val ch = data.toChar()
                    stringBuffer.append(ch)
                    data = inputStreamReader.read()
                }
                responseClassBuffer.postValue(stringBuffer)
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }

    fun postData(Name: String, Job:String) {
        val jsonObject = JSONObject()
        val params = JSONObject()
        jsonObject.put("name", Name)
        jsonObject.put("job", Job)
        val paramString = params.toString()
        CoroutineScope(Dispatchers.IO).launch {
            var url: URL? = null
            try {
                url = URL("https://reqres.in/api/users")
                //Open the connection and connect to server
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "POST"
                urlConnection.setRequestProperty("Content-Type", "application/json")
                // The format of the content we're sending to the server
                urlConnection.setRequestProperty("Accept", "application/json")
                // The format of response we want to get from the server
                urlConnection.doInput = true
                urlConnection.doOutput = true
                // Send the JSON we created
                val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
                outputStreamWriter.write(paramString)
                outputStreamWriter.flush()
                Log.d("check",urlConnection.responseCode.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}