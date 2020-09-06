package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import retrofit2.Response

interface ProfileRepository {
    suspend fun getProfile(): ResponseUserProfile?
    suspend fun updateProfile(requestUserProfile: RequestUserProfile):ResponseUserProfile?
}