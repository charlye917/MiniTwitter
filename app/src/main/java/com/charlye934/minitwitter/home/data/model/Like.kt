package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Like(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("username")
    @Expose
    val username: String? = null,

    @SerializedName("descripcion")
    @Expose
    val descripcion: String? = null,

    @SerializedName("website")
    @Expose
    val website: String? = null,

    @SerializedName("photoUrl")
    @Expose
     val photoUrl: String? = null,

    @SerializedName("created")
    @Expose
     val created: String? = null
)