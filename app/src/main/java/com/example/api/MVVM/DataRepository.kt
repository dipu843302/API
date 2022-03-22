package com.example.api.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.api.responseclass.ResponseClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DataRepository   {
    private val responseClassBuffer= MutableLiveData<ResponseClass>()
    val  userBuffer: LiveData<ResponseClass> get()=responseClassBuffer
 //   private val responseHandler: ResponseHandler = ResponseHandler()

   fun openTheConnection(){
       val stringBuffer= StringBuffer()
       CoroutineScope(Dispatchers.IO).launch {
           var url:URL?=null
           try {
               url=URL("https://reqres.in/api/users?page=2")
               val urlConnection=url.openConnection() as HttpURLConnection
               urlConnection.requestMethod="GET"

               //stream of data from the server
               val inputStream=urlConnection.inputStream

               // read the stream fo data received from the server
               val inputStreamReader=InputStreamReader(inputStream)

               //inputStreamReader.read will give one element at a time so data will have the first element
               var data=inputStreamReader.read()

               // StringBuffer class is used to build the json response int the string format
               // when data is -1 we reached the end of the response
               while (data!=-1){
                   // the data will be in byte format so we are cast it to char
                   val ch =data.toChar()
                   stringBuffer.append(ch)
                   data=inputStreamReader.read()
               }

           }catch (e:Exception){

               e.printStackTrace()
           }
       }
   }
}