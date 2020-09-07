package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUploadPhoto
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import okhttp3.RequestBody

interface ProfileRepository {
    suspend fun getProfile(): ResponseUserProfile?
    suspend fun updateProfile(requestUserProfile: RequestUserProfile):ResponseUserProfile?
    suspend fun uploadProfilePhoto(file: RequestBody): ResponseUploadPhoto
}