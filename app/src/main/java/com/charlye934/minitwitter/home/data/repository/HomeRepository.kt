package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import retrofit2.Response

interface HomeRepository {
    suspend fun getTwitts():List<Tweet>
    suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet
    suspend fun likeTweet(idTweet: Int): Tweet
    suspend fun deleteTweet(idTweet:Int): TweetDelete
}