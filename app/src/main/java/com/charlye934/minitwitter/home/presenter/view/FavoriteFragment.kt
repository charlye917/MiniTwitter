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
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.listener.ListenerHome
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_tweet_list.*

class FavoriteFragment : Fragment(), ListenerHome {

    private val viewModel: HomeViewModel by viewModels()
    private val favAdapter = TweetAdapter()
    private var listFavData = arrayListOf<Tweet>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh()
        recyclerData()
        loadFavData()

    }

    private fun swipeRefresh(){
        refreshFavoritesList.setColorSchemeColors(resources.getColor(R.color.colorAzul))

        refreshFavoritesList.setOnRefreshListener {
            refreshFavoritesList.isRefreshing = true
            loadNewFavData()
        }
    }

    private fun recyclerData(){
        recyclerlistFragmentFavorites.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favAdapter
        }
    }

    private fun loadFavData(){
        viewModel.getFavTweet().observe(viewLifecycleOwner){
            if(it != null){
                listFavData = it as ArrayList<Tweet>
                favAdapter.updateData(listFavData,this)
            }else{
                Toast.makeText(context, "Error al cargar los tweets", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadNewFavData(){
        viewModel.getFavTweet().observe(viewLifecycleOwner){
            if(it != null){
                listFavData = it as ArrayList<Tweet>
                refreshFavoritesList.isRefreshing = false
                favAdapter.setData(listFavData)
            }else{
                Toast.makeText(context,"problema al dar like",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun likePhoto(idTweet: Int) {
        viewModel.likeTweet(idTweet).observe(viewLifecycleOwner){
            if(it != null){
                Toast.makeText(context, "Le dio like", Toast.LENGTH_SHORT).show()
                loadFavData()
            }else{
                Toast.makeText(context, "Problema al dar like", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun deleteTweet(idTweet: Int) {

    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}