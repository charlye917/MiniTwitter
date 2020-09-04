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
        CoroutineScope(Dispatchers.IO).launch {
            val response = homeInteractor.getAllTweets()
            allTweets.postValue(response)
        }
        return allTweets
    }

    fun getTweets(): MutableLiveData<List<Tweet>?> = allTweets


    fun insertTweet(mensaje: String) = liveData{
        val newList:ArrayList<Tweet>? = allTweets.value as ArrayList<Tweet>
        val dataTweet = homeInteractor.createTweet(mensaje)
        dataTweet?.let { newList?.add(0, it) }
        allTweets.value = newList
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = homeInteractor.likeTweet(idTweet)
        emit(response)
    }

    fun getFavTweet(): MutableLiveData<List<Tweet>>? {
        CoroutineScope(Dispatchers.Main).launch{
            favTweets?.value = homeInteractor.getFavsTweets()
        }
        return favTweets
    }

    fun deleteTweet(idTweet: Int) = liveData {
        val response = homeInteractor.deleteTweet(idTweet)
        emit(response)
    }

    fun opneDialogTweetMenu(context: Context?, idTweet: Int){

    }

    companion object{
        private val homeInteractor:HomeInteractor = HomeInteractorImp()
        private val favTweets:MutableLiveData<List<Tweet>>? = MutableLiveData()
        private var allTweets:MutableLiveData<List<Tweet>?> = MutableLiveData()
        private var favTwets:MutableLiveData<List<Tweet>?> = MutableLiveData()
    }
}