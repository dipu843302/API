package com.example.api.responseclass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseClass(
    val id: Int, val email: String,
    val firstName: String, val lastName: String,
    val image: String
) : Parcelable
