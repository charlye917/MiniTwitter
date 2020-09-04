package com.charlye934.minitwitter.home.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import com.charlye934.minitwitter.home.data.repository.HomeRepository
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp

class HomeInteractorImp : HomeInteractor {

    companion object {
        private val homeRepository: HomeRepository = HomeRepositoryImp()
        private val userName = SharedPreferencesManager().getSomeStringValue(Constants.PREF_USERNAME)
        private var allTweet: ArrayList<Tweet> = arrayListOf()
        private var favTweets: ArrayList<Tweet> = arrayListOf()
    }

    override suspend fun getAllTweets(): List<Tweet>?{
        return try {
            allTweet = homeRepository.getTwitts() as ArrayList<Tweet>
            allTweet
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            allTweet = arrayListOf()
            null
        }
    }

    override suspend fun getFavsTweets(): List<Tweet>? {
        if(allTweet.isNullOrEmpty()){
            allTweet = getAllTweets() as ArrayList<Tweet>
        }

        return try{
            val itTweet = allTweet.iterator()
            val newFavList = arrayListOf<Tweet>()

            while(itTweet.hasNext()){
                val current = itTweet.next()
                val itLikes = current.likes.iterator()
                var enc = false

                while(itLikes.hasNext() && !enc){
                    val like = itLikes.next()
                    if(like.username.equals(userName)){
                        enc = true
                        newFavList.add(current)
                    }
                }
            }
            favTweets = newFavList
            favTweets

        }catch (e:Throwable){
            null
        }
    }

    override suspend fun createTweet(mensaje: String): Tweet? {
        return try {
            var listaClonada = arrayListOf<Tweet>()
            val response = homeRepository.createTweet(mensaje)

            listaClonada.add(response)
            allTweet.forEach {
                listaClonada.add(it)
            }
            allTweet = listaClonada
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            null
        }
    }

    override suspend fun deleteTweet(idTweet: Int): TweetDelete? {
        val cloneTweet = arrayListOf<Tweet>()
       return try {
           val response = homeRepository.deleteTweet(idTweet)
           for(i in allTweet.indices){
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

    override suspend fun likeTweet(idTweet: Int): Tweet? {
        Log.d("interactor", allTweet.toString())
        return try {
            //val listaClonada = arrayListOf<Tweet>()
            val response = homeRepository.likeTweet(idTweet)
            /*allTweet.forEach {
                if(it.id == idTweet)
                    //reemplzamos la infromacion del viejo tweet con la que ahora le dimos like
                    listaClonada.add(response)
                else
                    listaClonada.add(it)
            }
            getFavsTweets()*/
            response
        }catch (e: Throwable){
            Log.d("Error", "error: ${e.message}")
            null
        }
    }
}