package com.charlye934.minitwitter.home.data.service

import com.charlye934.minitwitter.home.data.model.*
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

    //USER PROFILE
    @GET("users/profile")
    suspend fun getProfile(): ResponseUserProfile

    @PUT("users/profile")
    suspend fun updateProfile(@Body requestProfile: RequestUserProfile): ResponseUserProfile
}