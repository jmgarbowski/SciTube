package com.norbsoft.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Snippet(
    val title: String,
    val description: String,
    val thumbnails: Thumbnails,
    val channelTitle: String
) : Parcelable