package com.charlye934.minitwitter.home.domain

import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete

interface HomeInteractor {
        suspend fun getAllTwitts(): List<Tweet>?
        suspend fun createTweet(requestCreateTweet: RequestCreateTweet): Tweet?
        suspend fun likeTweet(idTweet: Int): Tweet?
        suspend fun getFavsTweets():List<Tweet>?
        suspend fun deleteTweet(idTweet: Int): TweetDelete?
}