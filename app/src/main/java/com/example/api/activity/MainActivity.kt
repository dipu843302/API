package com.example.api.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.api.MVVM.DataRepository
import com.example.api.MVVM.DataViewModel
import com.example.api.MVVM.DataViewModelFactory
import com.example.api.R
import com.example.api.adapter.DataAdapter
import com.example.api.responseclass.ResponseClass
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var dataViewModel: DataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = DataRepository()
        val viewModelFactory = DataViewModelFactory(repository)
        dataViewModel = ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]

        btnListUser.setOnClickListener {
            val intent = Intent(this, ListUsersActivity::class.java)
            startActivity(intent)
        }


        btnPost.setOnClickListener {
          val intent=Intent(this,Add::class.java)
            startActivity(intent)
        }

    }


}