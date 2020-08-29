package com.charlye934.minitwitter.home.presenter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp

class HomeViewModel : ViewModel() {
    private val homeInteractor:HomeInteractor = HomeInteractorImp()

    fun getTweets() = liveData {
        val dataTweet = homeInteractor.getTwitts()
        emit(dataTweet)
    }

    fun postTweet(requestCreateTweet: RequestCreateTweet) = liveData{
        val dataTweet = homeInteractor.postTweet(requestCreateTweet)
        emit(dataTweet)
    }
}