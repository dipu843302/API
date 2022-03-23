package com.example.api.MVVM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataViewModel(private val dataRepository: DataRepository):ViewModel() {

    val user:LiveData<StringBuffer> get() = dataRepository.userBuffer
    fun openTheConnection(){
        viewModelScope.launch(Dispatchers.IO ) {
            dataRepository.openTheConnection()
        }
    }

}