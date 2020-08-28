package com.charlye934.minitwitter.home.domain

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.repository.HomeRepository
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp
import retrofit2.Response

class HomeInteractorImp : HomeInteractor {

    private val homeRepository: HomeRepository = HomeRepositoryImp()

    override suspend fun getTwitts(): Response<List<Tweet?>>{
        return try {
            val response = homeRepository.getTwitts()
            response
        }catch (e: Throwable){
            null!!
        }
    }

    override suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Response<Tweet?> {
        return try {
            val response = homeRepository.postTweet(requestCreateTweet)
            response
        }catch (e: Throwable){
            null!!
        }
    }
}