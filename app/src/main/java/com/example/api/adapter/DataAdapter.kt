package com.example.api.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.R
import com.example.api.responseclass.ResponseClass
import kotlinx.android.synthetic.main.item_layout.view.*

class DataAdapter(var list:MutableList<ResponseClass>,val clickListener: ClickListener)
    :RecyclerView.Adapter<DataAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return DataViewHolder(view,clickListener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
      val responseClass=list[position]
        holder.setData(responseClass)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    class DataViewHolder(val itemView:View,val clickListener: ClickListener):RecyclerView.ViewHolder(itemView){
        @SuppressLint("CheckResult")
        fun setData(responseClass: ResponseClass) {

            itemView.apply {
                Glide.with(context)
                    .load(responseClass.image)
                    .load(imageView)

                firstName.text=responseClass.firstName
                lastName.text=responseClass.lastName
                email.text=responseClass.email

                delete.setOnClickListener{
                    clickListener.deleteData(responseClass.id)
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}