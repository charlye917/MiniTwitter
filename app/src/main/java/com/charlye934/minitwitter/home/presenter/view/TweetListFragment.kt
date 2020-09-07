package com.charlye934.minitwitter.home.presenter.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.home.HomeActivity
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.listener.ListenerHome
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_tweet_list.*

class TweetListFragment : Fragment() {

    private val viewModel:HomeViewModel by activityViewModels()
    private var tweetList: ArrayList<Tweet> = arrayListOf()
    private var tweetAdapter = TweetAdapter()
    private lateinit var listener:ListenerHome

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tweet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh()
        recyclerData()
        viewModel.getAllTweets()
        loadTweetData()
    }

    private fun swipeRefresh(){
        refreshTweeList.setColorSchemeColors(resources.getColor(R.color.colorAzul))

        refreshTweeList.setOnRefreshListener {
            refreshTweeList.isRefreshing = true
            loadTweetData()
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
                tweetList = it as ArrayList<Tweet>
                tweetAdapter.updateData(tweetList, listener)
                refreshTweeList.isRefreshing = false
                lottieAnimationTweetList.isVisible = false
            }else{
                Toast.makeText(context, "Error al cargar los tweets",Toast.LENGTH_SHORT).show()
                refreshTweeList.isRefreshing = false
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as HomeActivity
    }

    companion object {
        fun newInstance() = TweetListFragment()
    }
}