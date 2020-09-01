package com.charlye934.minitwitter.home.domain

import android.util.Log
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import com.charlye934.minitwitter.home.data.repository.HomeRepository
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp
import java.lang.Exception

class HomeInteractorImp : HomeInteractor {

    private val homeRepository: HomeRepository = HomeRepositoryImp()
    private val userName = SharedPreferencesManager().getSomeStringValue(Constants.PREF_USERNAME)
    private var allTweet: ArrayList<Tweet> = arrayListOf()
    private var favTweets = arrayListOf<Tweet>()

    override suspend fun getTwitts(): List<Tweet>?{
        return try {
            val response = homeRepository.getTwitts()
            allTweet = response as ArrayList<Tweet>
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            allTweet = arrayListOf()
            null
        }
    }

    override suspend fun postTweet(requestCreateTweet: RequestCreateTweet): Tweet? {
        return try {
            val response = homeRepository.postTweet(requestCreateTweet)
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            null
        }
    }

    override suspend fun likeTweet(idTweet: Int): Tweet? {
        return try {
            val response = homeRepository.likeTweet(idTweet)
            response
        }catch (e: Throwable){
            Log.d("Error", "error: ${e.message}")
            null
        }
    }

    override suspend fun getFavsTweets(): List<Tweet>? {
        if(allTweet.isEmpty()){
            allTweet = getTwitts() as ArrayList<Tweet>
        }

        return try{
            favTweets.clear()
            for(i in 0..allTweet.size - 1){
                if(allTweet[i].likes.isNotEmpty()){
                    for(j in 0..allTweet[i].likes.size - 1){
                        if(allTweet[i].likes[j].username.equals(userName)){
                            favTweets.add(allTweet[i])
                        }
                    }
                }
            }
            favTweets
        }catch (e:Throwable){
           null
        }
    }

    override suspend fun deleteTweet(idTweet: Int): TweetDelete? {
        val cloneTweet = arrayListOf<Tweet>()
       return try {
           val response = homeRepository.deleteTweet(idTweet)
           for(i in 0..allTweet.size - 1 ){
               if(allTweet[i].id != idTweet){
                   cloneTweet.add(allTweet[i])
               }
           }
           allTweet = cloneTweet
           getFavsTweets()
           response
       }catch (e: Throwable){
           null
       }
    }
}