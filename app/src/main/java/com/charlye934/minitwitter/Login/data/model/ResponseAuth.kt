package com.charlye934.minitwitter.Login.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseAuth(
    @SerializedName("token")
    @Expose
    val token:String,

    @SerializedName("username")
    @Expose
    val username:String,

    @SerializedName("email")
    @Expose
    val email:String,

    @SerializedName("photoUrl")
    @Expose
    val photoUrl:String,

    @SerializedName("created")
    @Expose
    val created: String,

    @SerializedName("active")
    @Expose
    val active:Boolean
)