package com.charlye934.minitwitter.home.presenter.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp
import com.charlye934.minitwitter.home.presenter.view.BottomModalTweetFragment

class HomeViewModel : ViewModel() {
    private val homeInteractor:HomeInteractor = HomeInteractorImp()
    private val allTweets = MutableLiveData<List<Tweet>>()

    fun getAllTweets() = liveData{
        val response =  homeInteractor.getAllTwitts()
        allTweets.value = response
        emit(response)
    }

    fun createTweet(requestCreateTweet: RequestCreateTweet) = liveData{
        val dataTweet = homeInteractor.createTweet(requestCreateTweet)
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = homeInteractor.likeTweet(idTweet)
        emit(response)
    }

    fun getFavTweet() = liveData {
        val response = homeInteractor.getFavsTweets()
        emit(response)
    }

    fun deleteTweet(idTweet: Int) = liveData {
        val response = homeInteractor.deleteTweet(idTweet)
        emit(response)
    }

    fun opneDialogTweetMenu(context: Context?, idTweet: Int){
        val appCompact = context as AppCompatActivity
        val dialog = BottomModalTweetFragment.newInstance(idTweet)
        dialog.show(appCompact.supportFragmentManager, "BottomModalTweetFragment")
    }
}