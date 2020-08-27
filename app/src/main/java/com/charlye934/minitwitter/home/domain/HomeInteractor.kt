package com.charlye934.minitwitter.home.domain

import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet

interface HomeInteractor {
        suspend fun getTwitts(): List<Tweet>?
        suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet?

}