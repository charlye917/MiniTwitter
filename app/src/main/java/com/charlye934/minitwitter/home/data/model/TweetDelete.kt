package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TweetDelete (
    @SerializedName("mensaje")
    @Expose
    val mensaje:String,

    @SerializedName("user")
    @Expose
    val user:User
)