package com.example.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api.MVVM.DataRepository
import com.example.api.MVVM.DataViewModel
import com.example.api.MVVM.DataViewModelFactory
import com.example.api.adapter.DataAdapter
import com.example.api.responseclass.ResponseClass
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var dataViewModel: DataViewModel
    private lateinit var dataRepository: DataRepository
    lateinit var dataAdapter: DataAdapter
    private var dataList = mutableListOf<ResponseClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = DataRepository()
        val viewModelFactory = DataViewModelFactory(repository)
        dataViewModel = ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]

        dataViewModel.openTheConnection()
        dataViewModel.user.observe(this){
         dataList.clear()
            dataList.addAll(listOf(it))
            setRecyclerView()
        }
    }

    private fun setRecyclerView(){
        dataAdapter=DataAdapter(dataList)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=dataAdapter
    }
}