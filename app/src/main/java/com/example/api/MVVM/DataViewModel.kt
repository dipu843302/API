package com.example.api.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.responseclass.ResponseClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val dataRepository: DataRepository):ViewModel() {

    val user:LiveData<ResponseClass> get() = dataRepository.userBuffer
    fun openTheConnection(){
        viewModelScope.launch(Dispatchers.IO ) {
            dataRepository.openTheConnection()
        }
    }

}