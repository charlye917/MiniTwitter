package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.listener.ListenerHome
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_tweet_list.*

class TweetListFragment : Fragment(), ListenerHome {

    private val viewModel:HomeViewModel by activityViewModels()
    private val tweetAdapter = TweetAdapter(this)
    private var tweetListType: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments != null){
            tweetListType = requireArguments().getInt(Constants.TWEET_LIST_TYPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tweet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            swipeRefresh()
            recyclerData()

            //if(tweetListType == Constants.TWEET_LIST_ALL)
                loadTweetData()
            //else if(tweetListType == Constants.TWEET_LIST_FAVS)
            //    loadNewFavData()
        }
    }

    private fun swipeRefresh(){
        refreshTweeList.setColorSchemeColors(resources.getColor(R.color.colorAzul))

        refreshTweeList.setOnRefreshListener {
            refreshTweeList.isRefreshing = true
            if(tweetListType == Constants.TWEET_LIST_ALL)
                loadTweetData()
            else
                loadNewFavData()
        }
    }

    private fun recyclerData(){
        recyclerlistFragmentTweet.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tweetAdapter
        }
    }

    private fun loadTweetData(){
        viewModel.getTweets().observe(viewLifecycleOwner) {
            if(it != null){
                tweetAdapter.updateData(it)
                refreshTweeList.isRefreshing = false
            }else{
                Toast.makeText(context, "Error al cargar los tweets",Toast.LENGTH_SHORT).show()
                refreshTweeList.isRefreshing = false
            }
        }
    }

    private fun loadNewFavData(){
        viewModel.getFavTweet().observe(viewLifecycleOwner){
            if(it != null){
                tweetAdapter.setData(it)
            }else{
                Toast.makeText(context, "Error al cargar los tweets",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loadFavTweetData(){}

    override fun likePhoto(idTweet: Int) {
        viewModel.likeTweet(idTweet).observe(viewLifecycleOwner){
            if(it != null){
                Toast.makeText(context, "Le dio like", Toast.LENGTH_SHORT).show()
                loadTweetData()
            }else{
                Toast.makeText(context, "Problema al dar like", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        val TAG = this::class.java.simpleName
        fun newInstance() = TweetListFragment()
    }
}