package com.charlye934.minitwitter.home.presenter.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val dataTweet = getTweets()
    val dataError = MutableLiveData<String?>()
    private val listaClonada:ArrayList<Tweet> = ArrayList()

    private fun getTweets(): MutableLiveData<List<Tweet>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = homeInteractor.getTwitts()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    dataTweet.value = response.body() as List<Tweet>?
                }else{
                    dataTweet.value = arrayListOf()
                    dataError.value = "Error ${response.raw()}"
                }
            }
        }
        return dataTweet
    }

    fun postTweet(requestCreateTweet: RequestCreateTweet){
        CoroutineScope(Dispatchers.IO).launch {
            val response = homeInteractor.postTweet(requestCreateTweet)
            val allTweets = dataTweet.value!!.size - 1
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    listaClonada.add(response.body()!!)
                    for(i in 0..allTweets){
                        listaClonada.add(dataTweet.value!![i])
                    }
                    dataTweet.value = listaClonada
                }else{
                    Toast.makeText(MyApp.getContext(), "Error al envair el tweet intentelo mas tarde",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}