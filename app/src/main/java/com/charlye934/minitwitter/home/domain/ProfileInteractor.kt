package com.charlye934.minitwitter.home.domain

import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUploadPhoto
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import okhttp3.RequestBody

interface ProfileInteractor {
    suspend fun getProfile(): ResponseUserProfile?
    suspend fun updateProfile(requestProfile: RequestUserProfile): ResponseUserProfile?
    suspend fun uploadProfilePhoto(photoPath: String): ResponseUploadPhoto?
}