package com.charlye934.minitwitter.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.charlye934.minitwitter.R
import com.charlye934.minitwitter.common.Constants
import com.charlye934.minitwitter.common.SharedPreferencesManager
import com.charlye934.minitwitter.home.presenter.view.NewTweetDialogFragment
import com.charlye934.minitwitter.home.presenter.view.TweetListFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(savedInstanceState == null){
            supportActionBar!!.hide()
            goToHome()
            eventsNavigation()
            setPicturePerfil()
        }
    }

    private fun eventsNavigation(){
        navigationHome.setOnNavigationItemReselectedListener{
            when(it.itemId){
                R.id.navigation_home ->{ goToHome() }
                R.id.navigation_dashboard ->{Toast.makeText(applicationContext, "dashboard",Toast.LENGTH_SHORT).show()}
                R.id.navigation_notification ->{Toast.makeText(applicationContext, "notification",Toast.LENGTH_SHORT).show()}
            }
        }

        btnFloatHome.setOnClickListener {
            val dialog = NewTweetDialogFragment()
            dialog.show(supportFragmentManager, "NewTweetDialgoFragment")
        }
    }

    private fun setPicturePerfil(){
        val photoUrl = SharedPreferencesManager().getSomeStringValue(Constants.PREF_PHOTOURL)
        if(photoUrl!!.isNotEmpty()){
            Glide.with(this)
                .load(Constants.PHOTO_URL + photoUrl)
                .into(imgPerfilHome)
        }
    }

    private fun goToHome(){
        supportFragmentManager.beginTransaction()
            .replace( R.id.frameHome, TweetListFragment())
            .commit()
    }

    private fun changeFragment(fragment:Fragment, tag:String){
        supportFragmentManager.beginTransaction()
            .replace( R.id.frameHome, fragment)
            .addToBackStack(tag)
            .commit()
    }
}