package com.charlye934.minitwitter.home.domain

import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete

interface TweetInteractor {
        fun getTweet():MutableLiveData<List<Tweet>>
        suspend fun getAllTweets(): List<Tweet>?
        suspend fun createTweet(mensaje: String): Tweet?
        suspend fun likeTweet(idTweet: Int): Tweet?
        suspend fun getFavsTweets():List<Tweet>?
        suspend fun deleteTweet(idTweet: Int): TweetDelete?
}