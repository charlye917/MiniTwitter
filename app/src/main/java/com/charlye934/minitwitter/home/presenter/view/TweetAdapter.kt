package com.charlye934.minitwitter.home.presenter.view

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.MyApp
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.data.model.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter : RecyclerView.Adapter<TweetAdapter.TwitterViewHolder>(){

    private val listTweet =  ArrayList<Tweet>()
    private val username = SharedPreferencesManager().getSomeStringValue(Constants.PREF_USERNAME)
    private val context = MyApp.getContext()

    fun updateData(newTweet:List<Tweet>){
        listTweet.clear()
        listTweet.addAll(newTweet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet,parent, false)
        return TwitterViewHolder(item)
    }

    override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
        val item = listTweet[position]

        holder.tvUsername.text = "@${item.user.username}"
        holder.tvMessage.text = item.mensaje
        holder.tvLikeCount.text = item.likes.size.toString()

        val photo = item.user.photoUrl
        if(!photo.equals("")){
            Glide.with(context!!)
                .load(Constants.PHOTO_URL + photo)
                .into(holder.imgPhoto)
        }

        Glide.with(context)
            .load(R.drawable.ic_like)
            .into(holder.imgLike)
        holder.tvLikeCount.setTextColor(context.resources.getColor(android.R.color.black))
        holder.tvLikeCount.setTypeface(null, Typeface.NORMAL)

        item.likes.forEach{
            if(it.username!!.equals(username)){
                Glide.with(context!!)
                    .load(R.drawable.ic_like_pink)
                    .into(holder.imgLike)
                holder.tvLikeCount.setTextColor(context.resources.getColor(R.color.pink))
                holder.tvLikeCount.setTypeface(null, Typeface.BOLD)
            }
        }
    }

    override fun getItemCount(): Int = listTweet.size

    class TwitterViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val imgPhoto = item.imgPhotoPerfilItem
        val imgLike = item.imgLikeItem
        val tvUsername = item.tvUsernameItem
        val tvMessage = item.tvMessageItem
        val tvLikeCount = item.tvCountLikes
    }

}