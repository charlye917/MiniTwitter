package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
    private var tweetList: List<Tweet> = arrayListOf()
    private var tweetAdapter = TweetAdapter()

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
            loadNewData()
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
                tweetList = it
                Log.d("LoadTweetDataNew",it.size.toString())

                tweetAdapter.updateData(tweetList, this)
                refreshTweeList.isRefreshing = false
            }else{
                Toast.makeText(context, "Error al cargar los tweets",Toast.LENGTH_SHORT).show()
                refreshTweeList.isRefreshing = false
            }
        }
    }

    private fun loadNewData(){
        viewModel.getTweets().observe(viewLifecycleOwner){
            if(it != null){
                Log.d("LoadTweetDataNew",it.size.toString())
                tweetList = it as ArrayList<Tweet>
                refreshTweeList.isRefreshing = false
                tweetAdapter.setData(tweetList)
            }else{
                Toast.makeText(context, "Problemas al cargar los tweets",Toast.LENGTH_SHORT).show()
            }
        }
    }

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

    override fun deleteTweet(idTweet: Int) {

    }

    companion object {
        fun newInstance() = TweetListFragment()
    }
}