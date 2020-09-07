package com.charlye934.minitwitter.home.presenter.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.home.HomeActivity
import com.charlye934.minitwitter.home.data.model.Tweet
import com.charlye934.minitwitter.home.presenter.listener.ListenerHome
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(){

    private val viewModel: HomeViewModel by viewModels()
    private val favAdapter = TweetAdapter()
    private var listFavData = arrayListOf<Tweet>()
    private lateinit var listener: ListenerHome

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
            loadFavData()
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
                favAdapter.updateData(listFavData, listener)
                refreshFavoritesList.isRefreshing = false
                lottieAnimationFavoritos.isVisible = false
            }else{
                Toast.makeText(context, "Error al cargar los tweets", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = activity as HomeActivity
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}