package com.example.artikelapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Articles(
    val title: String,
    val description: String,
    val photo: Int
) : Parcelable
