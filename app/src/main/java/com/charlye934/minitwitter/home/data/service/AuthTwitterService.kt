package com.charlye934.minitwitter.home.data.service

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthTwitterService {
    @GET("tweets/all")
    suspend fun getAllTweets(): List<Tweet>

    @POST("tweets/create")
    suspend fun createTweet(@Body requestCreateTweet: RequestCreateTweet): Tweet
}