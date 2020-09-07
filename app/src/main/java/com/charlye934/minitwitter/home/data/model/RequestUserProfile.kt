package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestUserProfile(
    @SerializedName("username")
    @Expose
    val username:String,

    @SerializedName("email")
    @Expose
    val email:String,

    @SerializedName("descripcion")
    @Expose
    val description:String,

    @SerializedName("website")
    @Expose
    val website:String,

    @SerializedName("password")
    @Expose
    val password:String
)