package com.charlye934.minitwitter.home.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseUploadPhoto(

    @SerializedName("fieldname")
    @Expose
    val fieldName: String,

    @SerializedName("originalname")
    @Expose
    val originalname: String,

    @SerializedName("encoding")
    @Expose
    val encoding: String,

    @SerializedName("mimetype")
    @Expose
    val mimetype: String,

    @SerializedName("destination")
    @Expose
    val destination: String,

    @SerializedName("filename")
    @Expose
    val filename: String,

    @SerializedName("path")
    @Expose
    val path: String,

    @SerializedName("size")
    @Expose
    val size: Int
)