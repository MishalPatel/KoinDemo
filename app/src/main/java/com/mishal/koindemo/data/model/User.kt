package com.mishal.koindemo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("title")
    @Expose
    val original_title: String,
    @SerializedName("overview")
    @Expose
    val overview: String


)