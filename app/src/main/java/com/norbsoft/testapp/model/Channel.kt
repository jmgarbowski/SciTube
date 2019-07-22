package com.norbsoft.testapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Channel(
    val snippet: Snippet
) : Parcelable