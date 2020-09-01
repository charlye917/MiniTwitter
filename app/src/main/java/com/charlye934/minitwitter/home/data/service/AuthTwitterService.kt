package com.charlye934.minitwitter.home.data.service

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import retrofit2.http.*

interface AuthTwitterService {
    @GET("tweets/all")
    suspend fun getAllTweets(): List<Tweet>

    @POST("tweets/create")
    suspend fun createTweet(@Body requestCreateTweet: RequestCreateTweet): Tweet

    @POST("tweets/like/{idTweet}")
    suspend fun likeTweet(@Path("idTweet") idTweet: Int): Tweet

    @DELETE("tweets/{idTweet}")
    suspend fun deleteTweet(@Path("idTweet") idTweet: Int): TweetDelete
}