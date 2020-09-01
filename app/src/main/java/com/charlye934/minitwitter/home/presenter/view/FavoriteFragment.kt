package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.MyApp
import com.charlye934.minitwitter.home.presenter.listener.ListenerHome
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_tweet_list.*

class FavoriteFragment : Fragment(), ListenerHome {

    private val viewModel: HomeViewModel by viewModels()
    private val favAdapter = TweetAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            recyclerData()
            loadNewFavData()
        }
    }

    private fun recyclerData(){
        recyclerlistFragmentFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favAdapter
        }
    }

    private fun loadNewFavData(){
        viewModel.getFavTweet().observe(viewLifecycleOwner){
            if(it != null){
                favAdapter.setData(it)
            }else{
                Toast.makeText(context, "Error al cargar los tweets", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun likePhoto(idTweet: Int) {

    }

    override fun deleteTweet(idTweet: Int) {

    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}