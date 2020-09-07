package com.charlye934.minitwitter.home.domain

import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUploadPhoto
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import com.charlye934.minitwitter.home.data.repository.ProfileRepository
import com.charlye934.minitwitter.home.data.repository.ProfileRepositoryImp
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

class ProfileInteractorImp : ProfileInteractor{
    private val profileRepo:ProfileRepository = ProfileRepositoryImp()
    private val userProfile: MutableLiveData<ResponseUserProfile>? = MutableLiveData()
    private val photoProfile: MutableLiveData<String>? = MutableLiveData()

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

    override suspend fun uploadProfilePhoto(photoPath: String): ResponseUploadPhoto? {
        return try {
            val file = File(photoPath)
            val requestBody = RequestBody.create(MediaType.parse("image/jpg"), file)
            val response = profileRepo.uploadProfilePhoto(requestBody)

            SharedPreferencesManager().setSomeStringValue(Constants.PREF_PHOTOURL, response.fieldName)
            photoProfile?.value = response.fieldName
            response
        }catch (e: Throwable){
            null
        }
    }
}