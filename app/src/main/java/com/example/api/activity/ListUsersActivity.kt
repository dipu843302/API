package com.example.api.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.MVVM.DataRepository
import com.example.api.MVVM.DataViewModel
import com.example.api.MVVM.DataViewModelFactory
import com.example.api.R
import com.example.api.adapter.DataAdapter
import com.example.api.responseclass.ResponseClass
import kotlinx.android.synthetic.main.activity_list_users.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class ListUsersActivity : AppCompatActivity() {

    private lateinit var dataViewModel: DataViewModel
    lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<ResponseClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_users)

        val repository = DataRepository()
        val viewModelFactory = DataViewModelFactory(repository)
        dataViewModel = ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]

        dataViewModel.openTheConnection()
        dataViewModel.user.observe(this) {
            Log.d("check",it.toString())
            buildResponseData(it)
        }
    }
    private fun buildResponseData(stringBuffer: StringBuffer){
        // build a JSON object from the received string

        val json=stringBuffer.toString()
        try {
            val jsonObject= JSONObject(json)
            val jsonArray=jsonObject.getJSONArray("data")
            for(i in 0 until  jsonArray.length()){
                val eachJsonObject=jsonArray.getJSONObject(i)
                val id=eachJsonObject.getInt("id")
                val firstName=eachJsonObject.getString("first_name")
                val lastName=eachJsonObject.getString("last_name")
                val email=eachJsonObject.getString("email")
                val image=eachJsonObject.getString("avatar")
                val responseClass=ResponseClass(id,email,firstName,lastName,image)
                dataList.add(responseClass)
            }
            Log.d("size1", dataList.size.toString())
            setRecyclerView()
        }catch (e: JSONException){
            e.printStackTrace()
        }
    }

    private fun setRecyclerView() {
        dataAdapter = DataAdapter(dataList)
        recyclerView.adapter = dataAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}