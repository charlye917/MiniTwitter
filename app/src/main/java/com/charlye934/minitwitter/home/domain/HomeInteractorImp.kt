package com.charlye934.minitwitter.home.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.model.TweetDelete
import com.charlye934.minitwitter.home.data.repository.HomeRepository
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel

class HomeInteractorImp : HomeInteractor {

    companion object {
        private val homeRepository: HomeRepository = HomeRepositoryImp()
        private val userName = SharedPreferencesManager().getSomeStringValue(Constants.PREF_USERNAME)
        private var allTweet: MutableLiveData<List<Tweet>> = MutableLiveData()
        private var favTweets: MutableLiveData<List<Tweet>> = MutableLiveData()
    }

    override fun getTweet(): MutableLiveData<List<Tweet>> = allTweet

    override suspend fun getAllTweets(): List<Tweet>?{
        return try {
            allTweet.value = homeRepository.getTwitts()
            allTweet.value
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            allTweet = MutableLiveData()
            null
        }
    }

    override suspend fun createTweet(mensaje: String): Tweet? {
        return try {
            val newTweet: ArrayList<Tweet>? = allTweet.value as ArrayList<Tweet>?
            val response = homeRepository.createTweet(mensaje)

            newTweet?.add(0,response)
            allTweet.value = newTweet
            response
        }catch (e: Throwable){
            Log.d("Error","${e.message}")
            null
        }
    }

    override suspend fun likeTweet(idTweet: Int): Tweet? {
        return try {
            val listaClonada = arrayListOf<Tweet>()
            val listaFav = arrayListOf<Tweet>()
            val response = homeRepository.likeTweet(idTweet)

            allTweet.value?.forEach {
                if(it.id == idTweet){
                    //reemplzamos la infromacion del viejo tweet con la que ahora le dimos like
                    listaClonada.add(response)
                    listaFav.add(response)
                }

                else
                    listaClonada.add(it)
            }
            allTweet.value = listaClonada
            //getFavsTweets()
            response
        }catch (e: Throwable){
            Log.d("Error", "error: ${e.message}")
            null
        }
    }

    override suspend fun getFavsTweets(): List<Tweet>? {
        if(allTweet.value.isNullOrEmpty()){
            allTweet.value = getAllTweets()
        }

        return try{
            val itTweet = allTweet.value?.iterator()
            val newFavList = arrayListOf<Tweet>()

            while(itTweet!!.hasNext()){
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
            favTweets.value = newFavList
            favTweets.value

        }catch (e:Throwable){
            null
        }
    }

    override suspend fun deleteTweet(idTweet: Int): TweetDelete? {
        val cloneTweet = arrayListOf<Tweet>()
       return try {
           val response = homeRepository.deleteTweet(idTweet)
           for(i in allTweet.value!!.indices){
               if(allTweet.value!![i].id != idTweet){
                   cloneTweet.add(allTweet.value!![i])
               }
            }

           allTweet.value = cloneTweet
           getFavsTweets()
           response
       }catch (e: Throwable){
           null
       }
    }
}