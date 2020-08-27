package com.charlye934.minitwitter.home.view

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

class TwittAdapter(val listTweet:List<Tweet>, val context:Context) : RecyclerView.Adapter<TwittAdapter.TwitterViewHolder>(){

    private val username = SharedPreferencesManager().setSomeStringValue(Constants.PREF_USERNAME)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TwitterViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet,parent, false)
        return TwitterViewHolder(item)
    }

    override fun onBindViewHolder(holder: TwitterViewHolder, position: Int) {
        val item = listTweet[position]

        holder.tvUsername.text = item.user.username
        holder.tvMessage.text = item.mensaje
        holder.tvLikeCount.text = item.likes.size.toString()

        val photo = item.user.photoUrl
        if(!photo.equals("")){
            Glide.with(context)
                .load(Constants.PHOTO_URL + photo)
                .into(holder.imgPhoto)
        }

        item.likes.forEach{
            if(it.username!!.equals(username)){
                Glide.with(context)
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