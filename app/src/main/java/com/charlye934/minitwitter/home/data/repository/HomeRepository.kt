package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.common.MiniTwitterClient
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet

class HomeRepositoryImp : HomeRepository {

    private val twitterClientService = MiniTwitterClient.authTwitterClient()

    override suspend fun getTwitts(): List<Tweet> {
        return twitterClientService.getAllTweets()
    }

    override suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet {
        return twitterClientService.createTweet(requestCreateTweet)
    }
}