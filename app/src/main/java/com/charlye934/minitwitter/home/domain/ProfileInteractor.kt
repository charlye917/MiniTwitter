package com.charlye934.minitwitter.home.domain

import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile

interface ProfileInteractor {
    suspend fun getProfile(): ResponseUserProfile?
    suspend fun updateProfile(requestProfile: RequestUserProfile): ResponseUserProfile?
}