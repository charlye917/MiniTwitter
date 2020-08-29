package com.charlye934.minitwitter.home.presenter.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.common.MyApp
import com.charlye934.minitwitter.home.data.model.RequestCreateTweet
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.data.repository.HomeRepositoryImp
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    val homeInteractor:HomeInteractor = HomeInteractorImp()


    fun getTweets() =  liveData{
        val response = homeInteractor.getTwitts()
        emit(response)

    }

    fun postTweet(requestCreateTweet: RequestCreateTweet) = liveData{
        val response = homeInteractor.postTweet(requestCreateTweet)
        emit(response)
    }
}