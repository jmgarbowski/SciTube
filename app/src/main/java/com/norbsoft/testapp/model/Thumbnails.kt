package com.norbsoft.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnails(
    val default: Thumbnail,
    val medium: Thumbnail,
    val high: Thumbnail
) : Parcelable