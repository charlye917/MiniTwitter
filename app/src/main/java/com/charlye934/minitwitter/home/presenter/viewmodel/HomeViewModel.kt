package com.charlye934.minitwitter.home.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp

class HomeViewModel : ViewModel() {
    private val homeInteractor:HomeInteractor = HomeInteractorImp()

    fun getTweets() = liveData{
        val response =  homeInteractor.getTwitts()
        emit(response)

    }

    fun postTweet(requestCreateTweet: RequestCreateTweet) = liveData{
        val dataTweet = homeInteractor.postTweet(requestCreateTweet)
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = homeInteractor.likeTweet(idTweet)
        emit(response)
    }
}