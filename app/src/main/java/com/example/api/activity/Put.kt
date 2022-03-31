package com.example.api.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.api.MVVM.DataRepository
import com.example.api.MVVM.DataViewModel
import com.example.api.MVVM.DataViewModelFactory
import com.example.api.R
import kotlinx.android.synthetic.main.activity_put.*

class Put : AppCompatActivity() {

    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_put)

        val repository = DataRepository()
        val viewModelFactory = DataViewModelFactory(repository)
        dataViewModel = ViewModelProviders.of(this, viewModelFactory)[DataViewModel::class.java]

        btnUpdate.setOnClickListener{
            dataViewModel.put(etName.text.toString(),etJob.text.toString())
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}