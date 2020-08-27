package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_tweet_list.*

class TweetListFragment : Fragment() {

    private lateinit var adapter: TwittAdapter
    private var tweetList: MutableList<Tweet> = arrayListOf()
    private val viewModel:HomeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tweet_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadTweetData()
    }

    private fun loadTweetData(){
        viewModel.getTweets().observe(viewLifecycleOwner){
            if(it != null){
                tweetList = it as MutableList<Tweet>
                recyclerData()
            }else{
                Toast.makeText(activity, "Algo ha ido mal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun recyclerData(){
        recyclerlistFragmentTweet.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TwittAdapter(tweetList, context)
        }
    }

    companion object{
        val TAG = this::class.java.simpleName
    }
}