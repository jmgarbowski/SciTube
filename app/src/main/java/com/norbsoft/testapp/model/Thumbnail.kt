package com.norbsoft.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    val url: String,
    val width: Int,
    val height: Int
) : Parcelable