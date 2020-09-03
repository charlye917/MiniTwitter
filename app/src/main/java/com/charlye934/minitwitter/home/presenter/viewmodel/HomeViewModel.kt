package com.charlye934.minitwitter.home.presenter.viewmodel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.domain.HomeInteractor
import com.charlye934.minitwitter.home.domain.HomeInteractorImp
import com.charlye934.minitwitter.home.presenter.view.BottomModalTweetFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    fun getAllTweets(){
        CoroutineScope(Dispatchers.IO).launch {
            allTweets!!.postValue(homeInteractor.getAllTweets())
        }
    }

    fun insertTweet(mensaje: String) = liveData{
        val dataTweet = homeInteractor.createTweet(mensaje)
        emit(dataTweet)
    }

    fun likeTweet(idTweet: Int) = liveData{
        val response = homeInteractor.likeTweet(idTweet)
        emit(response)
    }

    fun getFavTweet() = liveData {
        favTweets?.value = homeInteractor.getFavsTweets()
        emit(favTweets?.value)
    }

    fun deleteTweet(idTweet: Int) = liveData {
        val response = homeInteractor.deleteTweet(idTweet)
        emit(response)
    }

    fun opneDialogTweetMenu(context: Context?, idTweet: Int){

    }

    companion object{
        private val homeInteractor:HomeInteractor = HomeInteractorImp()
        var allTweets:MutableLiveData<List<Tweet>>? = MutableLiveData()
        private val favTweets:MutableLiveData<List<Tweet>>? = MutableLiveData()
    }
}