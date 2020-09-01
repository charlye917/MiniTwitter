package com.charlye934.minitwitter.home.presenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.home.presenter.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_modal_tweet.*

class BottomModalTweetFragment : BottomSheetDialogFragment() {

    private val viewModel:HomeViewModel by viewModels()
    private var idTweetDelete: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null)
            idTweetDelete = requireArguments().getInt(Constants.ARG_TWEET_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_modal_tweet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init(){
        navigation_view_bottom_tweet.setNavigationItemSelectedListener {
            var id = it.itemId
            if(id == R.id.action_delete_tweet){
                viewModel.deleteTweet(idTweetDelete).observe(viewLifecycleOwner){
                    if(it != null)
                        Toast.makeText(context,"Tweet eliminado",Toast.LENGTH_SHORT).show()
                    else{
                        Toast.makeText(context, "Error al borrar el tweet",Toast.LENGTH_SHORT).show()
                    }
                }
                dialog!!.dismiss()
                true
            }
            false
        }
    }

    companion object{
        fun newInstance(idTweet:Int): BottomModalTweetFragment{
            val fragment = BottomModalTweetFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_TWEET_ID, idTweet)
            return fragment
        }
    }
}