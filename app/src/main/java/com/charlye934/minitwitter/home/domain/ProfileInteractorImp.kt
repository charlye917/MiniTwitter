package com.charlye934.minitwitter.home.domain

import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import com.charlye934.minitwitter.home.data.repository.ProfileRepository
import com.charlye934.minitwitter.home.data.repository.ProfileRepositoryImp

class ProfileInteractorImp : ProfileInteractor{
    private val profileRepo:ProfileRepository = ProfileRepositoryImp()
    private val userProfile: MutableLiveData<ResponseUserProfile>? = MutableLiveData()

    override suspend fun getProfile(): ResponseUserProfile? {
        return try {
            val response = profileRepo.getProfile()
            userProfile?.value = response
            userProfile?.value
        }catch (e: Throwable){
            null
        }
    }

    override suspend fun updateProfile(requestProfile: RequestUserProfile): ResponseUserProfile? {
        return try {
            val response = profileRepo.updateProfile(requestProfile)
            userProfile?.value = response
            userProfile?.value
        }catch (e: Throwable){
            null
        }
    }
}