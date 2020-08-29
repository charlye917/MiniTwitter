package com.charlye934.minitwitter.home.domain

import android.util.Log
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.repository.HomeRepository
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp

class HomeInteractorImp : HomeInteractor {

    private val homeRepository: HomeRepository = HomeRepositoryImp()

    override suspend fun getTwitts(): List<Tweet>?{
        return try {
            val response = homeRepository.getTwitts()
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            null
        }
    }

    override suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet? {
        return try {
            val response = homeRepository.postTweet(requestCreateTweet)
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            null
        }
    }
}