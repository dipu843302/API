package com.example.api.responseclass

import kotlinx.android.parcel.Parcelize


data class ResponseClass(
    val id: Int, val email: String,
    val firstName: String, val lastName: String,
    val image: String
)
