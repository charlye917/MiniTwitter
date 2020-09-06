package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import retrofit2.Response

interface TweetRepository {
    suspend fun getTwitts():List<Tweet>
    suspend fun createTweet(mensaje:String): Tweet
    suspend fun likeTweet(idTweet: Int): Tweet
    suspend fun deleteTweet(idTweet:Int): TweetDelete

}