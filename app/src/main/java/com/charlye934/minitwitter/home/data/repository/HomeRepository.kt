package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import retrofit2.Response

interface HomeRepository {
    suspend fun getTwitts(): Response<List<Tweet?>>
    suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Response<Tweet?>
}