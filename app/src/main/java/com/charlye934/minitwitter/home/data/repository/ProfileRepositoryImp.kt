package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.common.MiniTwitterClient
import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUploadPhoto
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import okhttp3.RequestBody

class ProfileRepositoryImp : ProfileRepository {

    private val profileService = MiniTwitterClient.authTwitterClient()

    override suspend fun getProfile(): ResponseUserProfile? {
        return profileService.getProfile()
    }

    override suspend fun updateProfile(requestUserProfile: RequestUserProfile): ResponseUserProfile? {
        return profileService.updateProfile(requestUserProfile)
    }

    override suspend fun uploadProfilePhoto(file: RequestBody): ResponseUploadPhoto {
        return profileService.uploadProfilePhoto(file)
    }

}