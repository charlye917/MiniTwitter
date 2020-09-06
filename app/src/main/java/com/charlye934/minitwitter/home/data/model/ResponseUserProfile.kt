package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseUserProfile(
    @SerializedName("id") @Expose
    val id: Int?,

    @SerializedName("username")
    @Expose
    val username: String?,

    @SerializedName("email")
    @Expose
    val email: String?,

    @SerializedName("descripcion")
    @Expose
    val descripcion: String?,

    @SerializedName("website")
    @Expose
    val website: String?,

    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String?,

    @SerializedName("created")
    @Expose
    val created: String?
)