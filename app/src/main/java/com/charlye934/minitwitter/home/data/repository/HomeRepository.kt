package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import retrofit2.Response

interface HomeRepository {
    suspend fun getTwitts():List<Tweet>
    suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet
}