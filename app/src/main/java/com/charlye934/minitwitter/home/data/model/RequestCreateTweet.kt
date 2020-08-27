package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestCreateTweet(
    @SerializedName("mensaje")
    @Expose
    val mensaje:String
)