package com.charlye934.minitwitter.home.presenter.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    fun getAllTweets(): MutableLiveData<List<Tweet>?> {
        CoroutineScope(Dispatchers.Main).launch {
            val response = homeInteractor.getAllTweets()
            allTweets.postValue(response)
        }
        return allTweets
    }

    fun getTweets(): MutableLiveData<List<Tweet>> = homeInteractor.getTweet()


    fun insertTweet(mensaje: String) = liveData{
        val dataTweet = homeInteractor.createTweet(mensaje)
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = homeInteractor.likeTweet(idTweet)
        getFavTweet()
        emit(response)
    }

    fun getFavTweet(): MutableLiveData<List<Tweet>?> {
        CoroutineScope(Dispatchers.Main).launch{
            val response = homeInteractor.getFavsTweets()
            favTweet.postValue(response)
        }
        return favTweet
    }

    fun deleteTweet(idTweet: Int) = liveData {
        val response = homeInteractor.deleteTweet(idTweet)
        emit(response)
    }

    fun opneDialogTweetMenu(context: Context?, idTweet: Int){

    }

    companion object{
        private val homeInteractor:HomeInteractor = HomeInteractorImp()
        private var allTweets:MutableLiveData<List<Tweet>?> = MutableLiveData()
        private var favTweet:MutableLiveData<List<Tweet>?> = MutableLiveData()
    }
}