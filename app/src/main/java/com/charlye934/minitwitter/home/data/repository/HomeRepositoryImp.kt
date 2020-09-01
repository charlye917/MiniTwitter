package com.charlye934.minitwitter.home.data.repository

import com.charlye934.minitwitter.common.MiniTwitterClient
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import retrofit2.Response

class HomeRepositoryImp : HomeRepository {

    private val twitterClientService = MiniTwitterClient.authTwitterClient()

    override suspend fun getTwitts(): List<Tweet> {
        return twitterClientService.getAllTweets()
    }

    override suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet {
        return twitterClientService.createTweet(requestCreateTweet)
    }

    override suspend fun likeTweet(idTweet: Int): Tweet {
        return twitterClientService.likeTweet(idTweet)
    }

    override suspend fun deleteTweet(idTweet: Int): TweetDelete {
        return twitterClientService.deleteTweet(idTweet)
    }


}