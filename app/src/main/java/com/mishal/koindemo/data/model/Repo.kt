package com.mishal.koindemo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Repo(

    @SerializedName("results")
    @Expose
    val userArray: ArrayList<User>
)