package com.charlye934.minitwitter.home.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.RequestUserProfile
import com.charlye934.minitwitter.home.data.model.ResponseUserProfile
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.ProfileInteractor
import com.charlye934.minitwitter.home.domain.ProfileInteractorImp
import com.charlye934.minitwitter.home.domain.TweetInteractor
import com.charlye934.minitwitter.home.domain.TweetInteractorImp
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    fun getAllTweets(): MutableLiveData<List<Tweet>?> {
        CoroutineScope(Dispatchers.Main).launch {
            val response = tweetInteractor.getAllTweets()
            allTweets.postValue(response)
        }
        return allTweets
    }

    fun getTweets(): MutableLiveData<List<Tweet>> = tweetInteractor.getTweet()


    fun insertTweet(mensaje: String) = liveData{
        val dataTweet = tweetInteractor.createTweet(mensaje)
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = tweetInteractor.likeTweet(idTweet)
        getFavTweet()
        emit(response)
    }

    fun getFavTweet(): MutableLiveData<List<Tweet>?> {
        CoroutineScope(Dispatchers.Main).launch{
            val response = tweetInteractor.getFavsTweets()
            favTweet.postValue(response)
        }
        return favTweet
    }

    fun deleteTweet(idTweet: Int) = liveData {
        val response = tweetInteractor.deleteTweet(idTweet)
        getFavTweet()
        emit(response)
    }

    fun changeProfile() = liveData {
            userProfile.value = profileInteractor.getProfile()
            emit(userProfile.value)
    }

    fun updateProfile(requestUserProfile: RequestUserProfile) = liveData {
        userProfile.value = profileInteractor.updateProfile(requestUserProfile)
        emit(userProfile.value)
    }



    companion object{
        private val tweetInteractor:TweetInteractor = TweetInteractorImp()
        private val profileInteractor:ProfileInteractor = ProfileInteractorImp()
        private var allTweets:MutableLiveData<List<Tweet>?> = MutableLiveData()
        private var favTweet:MutableLiveData<List<Tweet>?> = MutableLiveData()
        private var userProfile:MutableLiveData<ResponseUserProfile?> = MutableLiveData()
    }
}