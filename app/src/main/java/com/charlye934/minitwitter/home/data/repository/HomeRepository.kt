package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.common.MiniTwitterClient
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet

interface HomeRepository {

     suspend fun getTwitts(): List<Tweet>

     suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet

}