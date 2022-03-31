package com.example.api.MVVM

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun postData(Name: String, Job: String) {

        val params = JSONObject()
        params.put("name", Name)
        params.put("job", Job)
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
                Log.d("post", urlConnection.responseCode.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loginSuccess(email: String, password: String) {

        val params = JSONObject()
        params.put("email", email)
        params.put("password", password)
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
                Log.d("post", urlConnection.responseCode.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun put(name: String, job: String) {

        val param = JSONObject()
        param.put("name", name)
        param.put("job", job)

        val paramString = param.toString()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL("https://reqres.in/api/users/2")
                val urlConnection = url.openConnection() as HttpURLConnection
                urlConnection.doOutput = true
                urlConnection.requestMethod = "PUT"
                urlConnection.setRequestProperty("Content-Type", "application/json")
                // The format of the content we are sending to the server
                urlConnection.setRequestProperty("Accept", "application/json")
                // The format of response we want to get from the server

                urlConnection.doInput = true
                urlConnection.doOutput = true

                //send the JSON we created
                val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
                outputStreamWriter.write(paramString)
                outputStreamWriter.flush()
                Log.d("put", urlConnection.responseCode.toString())
            } catch (e: java.lang.Exception) {
                e.stackTrace
            }
        }
    }

    fun Delete(id: Int) {
    val url=URL("https://reqres.in/api/users/$id")
        val httpURLConnection=url.openConnection() as HttpURLConnection
        httpURLConnection.doOutput=true
        httpURLConnection.requestMethod="DELETE"
        httpURLConnection.connect()
      Log.d("delete",httpURLConnection.responseCode.toString())
    }

}