package com.example.api.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.api.R
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        val result=intent.getStringExtra("json_results")
        textView.text=result
    }
}