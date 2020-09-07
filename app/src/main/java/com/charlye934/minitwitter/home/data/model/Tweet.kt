package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tweet(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("mensaje")
    @Expose
    val mensaje:String,

    @SerializedName("likes")
    @Expose
    val likes: List<Like> = arrayListOf(),

    @SerializedName("user")
    @Expose
    val user:User
)